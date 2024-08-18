/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.
 *
 * This class represents items found in a room within the "World of Zuul" and
 * able to add items to a room.
 *
 * @author Mahad Ahmed 101220427
 * @version February 18, 2023
 *
 * @author Mahad Ahmed 101220427
 * @version March 14, 2023
 */
public class Item
{
    private String description;
    private double weight;
    private String name;

    /**
     * Constructor for class Item.
     * 
     * @param description The description of the item
     * @param weight The item's weight 
     */
    public Item(String description, double weight, String name)
    {
        this.description = description;
        this.weight = weight;
        this.name = name;
    }

    /**
     * Returns the description and weight of a desired item
     * 
     * @return The description of a desired item
     */
    public String getDescription()
    {
        return description + " that weighs " + weight + "kg.";
    }
    
    /**
     * Gets the name of the desired item
     * @return the item's name
     */
    public String getItemName()
    {
        return name;
    }
}
