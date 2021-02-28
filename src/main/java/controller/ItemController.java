package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ItemController {

    @FXML
    private ImageView editBut;

    @FXML
    private ImageView delBut;

    @FXML
    private Label label;

    @FXML
    private Label content;

    @FXML
    public void initialize() {
        Image editImage = new Image("file:src/main/resources/img/edit.png");
        Image delImage = new Image("file:src/main/resources/img/del.png");
        delBut.setImage(delImage);
        editBut.setImage(editImage);
    }
}
