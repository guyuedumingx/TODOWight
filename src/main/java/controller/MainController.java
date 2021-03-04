package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
    private BorderPane BasePane;

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
        //添加代办的按键的事件绑定
        butAdd.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY) {
                Arrangement arrangement = new Arrangement(Setting.ARRANGEMENT_DEFAULT_TEXT);
                BorderPane item = loadItem(arrangement,true);
                arrangements.getChildren().add(0,item);
                arrangementList.add(arrangement);
            }
        });
        //进入设置界面的按键的事件绑定
        butSet.setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.PRIMARY) {
                AnchorPane settingPane = loadSetting();
            }
        });
    }

    /**
     * 初始化main界面
     * @param stage 传入的窗口对象
     */
    public void init(Stage stage) {
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                service.save(arrangementList);
            }
        });
    }

    /**
     * 加载设置界面
     */
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
     * 加载的每一条具体代办
     */
    private BorderPane loadItem(Arrangement arrangement, boolean showEditor)  {
        URL resource = this.getClass().getClassLoader().getResource("item2.fxml");
        BorderPane item = null;
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

    /**
     * javafx 控制类的自动初始化函数
     * 在javafx自己生成MainController的时候会自动调用
     * 之所以还要存在init()方法的原因是initialize在初始化
     * 时还没有初始化界面的控件，导致如果使用控件会报空指针
     * 异常，而自定义的init()方法在initialize方法之后执行
     * ，不会造成该问题
     */
    @FXML
    public void initialize() {
        addButtonAction();
        //将代办容器的宽度与外部滚动面板的宽度绑定
        arrangements.prefWidthProperty().bind(scrollPane.widthProperty());
        //从文件中读取已有的代办
        arrangementList = service.read();
        //把返回的代办加载到页面中
        for (Arrangement arrangement : arrangementList) {
            BorderPane item = loadItem(arrangement,false);
            arrangements.getChildren().add(0,item);
        }
    }
}
