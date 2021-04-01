package sample;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import javafx.scene.input.MouseEvent;

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