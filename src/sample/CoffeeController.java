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

    private Coffee coffee;

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

        numCoffee = ONE;

        subTotal = Coffee.SHORT_PRICE * numCoffee;

        subTotalField.setText( String.format( "%.2f", subTotal ) );

        coffee = new Coffee(Coffee.SIZE_SHORT);

    }

    @FXML
    void addToOrder(ActionEvent event) {

    }

    @FXML
    void caAction(ActionEvent event) {
        //caramel
        if(!coffee.getAddIns()[Coffee.CARAMEL]) {
            //if false, add add-in
            coffee.add("caramel");
            subTotal += Coffee.ADDIN_PRICE;
        }
        else {
            //if true, remove add-in
            coffee.remove("caramel");
            subTotal -= Coffee.ADDIN_PRICE;
        }

        subTotalField.setText(String.format("%.2f", subTotal));
    }

    @FXML
    void cbAction(ActionEvent event) {
        //cream
        if(!coffee.getAddIns()[Coffee.CREAM]) {
            //if false, add add-in
            coffee.add("cream");
            subTotal += Coffee.ADDIN_PRICE;
        }
        else {
            //if true, remove add-in
            coffee.remove("cream");
            subTotal -= Coffee.ADDIN_PRICE;
        }

        subTotalField.setText(String.format("%.2f", subTotal));
    }

    @FXML
    void mbAction(ActionEvent event) {
        //milk
        if(!coffee.getAddIns()[Coffee.MILK]) {
            //if false, add add-in
            coffee.add("milk");
            subTotal += Coffee.ADDIN_PRICE;
        }
        else {
            //if true, remove add-in
            coffee.remove("milk");
            subTotal -= Coffee.ADDIN_PRICE;
        }

        subTotalField.setText(String.format("%.2f", subTotal));
    }

    @FXML
    void sbAction(ActionEvent event) {
        //syrup
        if(!coffee.getAddIns()[Coffee.SYRUP]) {
            //if false, add add-in
            coffee.add("syrup");
            subTotal += Coffee.ADDIN_PRICE;
        }
        else {
            //if true, remove add-in
            coffee.remove("syrup");
            subTotal -= Coffee.ADDIN_PRICE;
        }

        subTotalField.setText(String.format("%.2f", subTotal));
    }

    @FXML
    void wcbAction(ActionEvent event) {
        //whipped cream
        if(!coffee.getAddIns()[Coffee.WHIPPED_CREAM]) {
            //if false, add add-in
            coffee.add("whipped cream");
            subTotal += Coffee.ADDIN_PRICE;
        }
        else {
            //if true, remove add-in
            coffee.remove("whipped cream");
            subTotal -= Coffee.ADDIN_PRICE;
        }

        subTotalField.setText(String.format("%.2f", subTotal));
    }

    @FXML
    void updateQuantity(ActionEvent event) {
        numCoffee = Integer.parseInt(quantityCB.getValue());

        double addInCost = 0;
        for(int i = 0; i < Coffee.NUM_ADD_INS; i ++) {
            if(coffee.getAddIns()[i]) {
                addInCost += Coffee.ADDIN_PRICE;
            }
        }

        double sizeCost = 0;
        switch(sizeCB.getValue()) {
            case "Short" -> sizeCost = Coffee.SHORT_PRICE;
            case "Tall" -> sizeCost = Coffee.TALL_PRICE;
            case "Grande" -> sizeCost = Coffee.GRANDE_PRICE;
            case "Venti" -> sizeCost = Coffee.VENTI_PRICE;
        }

        subTotal = numCoffee * (sizeCost + addInCost);
        subTotalField.setText(String.format("%.2f", subTotal));
    }

    @FXML
    void updateSize(ActionEvent event) {

    }

}

