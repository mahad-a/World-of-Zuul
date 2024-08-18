/**
 * Subclass - Beamer - a special item that can be charged
 * and fired to teleport player to a location
 *
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 *
 * Whenever player charges the beamer, the room will be "charged"
 * until player fires. When player fires, player is teleported to
 * the charged room.
 *
 * @author Mahad Ahmed 101220427
 * @version March 13, 2023
 */

public class Beamer extends Item  {

    private Room theCharge;
    /**
     * Constructor for class Beamer.
     */
    public Beamer() {
        super("A beamer", 4, "beamer");
    }
    /**
     * Charges the beamer and sets room to be the charged room.
     * @param room
     */
    public void charge(Room room) {
        theCharge = room;
    }

    /**
     * Fires the beamer and teleports player to the charged room.
     * @return returns player to the room if a room has been charged,
     * returns null if no room has been charged
     */
    public Room fire() {
        Room teleport = theCharge;
        theCharge = null;
        return teleport;
    }

    /**
     * Returns true if a charged room exists, returns
     * false if no charged room exists
     * @return true if charged room exists, false if not
     */
    public boolean isCharged() {
        return (theCharge != null);
    }
}
