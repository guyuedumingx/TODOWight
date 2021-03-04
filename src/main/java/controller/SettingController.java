package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.label.LabelType;
import model.Setting;

/**
 * 设置页面的控制器
 *
 * @author yohoyes
 */
public class SettingController {

    @FXML
    private Button pushBut;

    @FXML
    private Button pathBut;

    @FXML
    private TextField nameField;

    @FXML
    private TextField attrField;

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
        //设置界面定义标签的确定按键
        pushBut.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY) {
                String name = nameField.getText();
                String text = attrField.getText();
                //如果是空字符串，则不用添加为标签
                if(!"".equals(name)) {
                    LabelType label = new LabelType(name, text);
                    Setting.getSetting().addLabel(label);
                }
            }
        });
        //选择路径作为资源路径的按键
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
