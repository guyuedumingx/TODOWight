package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EditorController {

    @FXML
    private ImageView pushBut;

    @FXML
    private Label editArea;

    @FXML
    public void initialize() {
        pushBut.setImage(new Image("file:src/main/resources/img/send.png"));
    }
}
