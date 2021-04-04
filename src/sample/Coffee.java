package sample;

/**
 * Subclass to hold information on a Coffee item.
 * @author Prasanth Balaji, Aditya Dhawan
 */
public class Coffee extends MenuItem implements Customizable {

    /**
     * Integer representation of size:
     * 0: short
     * 1: tall
     * 2: grande
     * 3: venti
     */
    private int size;

    /**
     * Boolean array representation of the add-ins to an order of coffee.
     */
    private boolean[] addIns;

    /**
     * Constants relating to coffee size.
     */
    public static final int SIZE_SHORT = 0, SIZE_TALL = 1, SIZE_GRANDE = 2, SIZE_VENTI = 3, NUM_ADD_INS = 5;

    /**
     * Constants relating to the indices of the addIns array
     */

    public static final int CREAM = 0, SYRUP = 1, MILK = 2, CARAMEL = 3, WHIPPED_CREAM = 4;

    /**
     * Constants relating to the price for each specific size of coffee
     */

    public static final double SHORT_PRICE = 1.99, TALL_PRICE = 2.49, GRANDE_PRICE = 2.99, VENTI_PRICE = 3.49;

    /**
     * Constructor for a new Coffee object.
     * @param size the size of the coffee order.
     */

    public Coffee(int size) {
        this.size = size;
        this.addIns = new boolean[ NUM_ADD_INS ];
    }

    /**
     * Method to calculate the price of this Coffee, overridden from the parent class MenuItem. This will depend on both the size and add-ins in the coffee.
     */
    @Override
    public void itemPrice() {

        // Checking the boolean array representation of the add ins

        for ( int i = 0; i < NUM_ADD_INS; i++ ) {

            if ( addIns[ i ] )
                super.setPrice( super.getPrice() + 0.2 );

        }

        // Checking the size

        switch (size) {
            case SIZE_SHORT -> super.setPrice(super.getPrice() + SHORT_PRICE);
            case SIZE_TALL -> super.setPrice(super.getPrice() + TALL_PRICE);
            case SIZE_GRANDE -> super.setPrice(super.getPrice() + GRANDE_PRICE);
            case SIZE_VENTI -> super.setPrice(super.getPrice() + VENTI_PRICE);
        }



    }

    /**
     * Method to add an object.
     * @param obj The object to add.
     * @return true if object was added successfully, false otherwise.
     */

    @Override
    public boolean add(Object obj) {

        if ( obj instanceof String ) {

            String temp = ( String ) obj;

            switch ( temp )
            {

                case "cream":
                    addIns[ CREAM ] = true;
                    break;

                case "syrup":
                    addIns[ SYRUP ] = true;
                    break;

                case "milk":
                    addIns[ MILK ] = true;
                    break;

                case "caramel":
                    addIns[ CARAMEL] = true;
                    break;

                case "whipped cream":
                    addIns[ WHIPPED_CREAM ] = true;
                    break;

                // User enters a topping that DNE

                default:
                    return false;

            }

            return true;

        }

        return false;
    }

    /**
     * Method to remove an object.
     * @param obj The object to remove.
     * @return true if the object was removed successfully, false otherwise.
     */

    @Override
    public boolean remove(Object obj) {

        if ( obj instanceof String ) {

            String temp = ( String ) obj;

            switch ( temp )
            {

                case "cream":
                    addIns[ CREAM ] = false;
                    break;

                case "syrup":
                    addIns[ SYRUP ] = false;
                    break;

                case "milk":
                    addIns[ MILK ] = false;
                    break;

                case "caramel":
                    addIns[ CARAMEL] = false;
                    break;

                case "whipped cream":
                    addIns[ WHIPPED_CREAM ] = false;
                    break;

                // User enters a topping that DNE

                default:
                    return false;

            }

            return true;

        }

        return false;

    }

    /**
     * Getter method to obtain the size of the coffee.
     * @return the size of the coffee.
     */
    public int getSize() {
        return size;
    }

    /**
     * Getter method to obtain the list of add-ins of the coffee.
     * @return
     */
    public boolean[] getAddIns() {
        return addIns;
    }
}