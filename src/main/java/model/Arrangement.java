package model;

public class Arrangement {
    private LabelType label = null;
    private String value = null;
    private static final String DEFAULT_LABEL_NAME = "TODO";

    public Arrangement(String value) {
        this(DEFAULT_LABEL_NAME, value);
    }

    public Arrangement(String label, String value) {
        this.label = new LabelType(label);
        this.value = value;
    }

    public LabelType getLabel() {
        return label;
    }

    public void setLabel(LabelType label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
