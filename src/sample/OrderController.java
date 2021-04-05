package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class OrderController {

    public static final double TAX = 0.0625;

    public static final int INDEX_QTY = 0, INDEX_SIZE = 1, INDEX_FLAVOR = 1, INDEX_TYPE = 2, INDEX_PRICE_DONUT = 3, INDEX_ADDINS = 3, INDEX_PRICE_COFFEE = 4;

    public static final int ITEM_ARR_DONUT = 4, ITEM_ARR_COFFEE = 5;

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
        this.currentOrder = mainController.getCurrentOrder();
        ArrayList<MenuItem> orderList = currentOrder.getList();
        initializeOrderListView(orderList);
    }

    public void initializeOrderListView(ArrayList<MenuItem> orderList) {
        for(int i = 0; i < orderList.size(); i ++) {
            orderLV.getItems().add(orderList.get(i).toString());
        }
        setTotals(orderList);
    }

    public void setTotals(ArrayList<MenuItem> orderList) {
        //Calculate Subtotal
        double subTotal = 0;
        for(int i = 0; i < orderList.size(); i ++) {
            subTotal += orderList.get(i).getPrice();
        }
        subtotalField.setText(String.format("%.2f", subTotal));

        //Calculate Tax
        double tax = subTotal * TAX;
        taxField.setText(String.format("%.2f", tax));

        //Calculate Total (Subtotal + Tax)
        double total = subTotal + tax;
        totalField.setText(String.format("%.2f", total));
    }

    @FXML
    public void initialize() {
        subtotalField.setEditable(false);
        taxField.setEditable(false);
        totalField.setEditable(false);
    }

    @FXML
    void placeOrder(ActionEvent event) {

    }

    @FXML
    void removeItem(ActionEvent event) {
        String item = orderLV.getSelectionModel().getSelectedItem();
        if(item == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("No menu item selected.");
            a.show();
            return;
        }
        String[] itemArr = item.split(" ");
        //Is this Coffee or Donut?
        if(itemArr.length == ITEM_ARR_DONUT) { //donut
            String donutType = itemArr[INDEX_TYPE].replace("_", " ");
            String flavor = itemArr[INDEX_FLAVOR].replace("_", " ");
            int type = Donut.TYPE_YEAST;
            switch(donutType) {
                case "Yeast Donut" -> type = Donut.TYPE_YEAST;
                case "Cake Donut" -> type = Donut.TYPE_CAKE;
                case "Donut Hole" -> type = Donut.TYPE_HOLE;
            }
            Donut donutItem = new Donut(type, flavor);
            currentOrder.remove(donutItem);
        }
        else if(itemArr.length == ITEM_ARR_COFFEE) { //coffee
            String coffeeSize = itemArr[INDEX_SIZE];
            int size = Coffee.SIZE_SHORT;
            switch(coffeeSize) {
                case "Short" -> size = Coffee.SIZE_SHORT;
                case "Tall" -> size = Coffee.SIZE_TALL;
                case "Grande" -> size = Coffee.SIZE_GRANDE;
                case "Venti" -> size = Coffee.SIZE_VENTI;
            }
            String coffeeAddIns = itemArr[INDEX_ADDINS].replace("[", "");
            Coffee coffeeItem = new Coffee(size);
            if(coffeeAddIns.contains("cream")) {
                coffeeItem.add("cream");
            }
            if(coffeeAddIns.contains("syrup")) {
                coffeeItem.add("syrup");
            }
            if(coffeeAddIns.contains("milk")) {
                coffeeItem.add("milk");
            }
            if(coffeeAddIns.contains("caramel")) {
                coffeeItem.add("caramel");
            }
            if(coffeeAddIns.contains("whipped_cream")) {
                coffeeItem.add("whipped cream");
            }
            currentOrder.remove(coffeeItem);
        }
        else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Something went wrong (unexpected number of arguments).");
            a.show();
        }

        /*
        ONCE AN ITEM IS REMOVED FROM LIST, REMOVE IT FROM LISTVIEW (reflect it on GUI)
         */
    }

}
