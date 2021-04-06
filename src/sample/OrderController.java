package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Controller class for the Current Order GUI.
 * @author Prasanth Balaji, Aditya Dhawan
 */
public class OrderController {

    /**
     * Constant for the tax of New Jersey.
     */
    public static final double TAX = 0.0625;

    /**
     * Constants for indexing String arrays containing information regarding Donut and Coffee objects in their String representation.
     */
    public static final int INDEX_QTY = 0, INDEX_SIZE = 1, INDEX_FLAVOR = 1, INDEX_TYPE = 2, INDEX_PRICE_DONUT = 3, INDEX_ADDINS = 3, INDEX_PRICE_COFFEE = 4;

    /**
     * Constants for indexing String arrays containing information regarding Donut and Coffee objects in their String representation.
     */
    public static final int ITEM_ARR_DONUT = 4, ITEM_ARR_COFFEE = 5;

    /**
     * Instance field for the ListView containing the specific MenuItems of the current Order.
     */
    @FXML
    private ListView<String> orderLV;

    /**
     * Instance field for displaying the subtotal of the current Order.
     */
    @FXML
    private TextField subtotalField;

    /**
     * Instance field for displaying the tax on the current Order.
     */
    @FXML
    private TextField taxField;

    /**
     * Instance field for displaying the total of the current Order, calculated by adding subtotal and tax together.
     */
    @FXML
    private TextField totalField;

    /**
     * Instance field for the button that removes a selected MenuItem from the ListView of MenuItems.
     */
    @FXML
    private Button removeItemButton;

    /**
     * Instance field for the button that confirms the Order and places it, and closes the Curernt Order GUI.
     */
    @FXML
    private Button placeOrderButton;

    /**
     * Instance field for the main Controller class, meant to link both the main and this controller together to share information.
     */
    private Controller mainController;

    /**
     * Instance field for the current Order object, obtained from the main Controller class.
     */
    private Order currentOrder;

    /**
     * Setter method that links this OrderController to the main Controller class, and obtains the current Order object field from the main Controller class.
     * @param mainController - the main Controller class.
     */
    public void setMainController(Controller mainController) {
        this.mainController = mainController;
        this.currentOrder = mainController.getCurrentOrder();
        ArrayList<MenuItem> orderList = currentOrder.getList();
        initializeOrderListView(orderList);
    }

    /**
     * Initialization method for the ListView containing all the MenuItems of the current Order.
     * @param orderList - the list of MenuItems in the current Order.
     */
    public void initializeOrderListView(ArrayList<MenuItem> orderList) {
        for(int i = 0; i < orderList.size(); i ++) {
            orderLV.getItems().add(orderList.get(i).toString());
        }
        setTotals(orderList);
    }

    /**
     * Setter method for the subtotal, tax, and total TextFields of the GUI. These TextFields are set based on the subtotal of the Order, the tax on the Order, and subtotal + tax.
     * @param orderList - the list of MenuItems in the current Order.
     */
    public void setTotals(ArrayList<MenuItem> orderList) {
        //Calculate Subtotal

        double subTotal = currentOrder.orderPrice();

        /*
        for(int i = 0; i < orderList.size(); i ++) {
            subTotal += orderList.get(i).getPrice();
        }*/

        subtotalField.setText(String.format("%.2f", subTotal));

        //Calculate Tax
        double tax = subTotal * TAX;
        taxField.setText(String.format("%.2f", tax));

        //Calculate Total (Subtotal + Tax)
        double total = subTotal + tax;
        totalField.setText(String.format("%.2f", total));
        currentOrder.setTotalPrice(total);
    }

    /**
     * Initialization method for the GUI as a whole. Sets specific attributes so that they cannot be edited by the user.
     */
    @FXML
    public void initialize() {
        subtotalField.setEditable(false);
        taxField.setEditable(false);
        totalField.setEditable(false);
    }

    /**
     * Method to confirm and place an Order after the user is finished reviewing it. Calls to the main Controller to reinitialize current Order with a new number, and closes the Current Order GUI.
     * @param event - Specific event for when the Place Order button is pressed.
     */
    @FXML
    void placeOrder(ActionEvent event) {

        mainController.getStoreOrders().add( mainController.getCurrentOrder() );

        mainController.finalizePlaceOrder();

        Stage stage = (Stage) placeOrderButton.getScene().getWindow();
        stage.close();

    }

    /**
     * Method to remove a selected MenuItem from the ListView of MenuItems. If no MenuItem is selected in the ListView, an alert will show up, prompting the user to first select an item to remove.
     * @param event - Specific event for when the Remove button is pressed.
     */
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

            //System.out.println( "Original size: " + currentOrder.getList().size() );

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

            //System.out.println( "Add Ins: " + coffeeAddIns );

            Coffee coffeeItem = new Coffee(size);

            for ( int i = 0; i < addInsInfo.length; i++ ) {

                switch ( addInsInfo[ i ] ) {

                    case "cream" -> coffeeItem.add( "cream" );

                    case "syrup" -> coffeeItem.add( "syrup" );

                    case "milk" -> coffeeItem.add( "milk" );

                    case "caramel" -> coffeeItem.add("caramel");

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
