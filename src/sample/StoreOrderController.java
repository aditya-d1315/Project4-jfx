package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

/**
 * Controller class for the StoreOrder GUI.
 * @author Prasanth Balaji, Aditya Dhawan
 */
public class StoreOrderController {

    /**
     * Constants for indexing the String arrays used to read in an Order's name and number.
     */
    public static final int INDEX_ORDER_NAME = 0, INDEX_ORDER_NUMBER = 1;

    /**
     * Instance field for the ListView containing the different Orders as items.
     */
    @FXML
    private ListView<String> ordersLV;

    /**
     * Instance field for the button used to remove items from the ListView containing Orders.
     */
    @FXML
    private Button removeButton;

    /**
     * Instance field for the ListView containing the MenuItems for a specified Order.
     */
    @FXML
    private ListView<String> menuItemLV;

    /**
     * Instance field for the TextArea at the bottom of the screen, meant to display results of certain actions.
     */
    @FXML
    private TextArea outputTextArea;

    /**
     * Instance field for the button used to export all the information of all the Orders to a text file.
     */
    @FXML
    private Button exportButton;

    /**
     * Instance field for the button used to close the StoreOrder GUI and return to the Main Menu GUI.
     */
    @FXML
    private Button returnButton;

    /**
     * Instance field for the main Controller class, meant to link both the main and this controller together to share information.
     */
    private Controller mainController;

    /**
     * Instance field for the StoreOrders object, obtained from the main Controller class.
     */
    private StoreOrders storeOrders;

    /**
     * Setter method that links this StoreOrderController class to the main Controller class, and obtains the storeOrders field from the main Controller class.
     * @param mainController - the main Controller class.
     */
    public void setMainController(Controller mainController) {
        this.mainController = mainController;
        this.storeOrders = mainController.getStoreOrders();
        initializeOrderListView();
    }

    /**
     * Initialization method for the ListView, populating it with the different Orders that the user has placed.
     */
    public void initializeOrderListView() {
        ArrayList<Order> orderList = storeOrders.getListOrders();
        for(Order order : orderList) {
            ordersLV.getItems().add(order.toString());
        }
    }

    /**
     * Initialize method for the GUI as a whole. Sets specific attributes so that they cannot be edited by the user.
     */
    @FXML
    public void initialize() {
        outputTextArea.setEditable(false);
    }

    /**
     * Method to display the specific items in a specific order on the ListView of MenuItems. Display happens when the user clicks on an item in the ListView of Orders.
     * @param event - Specific event for when the user clicks on the ListView of Orders.
     */
    @FXML
    void displayOrderInfo(MouseEvent event) {
        String selectedOrder = ordersLV.getSelectionModel().getSelectedItem();
        if(selectedOrder != null) {
            String[] orderArr = selectedOrder.split("\t");
            String[] orderNumArr = orderArr[INDEX_ORDER_NAME].split("#");
            int orderNumber = Integer.parseInt(orderNumArr[INDEX_ORDER_NUMBER]);

            ArrayList<Order> orderList = storeOrders.getListOrders();
            Order currentOrder = null;
            for(Order order : orderList) {
                if(order.getNumber() == orderNumber) {
                    currentOrder = order;
                    break;
                }
            }

            if(currentOrder != null) {
                menuItemLV.getItems().clear();
                for(MenuItem item : currentOrder.getList()) {
                    menuItemLV.getItems().add(item.toString());
                }
            }
        }
    }

    /**
     * Method to remove a selected Order in the ListView of orders. If an item isn't selected when the user hits the button, GUI displays an Alert, telling the user to select an Order on the ListView first.
     * @param event - Specific event for when the user presses the Remove buton.
     */
    @FXML
    void removeOrder(ActionEvent event) {
        String selectedOrder = ordersLV.getSelectionModel().getSelectedItem();
        if(selectedOrder == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("No order selected.");
            a.show();
            return;
        }
        else {

            // Remove selected item from storeOrder

            String[] orderArr = selectedOrder.split("\t");
            String[] orderNumArr = orderArr[INDEX_ORDER_NAME].split("#");
            int orderNumber = Integer.parseInt(orderNumArr[INDEX_ORDER_NUMBER]);

            storeOrders.remove( new Order( orderNumber ) );

            ordersLV.getItems().remove( ordersLV.getSelectionModel().getSelectedItem() );

            menuItemLV.getItems().clear();

            outputTextArea.appendText( "Order# " + orderNumber + " has been successfully removed.\n" );

        }
    }

    /**
     * Method to export the contents of all Orders to a text file.
     * @param event - Specific event for when the user presses the Export to File button.
     */
    @FXML
    void exportToFile(ActionEvent event) {

        OutputStream os;



        try {

            os = new FileOutputStream( new File( "receipt.txt") );

            for ( int i = 0; i < storeOrders.listOrders.size(); i++ ) {

                //System.out.println( "Order Number: " + storeOrders.listOrders.get( i ).getNumber() );
                //System.out.println( "\n\n\n" );

                try {

                    os.write( ( storeOrders.getListOrders().get( i ).toString() ).getBytes() );

                    os.write( ( "\n").getBytes() );

                    for ( int j = 0; j < storeOrders.listOrders.get( i ).getList().size(); j++ ) {



                        os.write( ( "\t" + storeOrders.getListOrders().get( i ).getList().get( j ) ).getBytes() );


                        os.write( ( "\n" ).getBytes() );


                    }

                    os.write( ( "\n\n\n").getBytes() );

                } catch ( IOException e ) {
                    e.printStackTrace();
                }

            }

            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        outputTextArea.appendText( "Exported successfully.\n" );



    }

    /**
     * Method for when the user wishes to exit the StoreOrder GUI and return to the Main Menu GUI. Closes this window.
     * @param event - Specific event for when the user presses the Return to Main button.
     */
    @FXML
    void returnToMain( ActionEvent event ) {

        Stage stage = (Stage) returnButton.getScene().getWindow();
        stage.close();

    }


}