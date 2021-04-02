package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

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
    private ListView<?> order;

    @FXML
    private TextField totalTextField;

    @FXML
    private Button addOrderButton;

    private double basePrice, numDonuts;

    @FXML
    public void initialize() {

        // Type of Donut

        typesCB.getItems().add( "Yeast" );
        typesCB.getItems().add( "Cake Donut" );
        typesCB.getItems().add( "Donut Holes" );

        typesCB.getSelectionModel().select("Yeast");

        flavorLV.getItems().add( "Glazed" );
        flavorLV.getItems().add( "Choclate Frosted" );
        flavorLV.getItems().add( "Strawberry Frosted" );

        // Quantity Button

        for ( int i = 1; i <= 12; i++ ) {

            quantity.getItems().add( Integer.toString( i ) );

        }

        quantity.getSelectionModel().select("1" );

        basePrice = 1.39;

        numDonuts = 1;

        totalTextField.setText( String.format( Double.toString( basePrice * numDonuts ), "%.2f" ) );


        // ListView

    }

    @FXML
    void update(ActionEvent event) {


        if ( typesCB.getValue().equals( "Yeast" ) ) {

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
        else if ( typesCB.getValue().equals( "Donut Holes" ) ) {

            flavorLV.getItems().clear();

            flavorLV.getItems().add( "Jelly" );

            flavorLV.getItems().add( "Double Choclate" );

            flavorLV.getItems().add( "Vanilla Frosted" );

            basePrice = 0.33;

        }

        totalTextField.setText( String.format( Double.toString( basePrice * numDonuts ), "%.2f" ) );






    }

    @FXML
    void updateCost(ActionEvent event) {

        numDonuts = Double.parseDouble( quantity.getValue() );

        totalTextField.setText( String.format( Double.toString( basePrice * numDonuts ), "%.2f" ) );

    }


}

