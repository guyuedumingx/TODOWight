package model;

import service.properties.PropertiesService;
import service.properties.impl.XmlPropertiesService;
import java.util.List;

public class Setting {
    private static String PROPERTIES_FILE_NAME = "properties.xml";
    private static Setting properties = new Setting();
    private List<LabelType> labels = null;
    private PropertiesService service = null;

    private Setting() {
        init();
    }

    public void init() {
        service = new XmlPropertiesService(PROPERTIES_FILE_NAME);
        labels = service.readLabelType();
    }

    public static Setting getSetting() {
        return properties;
    }

    public List<LabelType> getLabelTypes() {
        return labels;
    }

    public void addLabel(LabelType label) {
        labels.add(label);
        service.addLabelType(label);
    }
}
