package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Arrangement;

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
                AnchorPane item = loadItem(new Arrangement("google"));
                arrangements.getChildren().add(item);
            }
        });
    }

    private AnchorPane loadItem(Arrangement arrangement)  {
        URL resource = this.getClass().getClassLoader().getResource("item.fxml");
        AnchorPane item = null;
        ItemController itemController = null;

        try {
            FXMLLoader loader = new FXMLLoader(resource);
            item = loader.load();
            itemController = loader.getController();

            addItemButtonAction(item, arrangement);
            itemController.init(arrangement);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return item;
    }

    private void addItemButtonAction(AnchorPane item, Arrangement arrangement) {
        ImageView editBut = (ImageView)item.lookup("#editBut");
        ImageView delBut = (ImageView)item.lookup("#delBut");

        editBut.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY) {
                AnchorPane editor = loadEditor();
            }
        });

        delBut.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY) {
                arrangements.getChildren().remove(item);
            }
        });
    }

    private AnchorPane loadEditor() {
        URL resource = this.getClass().getClassLoader().getResource("editor.fxml");
        Stage stage = new Stage();
        AnchorPane root = null;
        try {
            root = FXMLLoader.load(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("代办事项");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
        return root;
    }

    @FXML
    public void initialize() {
        addButtonAction();
    }
}
