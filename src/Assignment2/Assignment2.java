package Assignment2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Assignment2 extends Application {

    public static void main(String[] args) {
        Controller controller = new Controller();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Gui.fxml"));
        primaryStage.setTitle("Character Transfer (Reader/Writer)");
        primaryStage.setScene(new Scene(root,600,400));
        primaryStage.show();
    }
}
