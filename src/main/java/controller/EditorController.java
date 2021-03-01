package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Arrangement;

public class EditorController {

    @FXML
    private ImageView pushBut;

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
        pushBut.setImage(new Image("file:src/main/resources/img/send.png"));
        addItemButtonAction();
    }
}
