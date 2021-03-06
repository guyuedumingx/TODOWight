import controller.MainController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Setting;

import java.net.URL;

/**
 * 主类 程序入口
 *
 * @author yohoyes
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        URL resource = Main.class.getClassLoader().getResource("main.fxml");
        FXMLLoader loader = new FXMLLoader(resource);
        Parent root = loader.load();
        MainController controller = (MainController) loader.getController();
        controller.init(primaryStage);
        primaryStage.setTitle(Setting.APPLICATION_NAME);
        primaryStage.setScene(new Scene(root, 924, 622));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
