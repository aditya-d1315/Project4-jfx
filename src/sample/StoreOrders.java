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

        if ( obj instanceof Order )
            return listOrders.remove( ( Order ) obj );
        else
            return false;

    }
}