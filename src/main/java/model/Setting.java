package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.label.LabelType;
import model.label.impl.AttrLabelType;
import model.label.impl.NoAttrLabelType;
import service.properties.PropertiesService;
import service.properties.impl.XmlPropertiesService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 设置类
 * 用来调控程序的相关设置
 *
 * @author yohoyes
 */
public class Setting {
    private static String PROPERTIES_FILE_NAME = "properties.xml";
    public static String APPLICATION_NAME = "代办事项";
    public static String ARRANGEMENT_DEFAULT_TEXT = "Text Something Here";

    private StringProperty path = new SimpleStringProperty();
    private static Setting properties = new Setting();
    private List<LabelType> labels = null;
    private PropertiesService service = null;

    private Setting() {
        init();
    }

    public void init() {
        service = new XmlPropertiesService(PROPERTIES_FILE_NAME);
        labels = service.readLabelType();
        labels.add(new AttrLabelType("DONE", "DONE"));
        String path = service.getPath();
        if("".equals(path)){
            path = this.getClass().getClassLoader().getResource("").getPath();
        }
        this.path.setValue(path);
    }

    public static Setting getSetting() {
        return properties;
    }

    public List<LabelType> getLabelTypes() {
        return labels;
    }

    public LabelType getNextLabel(LabelType label) {
        int index = getLabelIndex(label);
        if(++index >= labels.size()) {
            index = 0;
        }
        LabelType labelType = labels.get(index);
        //专门为DONE标签优化
        if("DONE".equals(labelType.getName())) {
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            labelType = new AttrLabelType(labelType.getName(), "Closed at: "+time);
        }
        return labelType;
    }

    private int getLabelIndex(LabelType labelType) {
        Iterator<LabelType> iterator = labels.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            if(iterator.next().getName().equals(labelType.getName())){
                return i;
            }
            i++;
        }
        return -1;
    }

    public void addLabel(LabelType label) {
        labels.add(label);
        service.addLabelType(label);
    }

    public String getPath() {
        return path.get();
    }

    public void setPath(String path) {
        this.path.set(path);
        service.setPath(path);
    }

    public StringProperty pathProperty() {
        return path;
    }
}
