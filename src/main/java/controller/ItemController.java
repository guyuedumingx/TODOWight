package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Arrangement;
import model.LabelType;
import model.Setting;
import java.io.IOException;
import java.net.URL;

/**
 * 选项卡的控制器
 * 代表着一条具体的代办事项
 * @author yohoyes
 */
public class ItemController {

    /**
     * 编辑按钮
     */
    @FXML
    private Button editBut;

    /**
     * 该界面所有控件的公有父类
     */
    @FXML
    private HBox parentBox;

    /**
     * 删除按键
     */
    @FXML
    private Button delBut;

    /**
     * 代办事项左侧的标签
     */
    @FXML
    private Label label;

    /**
     * 代办事项的内容
     */
    @FXML
    private Label content;

    /**
     * 操作的具体代办事项
     */
    private Arrangement arrangement = null;

    /**
     * 自定义的初始化方法
     * @param arrangements 代办事项的容器
     * @param showEditor 添加代办的时候选择是否显示编辑窗口
     * @param arrangement 具体的代办事项
     */
    public void init(BorderPane item, VBox arrangements, boolean showEditor, Arrangement arrangement) {
        this.arrangement = arrangement;
        if(showEditor) {
            AnchorPane editorPane = loadEditor();
            TextArea editArea = (TextArea) editorPane.lookup("#editArea");
            editArea.selectAll();

        }
        //绑定代办的内容
        content.textProperty().bind(arrangement.getValueProperty());
        //绑定代办的标签
        label.textProperty().bind(arrangement.getLabel().getNameProperty());
        addItemButtonAction(item, arrangements);
    }

    @FXML
    public void initialize() {
        content.prefWidthProperty().bind(parentBox.widthProperty());
    }

    public void addItemButtonAction(BorderPane item, VBox arrangements) {
        Button editBut = (Button)item.lookup("#editBut");
        Button delBut = (Button) item.lookup("#delBut");

        //编辑按钮的事件
        editBut.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY) {
                AnchorPane editorPane = loadEditor();
                TextArea editArea = (TextArea) editorPane.lookup("#editArea");
                editArea.positionCaret(editArea.getText().length());
            }
        });

        //删除按键的事件
        delBut.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY) {
                arrangements.getChildren().remove(item);
                MainController.remove(arrangement);
            }
        });

        //标签的点击事件
        label.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY) {
                LabelType nextLabel = Setting.getSetting().getNextLabel(arrangement.getLabel());
                arrangement.setLabel(nextLabel);
                label.textProperty().bind(arrangement.getLabel().getNameProperty());
            }
        });

        //内容的点击事件
        content.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY) {
                BorderPane anchorPane = loadDetail();
            }
        });
    }

    /**
     * 加载编辑界面
     */
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

    /**
     * 加载详情页面
     */
    private BorderPane loadDetail() {
        URL resource = this.getClass().getClassLoader().getResource("detail3.fxml");
        Stage stage = new Stage();
        BorderPane root = null;
        try {
            FXMLLoader loader = new FXMLLoader(resource);
            root = loader.load();
            DetailController controller = (DetailController) loader.getController();
            controller.init(label,arrangement);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Detail");
        stage.setScene(new Scene(root, 600, 800));
        stage.show();
        return root;
    }

}
