package model.label;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 事务标签
 *
 * @author yohoyes
 */
public class LabelType {
    private StringProperty name;
    private String attr = "";

    public LabelType(){}

    public LabelType(String name) {
        this.name = new SimpleStringProperty(name);
        this.attr = name;
    }

    public LabelType(String name, String attr) {
        this.name = new SimpleStringProperty(name);
        this.attr = attr;
    }

    public String getName() {
        return name.getValue();
    }

    public StringProperty getNameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public String getAttr() {
        return attr;
    }
}
