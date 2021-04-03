package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert.AlertType;

import java.net.URL;
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

    private double subTotal, basePrice, numDonuts;

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

        for ( int i = 1; i <= 12; i++ ) {

            quantity.getItems().add( Integer.toString( i ) );

        }

        quantity.getSelectionModel().select("1" );

        subTotal = 0;

        basePrice = 1.39;

        numDonuts = 1;

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

            basePrice = 1.39;

        }
        else if ( typesCB.getValue().equals( "Cake Donut" ) ) {

            flavorLV.getItems().clear();

            flavorLV.getItems().add( "Boston Kreme" );

            flavorLV.getItems().add( "Bavarian Kreme" );

            flavorLV.getItems().add( "Choclate Kreme" );

            basePrice = 1.59;

        }
        else if ( typesCB.getValue().equals( "Donut Hole" ) ) {

            flavorLV.getItems().clear();

            flavorLV.getItems().add( "Jelly" );

            flavorLV.getItems().add( "Double Choclate" );

            flavorLV.getItems().add( "Vanilla Frosted" );

            basePrice = 0.33;

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
            double numDonuts = Double.parseDouble(quantity.getValue());

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
            double numDonuts = Double.parseDouble(itemArr[0]);
            String donutType = itemArr[1];

            switch(donutType) {
                case "Yeast Donut" -> subTotal -= (1.39 * numDonuts);
                case "Cake Donut" -> subTotal -= (1.59 * numDonuts);
                case "Donut Hole" -> subTotal -= (0.33 * numDonuts);
                default -> {
                    Alert a = new Alert(AlertType.ERROR);
                    a.setContentText("Something went wrong.");
                    a.show();
                }
            }

            order.getItems().remove(item);
            totalTextField.setText(String.format("%.2f", subTotal));
        }
    }

    @FXML
    void addToOrder(ActionEvent event) {

    }

}

