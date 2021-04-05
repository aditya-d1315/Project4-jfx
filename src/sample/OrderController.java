package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class OrderController {

    @FXML
    private ListView<String> orderLV;

    @FXML
    private TextField subtotalField;

    @FXML
    private TextField taxField;

    @FXML
    private TextField totalField;

    @FXML
    private Button removeItemButton;

    @FXML
    private Button placeOrderButton;

    private Controller mainController;

    private Order currentOrder;

    public void setMainController(Controller mainController) {
        this.mainController = mainController;
    }

    public void setCurrentOrder(Order order) {
        this.currentOrder = order;
    }


    @FXML
    public void initialize() {
        subtotalField.setEditable(false);
        taxField.setEditable(false);
        totalField.setEditable(false);

        ArrayList<MenuItem> orderList = currentOrder.getList();
        for(int i = 0; i < orderList.size(); i ++) {
            orderLV.getItems().add(orderList.get(i).toString());
        }
    }

    @FXML
    void placeOrder(ActionEvent event) {

    }

    @FXML
    void removeItem(ActionEvent event) {

    }

}
