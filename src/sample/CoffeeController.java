package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 * Controller class for the Coffee GUI.
 * @author Prasanth Balaji, Aditya Dhawan
 */
public class CoffeeController {

    /**
     * Constants for initialization and the max number of Coffee objects a user can order at a time.
     */
    public static final int ONE = 1, MAX_COFFEE = 6;

    /**
     * Instance field for the CheckBox for the cream add-in.
     */
    @FXML
    private CheckBox creamBox;

    /**
     * Instance field for the CheckBox for the milk add-in.
     */
    @FXML
    private CheckBox milkBox;

    /**
     * Instance field for the CheckBox for the whipped cream add-in.
     */
    @FXML
    private CheckBox whipcreamBox;

    /**
     * Instance field for the CheckBox for the syrup add-in.
     */
    @FXML
    private CheckBox syrupBox;

    /**
     * Instance field for the CheckBox for the caramel add-in.
     */
    @FXML
    private CheckBox caramelBox;

    /**
     * Instance field for the ComboBox containing options for the size of the Coffee.
     */
    @FXML
    private ComboBox< String > sizeCB;

    /**
     * Instance field for the ComboBox containing options for the quantity of Coffee desired.
     */
    @FXML
    private ComboBox< String > quantityCB;

    /**
     * Instance field for the TextField that displays the subtotal of the Coffee being added to the Order.
     */
    @FXML
    private TextField subTotalField;

    /**
     * Instance field for the button that adds the specified Coffee to the Order.
     */
    @FXML
    private Button orderButton;

    /**
     * Instance field for the main Controller class, meant to link both the main and this controller together to share information.
     */
    private Controller mainController;

    /**
     * Instance field for the Coffee object that gets modified using the GUI.
     */
    private Coffee coffee;

    /**
     * Instance field for the subtotal of the Coffee being ordered.
     */
    private double subTotal;

    /**
     * Instance field for the number of Coffee objects being ordered, modified based on the ComboBox for the quantity.
     */
    private int numCoffee;

    /**
     * Setter method that links this CoffeeController class to the main Controller class.
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

    /**
     * Method for when the user presses the button to add the specified Coffee to the Order. This will send the specified number of Coffee objects to the main Controller, and close the Coffee GUI.
     * @param event - Specific event for when the user presses the Add to Order button.
     */
    @FXML
    void addToOrder(ActionEvent event) {
        coffee.itemPrice();
        ArrayList<MenuItem> coffeeList = new ArrayList<MenuItem>();
        for(int i = 1; i <= Integer.parseInt(quantityCB.getValue()); i ++) {
            coffeeList.add(coffee);
        }

        mainController.addCoffeeToOrder(coffeeList);

        Stage stage = (Stage)(orderButton.getScene().getWindow());
        stage.close();
    }

    /**
     * Method for when the user toggles the CheckBox for caramel. Based on the status of the CheckBox, this will either add or remove the add-in.
     * @param event - Specific event for when the user toggles this CheckBox.
     */
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

    /**
     * Method for when the user toggles the CheckBox for cream. Based on the status of the CheckBox, this will either add or remove the add-in.
     * @param event - Specific event for when the user toggles this CheckBox.
     */
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

    /**
     * Method for when the user toggles the CheckBox for milk. Based on the status of the CheckBox, this will either add or remove the add-in.
     * @param event - Specific event for when the user toggles this CheckBox.
     */
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

    /**
     * Method for when the user toggles the CheckBox for syrup. Based on the status of the CheckBox, this will either add or remove the add-in.
     * @param event - Specific event for when the user toggles this CheckBox.
     */
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

    /**
     * Method for when the user toggles the CheckBox for whipped cream. Based on the status of the CheckBox, this will either add or remove the add-in.
     * @param event - Specific event for when the user toggles this CheckBox.
     */
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

    /**
     * Method for when the user selects a specific quantity of Coffee to order from the quantity ComboBox. This will apply a multiplier to the current subtotal, and redisplay it.
     * @param event - Specific event for when the user selects an option from the quantity ComboBox.
     */
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

    /**
     * Method for when the user selects a specific size for the Coffee from the size ComboBox. This will alter the subtotal based on the prices for different sizes.
     * @param event - Specific event for when the user selects an option from the size ComboBox.
     */
    @FXML
    void updateSize(ActionEvent event) {
        double sizeCost = 0;
        String sizeCoffee = sizeCB.getValue();

        switch(sizeCoffee) {
            case "Short":
                sizeCost = Coffee.SHORT_PRICE;
                coffee.setSize(Coffee.SIZE_SHORT);
                break;
            case "Tall":
                sizeCost = Coffee.TALL_PRICE;
                coffee.setSize(Coffee.SIZE_TALL);
                break;
            case "Grande":
                sizeCost = Coffee.GRANDE_PRICE;
                coffee.setSize(Coffee.SIZE_GRANDE);
                break;
            case "Venti":
                sizeCost = Coffee.VENTI_PRICE;
                coffee.setSize(Coffee.SIZE_VENTI);
                break;
        }

        double addInCost = 0;
        for(int i = 0; i < Coffee.NUM_ADD_INS; i ++) {
            if(coffee.getAddIns()[i]) {
                addInCost += Coffee.ADDIN_PRICE;
            }
        }

        subTotal = numCoffee * (sizeCost + addInCost);
        subTotalField.setText(String.format("%.2f", subTotal));
    }

}

