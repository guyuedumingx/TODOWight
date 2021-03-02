package controller;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import model.LabelType;
import model.Setting;

import java.util.Locale;

public class SettingController {

    @FXML
    private Button pushBut;

    @FXML
    private TextField nameField;

    @FXML
    public void initialize() {
        addButtonAction();
    }

    public void addButtonAction() {
        pushBut.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY) {
                String name = nameField.getText();
                if(!"".equals(name)) {
                    LabelType label = new LabelType(name);
                    Setting.getSetting().addLabel(label);
                }
            }
        });
    }
}
