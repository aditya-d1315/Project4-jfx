package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Main controller defining the functionality of the main window's GUI and storing vital information used for the entire program.
 * @author Prasanth Balaji, Aditya Dhawan
 */

public class Controller {

    /**
     * Constant used to set the order number of the first order.
     */

    public static final int FIRST_ORDER = 1;

    /**
     * Instance field for the TextArea to perform as a console for the user.
     */

    @FXML
    private TextArea outputTextArea;

    /**
     * An instance of the current order the user makes.
     */

    private Order currentOrder;

    /**
     * An instance of the list of orders the user makes using the ADT StoreOrders.
     */

    private StoreOrders storeOrders;

    /**
     * An attribute to keep track of the order number for each order the user creates.
     */

    private int orderNum;

    /**
     * Initialization method for the main's GUI.
     */

    @FXML
    public void initialize() {
        orderNum = FIRST_ORDER;
        currentOrder = new Order(orderNum);
        storeOrders = new StoreOrders();
        outputTextArea.setEditable(false);
        outputTextArea.appendText("Started.\n");
    }

    /**
     * Method to add the Donut objects chosen by the user to the currentOrder attribute.
     * @param donutsList -> The list of Donut objects selected by the user.
     */

    public void addDonutsToOrder(ArrayList<MenuItem> donutsList) {

        for(int i = 0; i < donutsList.size(); i ++) {

            currentOrder.add( donutsList.get( i ) );

        }

        currentOrder.orderPrice();

        outputTextArea.appendText("Donuts have been added to order.\n");

    }

    /**
     * Method to add the Coffee objects chosen by the user to the currentOrder attribute.
     * @param coffeeList -> The list of Coffee objects selected by the user.
     */

    public void addCoffeeToOrder(ArrayList<MenuItem> coffeeList) {
        for(int i = 0; i < coffeeList.size(); i ++) {
            currentOrder.add(coffeeList.get(i));
        }

        currentOrder.orderPrice();

        outputTextArea.appendText("Coffee has been added to the order.\n");
    }

    /**
     * Reinitializing the currentOrder attribute and incrementing the order number, once an order is placed, to create a new instance
     * of the current order for the user.
     */

    public void finalizePlaceOrder() {
        //Initialize new order
        orderNum ++;
        currentOrder = new Order( orderNum );
        outputTextArea.appendText( "Order has been placed. \n" );
    }


    /**
     * Method to setup the Coffee GUI once the Coffee option is clicked through the main GUI.
     * @param event
     */

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

    /**
     * Method to setup the Donut GUI once the Donut option is clicked through the main GUI.
     * @param event
     */

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

    /**
     * Method to setup the Current Order GUI once the Current Order option is clicked through the main GUI.
     * @param event
     */

    @FXML
    void showCurrentOrder(MouseEvent event) {
        //System.out.println("Current order.");

        if ( currentOrder.getList().size() == 0 ) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Order is currently empty");
            a.show();
            return;
        }

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

        }
    }

    /**
     * Method to setup the Show Order GUI once the Show Order option is clicked through the main GUI.
     * @param event
     */

    @FXML
    void showStoreOrderHistory(MouseEvent event) {
        //System.out.println("Order history.");

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StoreOrders.fxml"));
            Parent root1 = (Parent)(fxmlLoader.load());

            StoreOrderController storeOrderController = fxmlLoader.getController();
            storeOrderController.setMainController(this);

            Stage stage = new Stage();
            stage.setScene(new Scene(root1));

            stage.setTitle("Current Order");
            stage.show();
        } catch (IOException e) {

        }

    }

    /**
     * Getter method for the current order of the user, represented through the ADT Order.
     * @return -> The currentOrder attribute.
     */

    public Order getCurrentOrder() {
        return currentOrder;
    }

    /**
     * Getter method for the list of orders the user has made, represented through the ADT StoreOrders.
     * @return -> The storeOrders attribute.
     */

    public StoreOrders getStoreOrders() { return storeOrders; }
}