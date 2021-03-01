package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LabelType {
    private StringProperty name;

    public LabelType(String name) {
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
}
