package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override//Creation de la fenetre de l'application
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("StartView.fxml"));
        primaryStage.setTitle("IMit");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }

    //Lancement de l'application
    public static void main(String[] args) {
        launch(args);
    }
}
