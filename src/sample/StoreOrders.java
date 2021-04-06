package sample;

import java.util.ArrayList;

/**
 * A class that keeps the list of orders placed by the user
 */

public class StoreOrders implements Customizable {

    /**
     * A record of orders for a specific user
     */

    ArrayList< Order > listOrders;

    public StoreOrders()
    {

        listOrders = new ArrayList<>();

    }

    @Override
    public boolean add(Object obj) {

        if ( obj instanceof Order )
            return listOrders.add( ( Order ) obj );
        else
            return false;

    }

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

    public ArrayList<Order> getListOrders() {
        return listOrders;
    }
}