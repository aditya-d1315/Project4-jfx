package sample;

/**
 * Subclass to hold information on a Donut item.
 * @author Prasanth Balaji, Aditya Dhawan
 */
public class Donut extends MenuItem {

    /**
     * Integer representation of the type of donut:
     * 0: yeast
     * 1: cake
     * 2: hole
     */
    private int type;

    /**
     *  Specific price for the donut depending on the type
     */

    private double price;

    /**
     * Constants relating to type of donut.
     */
    public static final int TYPE_YEAST = 0, TYPE_CAKE = 1, TYPE_HOLE = 2;

    /**
     * Constants relating to the price of each type of donut.
     */

    public static final double YEAST_PRICE = 1.39, CAKE_PRICE = 1.59, HOLE_PRICE = 0.33;

    /**
     * Constructor for a new Donut object.
     * @param type the type of the donut order.
     */
    public Donut(int type) {
        this.type = type;
    }

    /**
     * Method to calculate price of this donut, overridden from the MenuItem superclass.
     */
    @Override
    public void itemPrice() {

        switch ( type )
        {
            case 0:
                price = YEAST_PRICE;
                break;

            case 1:
                price = CAKE_PRICE;
                break;

            case 2:
                price = HOLE_PRICE;
                break;

            default:
                price = 0;

        }


    }

    /**
     * Getter method to obtain the type of donut.
     * @return the type of donut.
     */
    public int getType() {
        return type;
    }
}