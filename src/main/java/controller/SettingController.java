package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.LabelType;
import model.Setting;
public class SettingController {

    @FXML
    private Button pushBut;

    @FXML
    private Button pathBut;

    @FXML
    private TextField nameField;

    @FXML
    private TextField pathField;
    private Setting setting = Setting.getSetting();

    @FXML
    public void initialize() {
        addButtonAction();
        pathField.textProperty().bindBidirectional(setting.pathProperty());
    }

    public void init(Stage stage) {
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                setting.setPath(pathField.getText());
            }
        });
    }

    public void addButtonAction() {
        pushBut.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY) {
                String name = nameField.getText();
                if(!"".equals(name)) {
                    LabelType label = new LabelType(name);
                    Setting.getSetting().addLabel(label);
                }
            }
        });
        pathBut.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY) {
                DirectoryChooser chooser = new DirectoryChooser();
                chooser.setTitle("Select a path");
                String url = chooser.showDialog(new Stage()).getPath();
                pathField.setText(url);
            }
        });
    }
}
