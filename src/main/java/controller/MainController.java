package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;

public class MainController {

    @FXML
    private AnchorPane BasePane;

    @FXML
    private VBox arrangements;

    @FXML
    private ImageView butAdd;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private void addButtonAction() {
        butAdd.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY) {
                AnchorPane item = loadItem();
                addItemButtonAction(item);
                arrangements.getChildren().add(item);
            }
        });
    }

    private void addItemButtonAction(AnchorPane item) {
        ImageView editBut = (ImageView)item.lookup("#editBut");
        ImageView delBut = (ImageView)item.lookup("#delBut");

        editBut.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY) {
                System.out.println("Clicked editBut");
            }
        });

        delBut.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY) {
                System.out.println("Clicked delBut");
                arrangements.getChildren().remove(item);
            }
        });
    }

    private AnchorPane loadItem()  {
        URL resource = this.getClass().getClassLoader().getResource("item.fxml");
        AnchorPane item = null;
        try {
            item = FXMLLoader.load(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return item;
    }

    @FXML
    public void initialize() {
        addButtonAction();
    }
}
