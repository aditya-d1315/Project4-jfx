package sample;

import java.util.ArrayList;

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
     * Constants relating to the indices of the addIns array.
     */

    public static final int CREAM = 0, SYRUP = 1, MILK = 2, CARAMEL = 3, WHIPPED_CREAM = 4;

    /**
     * Constants relating to the price for each specific size of coffee.
     */

    public static final double SHORT_PRICE = 1.99, TALL_PRICE = 2.49, GRANDE_PRICE = 2.99, VENTI_PRICE = 3.49, ADDIN_PRICE = 0.20;

    /**
     * Constructor for a new Coffee object.
     * @param size the size of the coffee order.
     */

    public Coffee(int size) {
        this.size = size;
        this.addIns = new boolean[ NUM_ADD_INS ];
        for(int i = 0; i < NUM_ADD_INS; i ++) {
            addIns[i] = false;
        }
    }

    /**
     * Overriding the equals() method for the Coffee object, defining equality based on their size and their addIns.
     * @param obj -> The other Coffee object, sent in as a generic Object.
     * @return -> true if the two objects are equal, false otherwise.
     */

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Coffee) {

            Coffee other = (Coffee)(obj);

            if(this.size == other.size) {
                for(int i = 0; i < this.addIns.length; i ++) {
                    if(this.addIns[i] != other.addIns[i]) {
                        return false;
                    }
                }
                return true;
            }

            return false;

        }
        return false;
    }

    /**
     * Setter method for the size.
     * @param size the size of the coffee order.
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Overriding the toString() method for the Coffee object.
     * @return -> String in the format of [ Quantity ] [ Size ] [ AddIns ] [ Price ].
     */

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("1 ");
        switch(size) {
            case SIZE_SHORT -> builder.append("Short Coffee ");
            case SIZE_TALL -> builder.append("Tall Coffee ");
            case SIZE_GRANDE -> builder.append("Grande Coffee ");
            case SIZE_VENTI -> builder.append("Venti Coffee ");
        }
        builder.append("[");

        ArrayList<String> addInList = new ArrayList<String>();
        if(addIns[CREAM]) {
            addInList.add("cream");
        }
        if(addIns[SYRUP]) {
            addInList.add("syrup");
        }
        if(addIns[MILK]) {
            addInList.add("milk");
        }
        if(addIns[CARAMEL]) {
            addInList.add("caramel");
        }
        if(addIns[WHIPPED_CREAM]) {
            addInList.add("whipped_cream");
        }

        for(int i = 0; i < addInList.size(); i ++) {
            if(i == addInList.size() - 1) {
                builder.append(addInList.get(i));
            }
            else {
                builder.append(addInList.get(i));
                builder.append(",");
            }
        }

        builder.append("] ");
        builder.append("$");
        builder.append(String.format("%.2f", super.getPrice()));

        return builder.toString(); //1 Short Coffee [syrup,caramel] <price.00>
    }

    /**
     * Method to calculate the price of this Coffee, overridden from the parent class MenuItem. This will depend on both the size and add-ins in the coffee.
     */
    @Override
    public void itemPrice() {

        // Checking the boolean array representation of the add ins

        for ( int i = 0; i < NUM_ADD_INS; i++ ) {

            if ( addIns[ i ] )
                super.setPrice( super.getPrice() + ADDIN_PRICE );

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
     * @return the add-ins array.
     */
    public boolean[] getAddIns() {
        return addIns;
    }
}