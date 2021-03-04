package service.history.impl;

import model.Arrangement;
import model.label.LabelType;
import model.label.impl.AttrLabelType;
import model.label.impl.NoAttrLabelType;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.dom.DOMDocument;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.DefaultElement;
import service.history.ArrangementService;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 历史记录的xml实现类
 *
 * @author yohoyes
 */
public class XmlArrangementService implements ArrangementService {

    private SAXReader reader = new SAXReader();
    private XMLWriter writer = null;
    private Document document = null;
    private Element root = null;
    private final String FILE_NAME = "history.xml";
    private String url = null;

    public XmlArrangementService(String path) {
        url = path + File.separator + FILE_NAME;
        tryReadXml(url);
    }

    private void tryReadXml(String url) {
        try {
            document = reader.read(new File(url));
            root = document.getRootElement();
        }catch (DocumentException e) {
            createArrangementXml();
        }
    }

    private void createArrangementXml() {
        document = new DOMDocument();
        root = new DefaultElement("arrangements");
        document.setRootElement(root);
    }

    @Override
    public List<Arrangement> read() {
        List<Arrangement> arrangements = new ArrayList<>();

        for (Iterator it = root.elementIterator("arrangement"); it.hasNext();) {
            Element element = (Element) it.next();
            String labelType = element.attribute("labelType").getValue();
            String value = element.attribute("value").getValue();
            Long deadline = Long.valueOf(element.attribute("deadline").getValue());
            Arrangement arrangement = new Arrangement(value);
            arrangement.setDeadline(deadline);
            boolean hasAttr = Boolean.valueOf(element.attribute("hasLabelAttr").getValue());
            if(hasAttr){
                String labelAttr = element.attribute("labelAttr").getValue();
                arrangement.setLabel(new AttrLabelType(labelType, labelAttr));
            }else {
                arrangement.setLabel(new NoAttrLabelType(labelType));
            }
            arrangements.add(arrangement);
        }
        return arrangements;
    }

    @Override
    public void save(List<Arrangement> arrangements) {
        createArrangementXml();
        new Runnable() {
            @Override
            public void run() {
                for (Arrangement arrangement : arrangements) {
                    Element element = root.addElement("arrangement");
                    element.addAttribute("labelType", arrangement.getLabel().getName());
                    element.addAttribute("value", arrangement.getValue());
                    element.addAttribute("deadline", arrangement.getDeadline()+"");
                    boolean hasAttr = arrangement.getLabel().hasAttr();
                    element.addAttribute("hasLabelAttr", hasAttr+"");
                    if(hasAttr) {
                        element.addAttribute("labelAttr", arrangement.getLabel().getAttr());
                    }
                }
                write();
            }
        }.run();
    }

    private void write() {
        try {
            writer = new XMLWriter(new FileWriter(url));
            writer.write(document);
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
