package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import model.Arrangement;

public class EditorController {

    @FXML
    private Button pushBut;

    @FXML
    private TextArea editArea;

    private Arrangement arrangement = null;

    public void init(Arrangement arrangement) {
        this.arrangement = arrangement;
        editArea.textProperty().bindBidirectional(arrangement.getValueProperty());
    }

    @FXML
    public void addItemButtonAction() {
        pushBut.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY) {
                Stage stage = (Stage)editArea.getScene().getWindow();
                stage.close();
            }
        });
    }

    @FXML
    public void initialize() {
        addItemButtonAction();
    }
}
