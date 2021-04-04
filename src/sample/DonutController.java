package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert.AlertType;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DonutController {

    @FXML
    private ComboBox< String > typesCB;

    @FXML
    private ListView< String > flavorLV;

    @FXML
    private ComboBox< String > quantity;

    @FXML
    private Button add;

    @FXML
    private Button remove;

    @FXML
    private ListView<String> order;

    @FXML
    private TextField totalTextField;

    @FXML
    private Button addOrderButton;

    private Controller mainController;

    private double subTotal, basePrice, numDonuts;

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

    public void setMainController(Controller controller) {
        this.mainController = controller;
    }

    @FXML
    public void initialize() {

        totalTextField.setEditable(false);

        // Type of Donut

        typesCB.getItems().add( "Yeast Donut" );
        typesCB.getItems().add( "Cake Donut" );
        typesCB.getItems().add( "Donut Hole" );

        typesCB.getSelectionModel().select("Yeast Donut");

        flavorLV.getItems().add( "Glazed" );
        flavorLV.getItems().add( "Choclate Frosted" );
        flavorLV.getItems().add( "Strawberry Frosted" );

        // Quantity Button

        for ( int i = 1; i <= MAX_DONUTS; i++ ) {

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

    @FXML
    void update(ActionEvent event) {


        if ( typesCB.getValue().equals( "Yeast Donut" ) ) {

            flavorLV.getItems().clear();

            flavorLV.getItems().add( "Glazed" );
            flavorLV.getItems().add( "Choclate Frosted" );
            flavorLV.getItems().add( "Strawberry Frosted" );

            basePrice = YEAST_PRICE;

        }
        else if ( typesCB.getValue().equals( "Cake Donut" ) ) {

            flavorLV.getItems().clear();

            flavorLV.getItems().add( "Boston Kreme" );

            flavorLV.getItems().add( "Bavarian Kreme" );

            flavorLV.getItems().add( "Choclate Kreme" );

            basePrice = CAKE_PRICE;

        }
        else if ( typesCB.getValue().equals( "Donut Hole" ) ) {

            flavorLV.getItems().clear();

            flavorLV.getItems().add( "Jelly" );

            flavorLV.getItems().add( "Double Choclate" );

            flavorLV.getItems().add( "Vanilla Frosted" );

            basePrice = HOLE_PRICE;

        }
    }

    @FXML
    void updateCost(ActionEvent event) {
        numDonuts = Double.parseDouble( quantity.getValue() );
    }

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

    @FXML
    void addToOrder(ActionEvent event) {
        ArrayList<MenuItem> donutsList = new ArrayList<MenuItem>();
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
                System.out.println(donut.getPrice());

                donutsList.add( donut );

            }
        }

        mainController.addDonutsToOrder(donutsList);
    }

}

