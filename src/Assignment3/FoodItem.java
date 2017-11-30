package Assignment3;

/**
 * A class that stores information about a food object.
 *
 * @author Jonatan Fridsten
 */
public class FoodItem {

    private double weight;
    private double volume;
    private String name;

    /**
     * The constructor for the class.
     *
     * @param name   Name of the object.
     * @param volume Volume for the object.
     * @param weight Weight for the object.
     */
    public FoodItem(String name, double volume, double weight) {
        this.name = name;
        this.volume = volume;
        this.weight = weight;
    }

    /**
     * Gets the weight for the object.
     *
     * @return Current weight.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Gets the volume for the object.
     *
     * @return Current volume.
     */
    public double getVolume() {
        return volume;
    }

    /**
     * Gets the name of the object.
     *
     * @return The object name.
     */
    public String getName() {
        return name;
    }

}
