package service.properties.impl;

import model.label.LabelType;
import model.label.impl.NoAttrLabelType;
import org.dom4j.*;
import org.dom4j.dom.DOMDocument;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.BaseElement;
import service.properties.PropertiesService;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 配置信息持久化类
 *
 * @author yohoyes
 */
public class XmlPropertiesService  implements PropertiesService {
    private SAXReader reader = new SAXReader();
    private XMLWriter writer = null;
    private Document document = null;
    private Element root = null;
    private String url = null;

    public XmlPropertiesService(String file) {
        url = this.getClass().getClassLoader().getResource(file).getFile();
        tryReadXml(url);
    }

    private void tryReadXml(String url) {
        try {
            document = reader.read(new File(url));
            root = document.getRootElement();
        }catch (DocumentException e) {
            createPropertiesXml();
        }
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

    private void createPropertiesXml() {
        document = new DOMDocument();
        root = new BaseElement("properties");
        document.setRootElement(root);
    }

    @Override
    public void addLabelType(LabelType label) {
        Element element = root.addElement("label");
        element.addAttribute("name", label.getName());
        write();
    }

    @Override
    public void setPath(String path) {
        Element element = root.element("path");
        if(element == null) {
            element = root.addElement("path");
        }
        element.addAttribute("url", path);
        write();
    }

    @Override
    public String getPath() {
        try {
            return root.element("path").attribute("url").getValue();
        }catch (Exception e) {
            return "";
        }
    }

    @Override
    public List<LabelType> readLabelType() {
        List<LabelType> labels = new ArrayList<>();
        for (Iterator it = root.elementIterator("label"); it.hasNext();) {
            Element element = (Element) it.next();
            String name = element.attribute("name").getValue();
            labels.add(new NoAttrLabelType(name));
        }
        return labels;
    }
}
