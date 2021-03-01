package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import model.Arrangement;
import model.LabelType;
import model.Setting;

import java.util.List;

public class DetailController {
    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TextArea content;

    private Arrangement arrangement = null;

    public void init(Arrangement arrangement) {
        this.arrangement = arrangement;
        content.textProperty().bindBidirectional(arrangement.getValueProperty());
        List<LabelType> labelTypes = Setting.getSetting().getLabelTypes();
        fillChoiceBox(labelTypes);
    }

    @FXML
    private void fillChoiceBox (List<LabelType> labelTypes) {
        for(LabelType label : labelTypes) {
            choiceBox.getItems().add(label.getName());
        }
        choiceBox.getSelectionModel().select(arrangement.getLabel().getName());
        choiceBox.getSelectionModel().selectedItemProperty().addListener(new
             ChangeListener<String>() {
                 @Override
                 public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        arrangement.getLabel().setName(newValue);
                 }
             }
        );
    }

    @FXML
    public void initialize() {
    }
}
