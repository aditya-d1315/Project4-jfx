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
     * Instance variable for the flavor of the donut.
     */
    private String flavor;

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
    public Donut(int type, String flavor) {
        this.type = type;
        this.flavor = flavor;
    }

    /**
     * Method to calculate price of this donut, overridden from the MenuItem superclass.
     */
    @Override
    public void itemPrice() {
        switch (type) {
            case TYPE_YEAST -> super.setPrice(YEAST_PRICE);
            case TYPE_CAKE -> super.setPrice(CAKE_PRICE);
            case TYPE_HOLE -> super.setPrice(HOLE_PRICE);
            default -> super.setPrice(0);
        }
    }

    /**
     * Getter method to obtain the type of donut.
     * @return the type of donut.
     */
    public int getType() {
        return type;
    }

    /**
     * Getter method to obtain the flavor of the donut.
     * @return the flavor of the donut.
     */
    public String getFlavor() {
        return flavor;
    }
}