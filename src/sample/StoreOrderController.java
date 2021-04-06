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

public class StoreOrderController {

    public static final int INIT = -1;

    public static final int INDEX_ORDER_NAME = 0, INDEX_ORDER_NUMBER = 1;

    @FXML
    private ListView<String> ordersLV;

    @FXML
    private Button removeButton;

    @FXML
    private ListView<String> menuItemLV;

    @FXML
    private TextArea outputTextArea;

    @FXML
    private Button exportButton;

    @FXML
    private Button returnButton;

    private Controller mainController;

    private StoreOrders storeOrders;





    public void setMainController(Controller mainController) {
        this.mainController = mainController;
        this.storeOrders = mainController.getStoreOrders();
        initializeOrderListView();
    }

    public void initializeOrderListView() {
        ArrayList<Order> orderList = storeOrders.getListOrders();
        for(Order order : orderList) {
            ordersLV.getItems().add(order.toString());
        }
    }

    @FXML
    public void initialize() {
        outputTextArea.setEditable(false);
    }

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

    @FXML
    void returnToMain( ActionEvent event ) {

        Stage stage = (Stage) returnButton.getScene().getWindow();
        stage.close();

    }


}