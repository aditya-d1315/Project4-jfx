package sample;

import java.util.ArrayList;

/**
 * Class defining the ADT StoreOrders, which contains the list of Orders made by the user and has functionality to
 * add and remove Orders.
 * @author Prasanth Balaji, Aditya Dhawan
 */

public class StoreOrders implements Customizable {

    /**
     * A list of Orders for a specific user.
     */

    ArrayList< Order > listOrders;

    /**
     * Default constructor to create a new StoreOrder object.
     */

    public StoreOrders()
    {

        listOrders = new ArrayList<>();

    }

    /**
     * Method to add an Order to the list of Orders for a specific user.
     * @param obj The object to add.
     * @return -> true if the object is an instance of Order, false otherwise.
     */

    @Override
    public boolean add(Object obj) {

        if ( obj instanceof Order )
            return listOrders.add( ( Order ) obj );
        else
            return false;

    }

    /**
     * Method to remove an Order from the list of Order for a specific user.
     * @param obj The object to remove.
     * @return -> true if the object is an instance of Order, false otherwise.
     */

    @Override
    public boolean remove(Object obj) {

        if ( obj instanceof Order ) {

            Order object = ( Order ) obj;

            for ( int i = 0; i < listOrders.size(); i++ ) {

                if ( listOrders.get( i ).getNumber() == object.getNumber() ) {

                    listOrders.remove( object );

                    return true;

                }


            }

            return false;
        }
        else
            return false;

    }

    /**
     * Getter method to retrieve the list of Orders for a specific user.
     * @return -> The list of Orders for a specific user.
     */

    public ArrayList<Order> getListOrders() {
        return listOrders;
    }
}