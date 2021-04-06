package sample;

import java.util.ArrayList;

/**
 * A class that has a unique order number and keeps the list of menu items added by the user.
 * @author Prasanth Balaji, Aditya Dhawan
 */

public class Order implements Customizable {

    /**
     * Unique order number.
     */

    private int number;

    /**
     * Total price for the order.
     */

    private double totalPrice;

    /**
     * List of MenuItems.
     */

    private ArrayList< MenuItem > list;

    /**
     * Constructor for a new Order object.
     * @param number -> The corresponding number for an instance of an order.
     */

    public Order( int number ) {

        this.number = number;

        this.list = new ArrayList<MenuItem>();

        this.totalPrice = 0;

    }

    /**
     * Overriding the toString() method for the Order object.
     * @return -> String in the format of "Order # < number >   < price >".
     */

    @Override
    public String toString() {
        //Order #1  <price.00>
        return "Order #" + number + "\t$" + String.format("%.2f", totalPrice);
    }

    /**
     * Adding a specific instance of a MenuItem to the list of MenuItems.
     * @param obj -> The object to add.
     * @return -> true if the object is an instance of Coffee or Donut ( which extends MenuItem ) and false otherwise.
     */

    @Override
    public boolean add(Object obj) {

        if ( obj instanceof Coffee )
            return list.add( ( Coffee ) obj );
        else if ( obj instanceof Donut )
            return list.add( ( Donut ) obj );
        else
            return false;

    }

    /**
     * Removing a specific instance of a MenuItem from the list of MenuItems.
     * @param obj -> The object to remove.
     * @return -> true if the object is an instance of Coffee or Donut ( which extends MenuItem ) and false otherwise.
     */

    @Override
    public boolean remove(Object obj) {

        if ( obj instanceof Coffee )
            return list.remove( ( Coffee ) obj );
        else if ( obj instanceof Donut )
            return list.remove( ( Donut ) obj );
        else
            return false;

    }

    /**
     * Getter method for the list of MenuItems.
     * @return -> the list of MenuItems.
     */

    public ArrayList< MenuItem > getList() {
        return list;
    }

    /**
     * Method to calculate the price of all the elements in the list of MenuItems.
     * @return -> the price of all the elements in the list.
     */
    public double orderPrice() {

        this.totalPrice = 0;
        for ( int i = 0; i < list.size(); i++ ) {

            this.totalPrice += list.get( i ).getPrice();

        }

        return this.totalPrice;

    }

    /**
     * Setter method to set the total price of the Order, which accounts for sales tax.
     * @param totalPrice -> The total price of all the elements in the list.
     */

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Getter method for the specific number corresponding to the Order.
     * @return -> The number associated with the Order.
     */

    public int getNumber() { return number; }

    /**
     * Getter method for the total price of the Order.
     * @return -> The total price of all the elements in the list.
     */

    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Overriding the equals() method for the Order object, defining equality based on the associated number.
     * @param obj -> The other Order object, sent in as a generic Object.
     * @return -> true if the two objects are equal, and false otherwise.
     */

    @Override
    public boolean equals(Object obj) {

        if ( obj instanceof Order ) {

            Order object = ( Order ) obj;

            return this.number == object.getNumber();
        }

        return false;

    }
}