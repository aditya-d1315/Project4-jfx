package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main method setting the stage for the GUI.
 * @author Prasanth Balaji, Aditya Dhawan
 */


public class Main extends Application {

    /**
     * Overriding the start() method of Application
     * @param primaryStage - the main window of the GUI.
     * @throws Exception - thrown for when the GUI cannot be launched.
     */

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainmenu.fxml"));
        primaryStage.setTitle("Café #213");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    /**
     * Main method of the class
     * @param args - Used to start the GUI.
     */

    public static void main(String[] args) {
        launch(args);
    }
}