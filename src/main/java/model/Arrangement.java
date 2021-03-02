package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 事务类
 * 核心类
 *
 * @author yohoyes
 */
public class Arrangement {
    private LabelType label = null;
    private StringProperty value = null;
    private static final String DEFAULT_LABEL_NAME = "TODO";

    public Arrangement(String value) {
        this(DEFAULT_LABEL_NAME, value);
    }

    public Arrangement(String label, String value) {
        this.label = new LabelType(label);
        this.value = new SimpleStringProperty(value);
    }

    public LabelType getLabel() {
        return label;
    }

    public void setLabel(LabelType label) {
        this.label = label;
    }

    public String getValue() {
        return value.getValue();
    }

    public StringProperty getValueProperty() {
        return value;
    }

    public void setValue(String value) {
        this.value.setValue(value);
    }
}
