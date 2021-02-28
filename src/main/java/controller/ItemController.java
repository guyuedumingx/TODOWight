package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Arrangement;

public class ItemController {

    @FXML
    private ImageView editBut;

    @FXML
    private ImageView delBut;

    @FXML
    private Label label;

    @FXML
    private Label content;

    public void init(Arrangement arrangement) {
        label.setText(arrangement.getLabel().getName());
        content.setText(arrangement.getValue());
    }

    @FXML
    public void initialize() {
        Image editImage = new Image("file:src/main/resources/img/edit.png");
        Image delImage = new Image("file:src/main/resources/img/del.png");
        delBut.setImage(delImage);
        editBut.setImage(editImage);
    }
}
