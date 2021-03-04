package model.label;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 事务标签
 *
 * @author yohoyes
 */
public abstract class LabelType {
    private StringProperty name;

    public LabelType(){}

    public LabelType(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public LabelType(String name, String attr) {
        this.name = new SimpleStringProperty(name);
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

    public abstract boolean hasAttr();

    public String getAttr() {
        return "";
    }
}
