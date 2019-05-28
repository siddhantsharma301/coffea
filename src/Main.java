import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Font.loadFont(Main.class.getResource("/fonts/Inter-SemiBold.otf").toExternalForm(), 12);
        Font.loadFont(Main.class.getResource("/fonts/Inter-ExtraBold.otf").toExternalForm(), 12);
        Font.loadFont(Main.class.getResource("/fonts/Inter-SemiBoldItalic.otf").toExternalForm(), 12);
        Font.loadFont(Main.class.getResource("/fonts/Inter-Bold.otf").toExternalForm(), 12);


        Parent root = FXMLLoader.load(getClass().getResource("resources/fxml/Coffea.fxml"));
        primaryStage.setTitle("Coffea");
        primaryStage.setScene(new Scene(root, 960, 540));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("resources/img/coffee-cup.png")));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}