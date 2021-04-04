package sample;

/**
 * Class to hold general information on items on the menu.
 * @author Prasanth Balaji, Aditya Dhawan
 */
public class MenuItem {

    /**
     * Instance variable to hold the price of the MenuItem.
     */
    private double price;

    /**
     * Default constructor to create a new MenuItem.
     */
    public MenuItem() {}

    /**
     * Method to calculate the price of this MenuItem. This method will be empty in the superclass, and overridden in the subclasses.
     */
    public void itemPrice() {}

    /**
     * Setter method to set the price of the MenuItem
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Getter method to obtain the price of the MenuItem.
     * @return the price.
     */
    public double getPrice() {
        return price;
    }

}