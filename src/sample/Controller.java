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
import java.util.ArrayList;

public class Controller {

    @FXML
    private VBox donut_img_button;

    @FXML
    private ImageView coffee_img_button;

    @FXML
    private ImageView order_img_button;

    @FXML
    private ImageView store_order_img_button;

    private Order currentOrder;

    private int orderNum;

    @FXML
    public void initialize() {
        orderNum = 1;
        currentOrder = new Order(orderNum);
    }

    public void addDonutsToOrder(ArrayList<MenuItem> donutsList) {
        for(int i = 0; i < donutsList.size(); i ++) {

        }
    }

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

            DonutController donutController = fxmlLoader.getController();
            donutController.setMainController(this);

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