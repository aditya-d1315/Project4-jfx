package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 * Controller class for the Donut GUI.
 * @author Prasanth Balaji, Aditya Dhawan
 */
public class DonutController {

    /**
     * Constants for donut type.
     */
    public static final int TYPE_YEAST = 0, TYPE_CAKE = 1, TYPE_HOLE = 2;

    /**
     * Initialization constant
     */
    public static final int INIT = 1;

    /**
     * Constants for indexing arrays.
     */
    public static final int QUANTITY_INDEX = 0, TYPE_INDEX = 1, FLAVOR_INDEX = 2;

    /**
     * Constant for max quantity of donuts to add at a given time
     */
    public static final int MAX_DONUTS = 12;

    /**
     * Constants for base prices of donuts.
     */
    public static final double YEAST_PRICE = 1.39, CAKE_PRICE = 1.59, HOLE_PRICE = 0.33;

    /**
     * Instance field for the ComboBox containing options for the types of Donuts.
     */
    @FXML
    private ComboBox< String > typesCB;

    /**
     * Instance field for the ListView that displays the different flavors offered for different types of Donuts.
     */
    @FXML
    private ListView< String > flavorLV;

    /**
     * Instance field for the ComboBox containing options for the quantity of Donuts, with 12 being the maximum.
     */
    @FXML
    private ComboBox< String > quantity;

    /**
     * Instance field for the ListView containing the Donuts that the user wishes to add to the current Order.
     */
    @FXML
    private ListView<String> order;

    /**
     * Instance field for the TextField that displays the subtotal for the Donuts that the user adds to the queue.
     */
    @FXML
    private TextField totalTextField;

    /**
     * Instance field for the button for adding the specified Donuts to the current Order.
     */
    @FXML
    private Button addOrderButton;

    /**
     * Instance field for the main Controller class, meant to link both the main and this controller together to share information.
     */
    private Controller mainController;

    /**
     * Instance fields for the subtotal of the Donuts, the base price of a specified type of Donut, and the number of Donuts specified.
     */
    private double subTotal, basePrice, numDonuts;

    /**
     * Setter method that links this DonutController class to the main Controller class.
     * @param controller - the main Controller class.
     */
    public void setMainController(Controller controller) {
        this.mainController = controller;
    }

    /**
     * Initialization method for the GUI as a whole. Sets specific attributes so that they cannot be edited by the user, and initializes instance fields to default values.
     */
    @FXML
    public void initialize() {

        totalTextField.setEditable(false);

        // Type of Donut

        typesCB.getItems().add( "Yeast Donut" );
        typesCB.getItems().add( "Cake Donut" );
        typesCB.getItems().add( "Donut Hole" );

        typesCB.getSelectionModel().select("Yeast Donut");

        flavorLV.getItems().add( "Glazed" );
        flavorLV.getItems().add( "Chocolate Frosted" );
        flavorLV.getItems().add( "Strawberry Frosted" );

        // Quantity Button

        for ( int i = INIT; i <= MAX_DONUTS; i++ ) {

            quantity.getItems().add( Integer.toString( i ) );

        }

        quantity.getSelectionModel().select("1" );

        subTotal = 0;

        basePrice = YEAST_PRICE;

        numDonuts = INIT;

        totalTextField.setText( String.format("%.2f", 0.00) );


        // ListView
        order.getItems().clear();

    }

    /**
     * Method that updates the subtotal and flavor choices based on the Donut type that the user specifies in the ComboBox for Donut type.
     * @param event - Specific event for when the user selects an option in the ComboBox for Donut type.
     */
    @FXML
    void update(ActionEvent event) {


        if ( typesCB.getValue().equals( "Yeast Donut" ) ) {

            flavorLV.getItems().clear();

            flavorLV.getItems().add( "Glazed" );
            flavorLV.getItems().add( "Chocolate Frosted" );
            flavorLV.getItems().add( "Strawberry Frosted" );

            basePrice = YEAST_PRICE;

        }
        else if ( typesCB.getValue().equals( "Cake Donut" ) ) {

            flavorLV.getItems().clear();

            flavorLV.getItems().add( "Boston Kreme" );

            flavorLV.getItems().add( "Bavarian Kreme" );

            flavorLV.getItems().add( "Chocolate Kreme" );

            basePrice = CAKE_PRICE;

        }
        else if ( typesCB.getValue().equals( "Donut Hole" ) ) {

            flavorLV.getItems().clear();

            flavorLV.getItems().add( "Jelly" );

            flavorLV.getItems().add( "Double Chocolate" );

            flavorLV.getItems().add( "Vanilla Frosted" );

            basePrice = HOLE_PRICE;

        }
    }

    /**
     * Method that updates the subtotal based on the new quantity of Donuts that the user selects in the quantity ComboBox.
     * @param event - Specific event for when the user selects an option in the quantity ComboBox.
     */
    @FXML
    void updateCost(ActionEvent event) {
        numDonuts = Double.parseDouble( quantity.getValue() );
    }

    /**
     * Method that adds a specific Donut with a quantity, type, and flavor to the queue, so that it can be added to the current Order later, or removed from the queue later.
     * @param event - Specific event for when the user presses the Add button.
     */
    @FXML
    void addToListView(ActionEvent event) {
        if(flavorLV.getSelectionModel().getSelectedItem() == null) {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText("Flavor not selected.");
            a.show();
        }
        else {
            String donutType = typesCB.getValue();
            String flavor = flavorLV.getSelectionModel().getSelectedItem();

            //<numDonuts>,<donutType>,<flavor>
            String item = numDonuts + "," + donutType + "," + flavor;
            order.getItems().add(item);
            subTotal += basePrice * numDonuts;
            totalTextField.setText( String.format("%.2f", subTotal ));
        }
    }

    /**
     * Method for when the user wishes to remove what they added previously from the queue, so that it doesn't get included in the current Order later on.
     * @param event - Specific event for when the user presses the Remove button.
     */
    @FXML
    void removeFromListView(ActionEvent event) {
        String item = order.getSelectionModel().getSelectedItem();
        if(item == null) {
            Alert a = new Alert(AlertType.ERROR);
            a.setContentText("No Donut item selected.");
            a.show();
        }
        else {
            //<numDonuts>,<donutType>,<flavor>
            String[] itemArr = item.split(",");
            double numDonuts = Double.parseDouble(itemArr[QUANTITY_INDEX]);
            String donutType = itemArr[TYPE_INDEX];

            switch(donutType) {
                case "Yeast Donut" -> subTotal -= (YEAST_PRICE * numDonuts);
                case "Cake Donut" -> subTotal -= (CAKE_PRICE * numDonuts);
                case "Donut Hole" -> subTotal -= (HOLE_PRICE * numDonuts);
                default -> {
                    Alert a = new Alert(AlertType.ERROR);
                    a.setContentText("Something went wrong (Subtracting from subTotal).");
                    a.show();
                }
            }

            order.getItems().remove(item);
            totalTextField.setText(String.format("%.2f", subTotal));
        }
    }

    /**
     * Method for when the user confirms whatever Donut items they want. This will send a list of Donuts to the main Controller to add to the current Order, and closes the Donut GUI. If no items are added to the queue, an alert will show, prompting the user to make selections before adding to the Order.
     * @param event - Specific event for when the user presses the Add to Order button.
     */
    @FXML
    void addToOrder(ActionEvent event) {
        ArrayList<MenuItem> donutsList = new ArrayList<MenuItem>();

        // Checking if no order was made

        if ( order.getItems().size() == 0 ) {

            Alert a = new Alert(AlertType.ERROR);
            a.setContentText("No selections were made");
            a.show();
            return;

        }

        for(int i = 0; i < order.getItems().size(); i ++) {
            String[] itemArr = order.getItems().get(i).split(",");
            int numDonuts = (int)(Double.parseDouble(itemArr[QUANTITY_INDEX]));
            int donutType = 0;
            switch(itemArr[TYPE_INDEX]) {
                case "Yeast Donut" -> donutType = TYPE_YEAST;
                case "Cake Donut" -> donutType = TYPE_CAKE;
                case "Donut Hole" -> donutType = TYPE_HOLE;
                default -> {
                    Alert a = new Alert(AlertType.ERROR);
                    a.setContentText("Something went wrong (Assigning donut type when adding to order).");
                    a.show();
                }
            }
            String donutFlavor = itemArr[FLAVOR_INDEX];

            for(int j = 0; j < numDonuts; j ++) {
                //donutsList.add(new Donut(donutType, donutFlavor));

                Donut donut = new Donut( donutType, donutFlavor );
                donut.itemPrice();

                donutsList.add( donut );

            }
        }

        mainController.addDonutsToOrder(donutsList);

        Stage stage = (Stage) addOrderButton.getScene().getWindow();
        stage.close();
    }

}

