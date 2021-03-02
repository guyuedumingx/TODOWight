package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Arrangement;
import model.Setting;
import service.history.ArrangementService;
import service.history.impl.XmlArrangementService;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * 主页面的控制器
 * @author yohoyes
 */
public class MainController {

    @FXML
    private AnchorPane BasePane;

    @FXML
    private VBox arrangements;

    @FXML
    private Button butAdd;

    @FXML
    private Button butSet;

    @FXML
    private ScrollPane scrollPane;

    private ArrangementService service = new XmlArrangementService(Setting.getSetting().getPath());
    private static List<Arrangement> arrangementList = null;

    public static void remove(Arrangement arrangement) {
        arrangementList.remove(arrangement);
    }

    /**
     * 添加main界面的事件绑定
     */
    @FXML
    private void addButtonAction() {
        butAdd.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY) {
                Arrangement arrangement = new Arrangement("Show Something Here");
                AnchorPane item = loadItem(arrangement,true);
                arrangements.getChildren().add(0,item);
                arrangementList.add(arrangement);
            }
        });
        butSet.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY) {
                System.out.println("touch setting");
                AnchorPane settingPane = loadSetting();
            }
        });
    }

    public void init(Stage stage) {
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                service.save(arrangementList);
            }
        });
    }

    private AnchorPane loadSetting() {
        URL resource = this.getClass().getClassLoader().getResource("setting.fxml");
        Stage stage = new Stage();
        AnchorPane set = null;
        SettingController controller = null;

        try{
            FXMLLoader loader = new FXMLLoader(resource);
            set = loader.load();
            controller = loader.getController();
            controller.init(stage);
        }catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Setting");
        stage.setScene(new Scene(set, 400, 600));
        stage.show();
        return set;
    }

    /**
     * 加载列表项
     */
    private AnchorPane loadItem(Arrangement arrangement, boolean showEditor)  {
        URL resource = this.getClass().getClassLoader().getResource("item2.fxml");
        AnchorPane item = null;
        ItemController itemController = null;

        try {
            FXMLLoader loader = new FXMLLoader(resource);
            item = loader.load();
            itemController = loader.getController();

            itemController.init(item, arrangements, showEditor, arrangement);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return item;
    }

    @FXML
    public void initialize() {
        addButtonAction();
        arrangementList = service.read();
        for (Arrangement arrangement : arrangementList) {
            AnchorPane item = loadItem(arrangement,false);
            arrangements.getChildren().add(0,item);
        }
    }
}
