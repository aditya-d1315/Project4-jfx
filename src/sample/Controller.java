package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOError;
import java.io.IOException;

public class Controller {

    @FXML
    private VBox donut_img_button;

    @FXML
    private ImageView coffee_img_button;

    @FXML
    private ImageView order_img_button;

    @FXML
    private ImageView store_order_img_button;

    @FXML
    void selectCoffee(MouseEvent event) {
        //System.out.println("Coffee");
    }

    @FXML
    void selectDonut(MouseEvent event) {
        //System.out.println("Donut");

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Donut.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root1));

            stage.setTitle( "Order Donuts" );

            stage.show();

        } catch ( IOException e ) {

        }
    }

    @FXML
    void showCurrentOrder(MouseEvent event) {
        //System.out.println("Current order.");
    }

    @FXML
    void showStoreOrderHistory(MouseEvent event) {
        //System.out.println("Order history.");
    }
}