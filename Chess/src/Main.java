import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.GameView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        var loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/layouts/sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Chess");
        primaryStage.setScene(new Scene(root, 720, 750));
        GameView view = loader.getController();
        view.init();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
