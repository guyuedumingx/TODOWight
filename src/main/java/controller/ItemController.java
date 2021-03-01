package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Arrangement;
import model.LabelType;
import model.Setting;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class ItemController {

    @FXML
    private Button editBut;

    @FXML
    private Button delBut;

    @FXML
    private Label label;

    @FXML
    private Label content;

    private Arrangement arrangement = null;

    public void init(AnchorPane item, VBox arrangements, boolean showEditor, Arrangement arrangement) {
        this.arrangement = arrangement;
        if(showEditor) {
            loadEditor();
        }
        content.textProperty().bind(arrangement.getValueProperty());
        label.textProperty().bind(arrangement.getLabel().getNameProperty());
        addItemButtonAction(item, arrangements);
    }

    @FXML
    public void initialize() {
    }

    public void addItemButtonAction(AnchorPane item, VBox arrangements) {
        Button editBut = (Button)item.lookup("#editBut");
        Button delBut = (Button) item.lookup("#delBut");

        editBut.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY) {
                AnchorPane editor = loadEditor();
            }
        });

        delBut.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY) {
                arrangements.getChildren().remove(item);
                MainController.remove(arrangement);
            }
        });

        label.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY) {
                LabelType nextLabel = Setting.getSetting().getNextLabel(arrangement.getLabel());
                arrangement.setLabel(nextLabel);
                label.textProperty().bind(arrangement.getLabel().getNameProperty());
            }
        });

        content.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY) {
                AnchorPane anchorPane = loadDetail();
            }
        });
    }

    private AnchorPane loadEditor() {
        URL resource = this.getClass().getClassLoader().getResource("editor.fxml");
        Stage stage = new Stage();
        AnchorPane root = null;
        try {
            FXMLLoader loader = new FXMLLoader(resource);
            root = loader.load();
            EditorController controller = (EditorController)loader.getController();
            controller.init(arrangement);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Editor");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
        return root;
    }

    private AnchorPane loadDetail() {
        URL resource = this.getClass().getClassLoader().getResource("detail.fxml");
        Stage stage = new Stage();
        AnchorPane root = null;
        try {
            FXMLLoader loader = new FXMLLoader(resource);
            root = loader.load();
            DetailController controller = (DetailController) loader.getController();
            controller.init(arrangement);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Detail");
        stage.setScene(new Scene(root, 400, 800));
        stage.show();
        return root;
    }

}
