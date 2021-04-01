package sample;

/**
 * Interface designed for holding the add/remove methods.
 * @author Prasanth Balaji, Aditya Dhawan
 */
public interface Customizable {
    /**
     * Method to add an object.
     * @param obj The object to add.
     * @return true if object was added successfully, false otherwise.
     */
    boolean add(Object obj);

    /**
     * Method to remove an object.
     * @param obj The object to remove.
     * @return true if the object was removed successfully, false otherwise.
     */
    boolean remove(Object obj);
}