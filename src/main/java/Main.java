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

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        URL resource = Main.class.getClassLoader().getResource("main.fxml");
        FXMLLoader loader = new FXMLLoader(resource);
        Parent root = loader.load();
        MainController controller = (MainController) loader.getController();
        controller.init(primaryStage);
        primaryStage.setTitle("代办事项");
        primaryStage.setScene(new Scene(root, 924, 622));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
