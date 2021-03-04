package model.label.impl;

import model.label.LabelType;

public class NoAttrLabelType extends LabelType {

    public NoAttrLabelType(String name) {
        super(name);
    }

    @Override
    public boolean hasAttr() {
        return false;
    }
}
