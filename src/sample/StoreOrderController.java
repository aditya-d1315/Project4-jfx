package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
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

        }
    }

    @FXML
    void exportToFile(ActionEvent event) {

    }
}