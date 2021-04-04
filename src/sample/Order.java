package sample;

import java.util.ArrayList;

/**
 * A class that has a unique order number and keeps the list of menu items added by the user
 * @author Prasanth Balaji, Aditya Dhawan
 */

public class Order implements Customizable {

    /**
     * Unique order number
     */

    private int number;

    /**
     * Total price for the order
     */

    private double totalPrice;

    /**
     * List of MenuItems
     */

    private ArrayList< MenuItem > list;

    public Order( int number ) {

        this.number = number;

        this.list = new ArrayList<MenuItem>();

        this.totalPrice = 0;

    }

    @Override
    public boolean add(Object obj) {

        if ( obj instanceof Coffee )
            return list.add( ( Coffee ) obj );
        else if ( obj instanceof Donut )
            return list.add( ( Donut ) obj );
        else
            return false;

    }

    @Override
    public boolean remove(Object obj) {

        if ( obj instanceof Coffee )
            return list.remove( ( Coffee ) obj );
        else if ( obj instanceof Donut )
            return list.remove( ( Donut ) obj );
        else
            return false;

    }

    public ArrayList< MenuItem > getList() {
        return list;
    }

    public void orderPrice() {


        for ( int i = 0; i < list.size(); i++ ) {

            this.totalPrice += list.get( i ).getPrice();

        }

    }

    public double getTotalPrice() {
        return totalPrice;
    }
}