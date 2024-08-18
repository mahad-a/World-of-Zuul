import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList; 
// Needed to use ArrayList for items class
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * 
 * @author Lynn Marshall
 * @version October 21, 2012
 * 
 * @author Mahad Ahmed 101220427
 * @version February 18, 2023
 *
 * @author Mahad Ahmed 101220427
 * @version March 14, 2023
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private ArrayList<Item> items;
    private static ArrayList<Room> rooms = new ArrayList<>();
    private Chests chest;
    private ArrayList<Chests> chests;
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * 
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        items = new ArrayList<Item>();
        rooms.add(this);
    }

    /**
     * Define an exit from this room.
     * 
     * @param direction The direction of the exit
     * @param neighbour The room to which the exit leads
     */
    public void setExit(String direction, Room neighbour) 
    {
        exits.put(direction, neighbour);
    }

    /**
     * Returns a short description of the room, i.e. the one that
     * was defined in the constructor
     * 
     * @return The short description of the room
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a long description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     *     
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString() + ".\n" + getItems();
    }
    /**
     * Add items to a room
     * @param item the item to add to the room
     */
    public void addItem(Item item) 
    {
        items.add(item);
    }
    /**
     * Returns a String containing all items in the current room
     * 
     * @return a String containing all items in the room
     */
    public String getItems() 
    {
        String gotItems = "";
        for (Item theItem: items) {
            gotItems += "\n " + theItem.getDescription();
        }
        return gotItems;
    }
    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * 
     * @return Details of the room's exits
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Remove and return item from the room. If item
     * not in room, return null.
     *
     * @param name The name of the item to remove
     * @return The item removed, null if item not in room
     */
    public Item removeItem(String name) {
        Item removedItem;
        Iterator<Item> checkItem = items.iterator();
        while (checkItem.hasNext()) {
            removedItem = checkItem.next();
            if (removedItem.getItemName().equals(name)) {
                checkItem.remove();
                return removedItem;
            }
        }
        return null;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * 
     * @param direction The exit's direction
     * @return The room in the given direction
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }

    /**
     * Grab all rooms in the world of zuul and place
     * into an ArrayList type Rooms
     *
     * @return ArrayList with all rooms in the world of zuul
     */
    static public ArrayList<Room> allRooms() {
        return rooms;
    }


}

