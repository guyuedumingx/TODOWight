package model.label.impl;

import model.label.LabelType;

public class AttrLabelType extends LabelType {
    private String attr = "";

    public AttrLabelType(String name) {
        super(name);
    }

    public AttrLabelType(String name, String attr) {
        this(name);
        this.attr = attr;
    }

    @Override
    public boolean hasAttr() {
        return true;
    }

    @Override
    public String getAttr() {
        return attr;
    }
}
