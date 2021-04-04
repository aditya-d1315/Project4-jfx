package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CoffeeController {

    @FXML
    private CheckBox creamBox;

    @FXML
    private CheckBox milkBox;

    @FXML
    private CheckBox whipcreamBox;

    @FXML
    private CheckBox syrupBox;

    @FXML
    private CheckBox caramelBox;

    @FXML
    private ComboBox< String > sizeCB;

    @FXML
    private ComboBox< String > quantityCB;

    @FXML
    private TextField subTotalField;

    @FXML
    private Button orderButton;

    private Controller mainController;

    private double subTotal;

    private int numCoffee;

    public static final int ONE = 1, MAX_COFFEE = 6;

    public void setMainController(Controller controller) {
        this.mainController = controller;
    }

    @FXML
    public void initialize() {

        // Subtotal setup

        subTotalField.setEditable( false );

        // Size setup

        sizeCB.getItems().add( "Short" );
        sizeCB.getItems().add( "Tall" );
        sizeCB.getItems().add( "Grande" );
        sizeCB.getItems().add( "Venti" );

        sizeCB.getSelectionModel().select( "Short" );

        // Quantity setup

        for ( int i = ONE; i <= MAX_COFFEE; i++ ) {

            quantityCB.getItems().add( Integer.toString( i ) );

        }

        quantityCB.getSelectionModel().select( "1" );

        subTotal = 0;

        numCoffee = ONE;

        subTotalField.setText( String.format( "%.2f", 0 ) );

    }

    @FXML
    void addToOrder(ActionEvent event) {



    }

    @FXML
    void cbAction(ActionEvent event) {

    }

    @FXML
    void mbAction(ActionEvent event) {

    }

    @FXML
    void sbAction(ActionEvent event) {

    }

    @FXML
    void updateQuantity(ActionEvent event) {

    }

    @FXML
    void updateSize(ActionEvent event) {

    }

    @FXML
    void wcbAction(ActionEvent event) {

    }

}

