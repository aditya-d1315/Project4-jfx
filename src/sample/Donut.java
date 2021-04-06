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
     * Overriding the equals() method for the Donut object, defining equality based on their type and flavor.
     * @param obj -> The other Donut object, sent in as a generic Object.
     * @return -> true if the two objects are equal, false otherwise.
     */

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Donut) {
            Donut other = (Donut)(obj);
            return (this.type == other.type && this.flavor.equals(other.flavor));
        }
        return false;
    }

    /**
     * Overriding the toString() method for the Donut object.
     * @return -> String in the format of < Quantity > < Flavor > < Type > < Price >.
     */

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("1 ");
        switch(flavor) {
            case "Glazed" -> builder.append("Glazed");
            case "Chocolate Frosted" -> builder.append("Chocolate_Frosted");
            case "Strawberry Frosted" -> builder.append("Strawberry_Frosted");
            case "Boston Kreme" -> builder.append("Boston_Kreme");
            case "Bavarian Kreme" -> builder.append("Bavarian_Kreme");
            case "Chocolate Kreme" -> builder.append("Chocolate_Kreme");
            case "Jelly" -> builder.append("Jelly");
            case "Double Chocolate" -> builder.append("Double_Chocolate");
            case "Vanilla Frosted" -> builder.append("Vanilla_Frosted");
        }
        builder.append(" ");
        switch(type) {
            case TYPE_YEAST -> builder.append("Yeast_Donut ");
            case TYPE_CAKE -> builder.append("Cake_Donut ");
            case TYPE_HOLE -> builder.append("Donut_Hole ");
        }
        builder.append("$");
        builder.append(String.format("%.2f", super.getPrice()));
        return builder.toString(); //1 <flavor> <type> <price.00>
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