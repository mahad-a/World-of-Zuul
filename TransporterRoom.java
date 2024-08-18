import java.util.Random;
/**
 * Subclass - TransporterRoom - a unique room in an adventure game.
 *
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 *
 * Whenever player leaves a transporter room, they are randomly
 * teleported into another room in the game. Does not have to be
 * adjacent to the transporter room.
 *
 * @author Mahad Ahmed 101220427
 * @version March 14, 2023
 */
public class TransporterRoom extends Room {
    private Random randRoom;
    /**
     * Creates a new Transporter Room
     */
    public TransporterRoom() {
        super("a transporter room.");
        randRoom = new Random();
        setExit("anywhere", null);
    }
    /**
     * Returns a random room, independent of the direction parameter.
     *
     * @param direction Ignored.
     * @return A randomly selected room.
     */
    public Room getExit(String direction) {
        return findRandomRoom();
    }

    /**
     * Choose a random room.
     *
     * @return The room we end up in upon leaving this one.
     */
    private Room findRandomRoom() {
        int numOfRooms = Room.allRooms().size();
        return Room.allRooms().get(randRoom.nextInt(numOfRooms));
    }
}
