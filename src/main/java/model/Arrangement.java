package model;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.label.LabelType;

import java.time.LocalDate;

/**
 * 事务类
 * 核心类
 * 定义了一个代办事项
 *
 * @author yohoyes
 */
public class Arrangement {
    private LabelType label = null;
    //相当于String,之所以用StringProperty,是为了跟界面的控件进行绑定
    private StringProperty value = null;
    //最后期限
    private long deadline = -1;
    private static final String DEFAULT_LABEL_NAME = "TODO";

    public Arrangement(String value) {
        this(new LabelType(DEFAULT_LABEL_NAME), value);
    }

    public Arrangement(LabelType label, String value) {
        this.label = label;
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

    public boolean hasDeadline() {
        return deadline != -1;
    }


    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }
}
