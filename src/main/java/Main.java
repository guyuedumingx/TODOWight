import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        URL resource = Main.class.getClassLoader().getResource("main.fxml");
        Parent root = FXMLLoader.load(resource);
        primaryStage.setTitle("代办事项");
        primaryStage.setScene(new Scene(root, 924, 622));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
