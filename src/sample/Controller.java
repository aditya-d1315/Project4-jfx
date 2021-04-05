package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    @FXML
    private TextArea outputTextArea;

    @FXML
    private VBox donut_img_button;

    @FXML
    private ImageView coffee_img_button;

    @FXML
    private ImageView order_img_button;

    @FXML
    private ImageView store_order_img_button;

    private Order currentOrder;

    private OrderController orderController;

    private int orderNum;

    public void setOrderController(OrderController orderController) {
        this.orderController = orderController;
    }

    @FXML
    public void initialize() {
        orderNum = 1;
        currentOrder = new Order(orderNum);
        outputTextArea.setEditable(false);
        outputTextArea.appendText("Started.\n");
    }

    public void addDonutsToOrder(ArrayList<MenuItem> donutsList) {

        for(int i = 0; i < donutsList.size(); i ++) {

            currentOrder.add( donutsList.get( i ) );

        }

        currentOrder.orderPrice();

        outputTextArea.appendText("Donuts have been added to order.\n");

    }

    public void addCoffeeToOrder(ArrayList<MenuItem> coffeeList) {
        for(int i = 0; i < coffeeList.size(); i ++) {
            currentOrder.add(coffeeList.get(i));
        }

        currentOrder.orderPrice();

        outputTextArea.appendText("Coffee has been added to the order.\n");
    }

    @FXML
    void selectCoffee(MouseEvent event) {
        //System.out.println("Coffee");

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Coffee.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root1));

            stage.setTitle( "Order Coffee" );

            stage.show();

            CoffeeController coffeeController = fxmlLoader.getController();
            coffeeController.setMainController(this);

        } catch ( IOException e ) {

        }


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
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Order.fxml"));
            Parent root1 = (Parent)(fxmlLoader.load());

            OrderController orderController = fxmlLoader.getController();
            orderController.setMainController(this);

            Stage stage = new Stage();
            stage.setScene(new Scene(root1));

            stage.setTitle("Current Order");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showStoreOrderHistory(MouseEvent event) {
        //System.out.println("Order history.");
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }
}