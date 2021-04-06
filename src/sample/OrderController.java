package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.StringTokenizer;

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

        mainController.getStoreOrders().add( mainController.getCurrentOrder() );

        mainController.finalizePlaceOrder();

        Stage stage = (Stage) placeOrderButton.getScene().getWindow();
        stage.close();

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

            // Removing instance from listview

            orderLV.getItems().remove( orderLV.getSelectionModel().getSelectedItem() );

            setTotals( currentOrder.getList() );



        }
        else if(itemArr.length == ITEM_ARR_COFFEE) { //coffee

            System.out.println( "Original size: " + currentOrder.getList().size() );

            String coffeeSize = itemArr[INDEX_SIZE];
            int size = Coffee.SIZE_SHORT;
            switch(coffeeSize) {
                case "Short" -> size = Coffee.SIZE_SHORT;
                case "Tall" -> size = Coffee.SIZE_TALL;
                case "Grande" -> size = Coffee.SIZE_GRANDE;
                case "Venti" -> size = Coffee.SIZE_VENTI;
            }

            String coffeeAddIns = itemArr[INDEX_ADDINS].replace("[", "").replace( "]", "" );

            String[] addInsInfo = coffeeAddIns.split( "," );

            System.out.println( "Add Ins: " + coffeeAddIns );

            Coffee coffeeItem = new Coffee(size);

            for ( int i = 0; i < addInsInfo.length; i++ ) {

                switch ( addInsInfo[ i ] ) {

                    case "cream" -> coffeeItem.add( "cream" );

                    case "syrup" -> coffeeItem.add( "syrup" );

                    case "milk" -> coffeeItem.add( "milk" );

                    case "whipped_cream" -> coffeeItem.add( "whipped cream" );

                }

            }

            currentOrder.remove(coffeeItem);

            // Removing instance from listview

            orderLV.getItems().remove( orderLV.getSelectionModel().getSelectedItem() );

            setTotals( currentOrder.getList() );
        }
        else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Something went wrong (unexpected number of arguments).");
            a.show();
        }

    }

}
