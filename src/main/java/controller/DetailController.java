package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseButton;
import model.Arrangement;
import model.LabelType;
import model.Setting;
import java.util.List;

/**
 * 详情页面的控制器
 * @author yohoyes
 */
public class DetailController {
    @FXML
    private Button editBut;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TextArea content;

    private Arrangement arrangement = null;
    private Label itemLabel = null;

    public void init(Label itemLabel, Arrangement arrangement) {
        this.itemLabel = itemLabel;
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
                     arrangement.setLabel(getLabelsByName(labelTypes,newValue));
                     itemLabel.textProperty().bind(arrangement.getLabel().getNameProperty());
                 }
             }
        );
    }

    private LabelType getLabelsByName(List<LabelType> labelTypes, String name) {
        for(LabelType label : labelTypes) {
            if(name.equals(label.getName())) {
                return label;
            }
        }
        return null;
    }

    @FXML
    public void addEditButtonAction() {
        editBut.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY) {
                if(content.isEditable()) {
                    content.setEditable(false);
                }else {
                    content.setEditable(true);
                }
            }
        });
    }

    @FXML
    public void initialize() {
        content.setWrapText(true);
        addEditButtonAction();
    }
}
