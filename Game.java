import java.util.Stack;
import java.util.ArrayList;
//Needed to use Stack class
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 *
 * PART B is included within this code. The new elements include PART B in the Javadoc.
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

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room pastRoom;
    private Stack<Room> pastRoomStack;
    // Players "bag" to hold items
    private Item backpack;
    public static final String beamer = "beamer";
    public static final String cookie = "cookie";
    private int bonusCarry = 0;
    public static final String gold = "gold";
    private Item merchant;
    public static final String chests = "chest";
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        pastRoom = null;
        pastRoomStack = new Stack<Room>();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theatre, pub, lab, office;
        Item box, chair1, chair2, computer1, computer2, desk, sword, shield,
                gold, car, cookie1, cookie2, cookie3, cookie4, cookie5;

        Beamer beamer1, beamer2;
        TransporterRoom transporterRoom;
        Chests chest;
          
        // create the items
        box = new Item("a cardboard box",5, "box");
        chair1 = new Item("a wooden chair",2, "chair");
        chair2 = new Item("a plastic chair", 4, "chair");
        computer1 = new Item("an HP laptop",5, "HP");
        computer2 = new Item("a MacBook Pro",3, "MBP");
        desk = new Item("an office desk", 30, "desk");
        sword = new Item("a sword",8, "sword");
        shield = new Item("a metal shield",20, "shield");
        gold = new Item("gold currency", 15, "gold");
        car = new Item("a car", 50, "car");
        cookie1 = new Item("a pack of cookies", 1, "cookie");
        cookie2 = new Item("a pack of cookies", 1, "cookie");
        cookie3 = new Item("a pack of cookies", 1, "cookie");
        cookie4 = new Item("a pack of cookies", 1, "cookie");
        cookie5 = new Item("a pack of cookies", 1, "cookie");

        merchant = new Item("a merchant selling goods.", 100, "merchant");

        beamer1 = new Beamer();
        beamer2 = new Beamer();

        chest = new Chests();


        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        transporterRoom = new TransporterRoom();
        
        // place items in designated rooms
        outside.addItem(car);
        outside.addItem(cookie5);
        outside.addItem(beamer1);
        outside.addItem(beamer2);
        outside.addItem(chest);
        theatre.addItem(chair2);
        theatre.addItem(cookie4);
        theatre.addItem(merchant);
        pub.addItem(chair1);
        pub.addItem(cookie1);
        pub.addItem(box);
        pub.addItem(merchant);
        pub.addItem(chest);
        lab.addItem(desk);
        lab.addItem(computer2);
        lab.addItem(sword);
        lab.addItem(shield);
        lab.addItem(cookie2);
        lab.addItem(chest);
        office.addItem(desk);
        office.addItem(gold);
        office.addItem(computer1);
        office.addItem(cookie3);
        office.addItem(chest);
        transporterRoom.addItem(cookie1);
        transporterRoom.addItem(gold);
        
        // initialise room exits
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theatre.setExit("west", outside);

        pub.setExit("east", outside);
        pub.setExit("north", transporterRoom);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);

        currentRoom = outside;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * 
     * @param command The command to be processed
     * @return true If the command ends the game, false otherwise
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            look(command);
        }
        else if (commandWord.equals("eat")) {
            eat(command);
        }
        else if (commandWord.equals("back")) {
            back(command);
        }
        else if (commandWord.equals("stackBack")) {
            stackBack(command);
        }
        else if (commandWord.equals("take")) {
            take(command);
        }
        else if (commandWord.equals("drop")) {
            drop(command);
        }
        else if (commandWord.equals("charge")) {
            charge(command);
        }
        else if (commandWord.equals("fire")) {
            fire(command);
        }
        else if (commandWord.equals("pay")) {
            pay(command);
        }
        else if (commandWord.equals("open")) {
            open(command);
        }
        else if (commandWord.equals("myBackpack")) {
            myBackpack(command);
        }
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print a cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.getCommands());
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * 
     * @param command The command to be processed
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            pastRoom = currentRoom; 
            pastRoomStack.push(currentRoom);
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }
    /**
     * "Look" was entered. Check the rest of the command to see
     * whether we really want to look.
     * 
     * @param command The command to be processed
     */
    private void look(Command command) {
        if(command.hasSecondWord()) {
            System.out.println("Look at what?");
        }
        else {
            System.out.println(currentRoom.getLongDescription());
        }
    }
    /**
     * "Eat" was entered. Check the rest of the command to see
     * whether we really want to eat.
     * 
     * @param command The command to be processed
     */
    private void eat(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Eat what?");
        } else if (backpack == null || !backpack.getItemName().equals(cookie)) {
            System.out.println("You don't have any cookies in your backpack.");
        }
        else {
            System.out.println("You have eaten a cookie and are no longer hungry.");
            bonusCarry += 5;
            backpack = null;
        }
    }
    /** 
     * "Back" was entered. Check the rest of the command to see
     * whether we really want to back.
     * 
     * @param command The command to be processed
     */
    private void back(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Back what?");
        }
        else {
            if (pastRoom==null) {
                System.out.println("There is no room to go back to.");
            } else {
                Room placementHolder = currentRoom;
                currentRoom = pastRoom;
                pastRoom = placementHolder;
                pastRoomStack.push(placementHolder);
                System.out.println(currentRoom.getLongDescription());
            }
        }
    }
    /** 
     * "StackBack" was entered. Check the rest of the command to see
     * whether we really want to stackBack.
     * 
     * @param command The command to be processed
     */
    private void stackBack(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("StackBack what?");
        } else {
            if (pastRoomStack.isEmpty()) {
                System.out.println("There is no room to go stackBack to.");
            } else {
                pastRoom = currentRoom;
                currentRoom = pastRoomStack.pop();
                System.out.println(currentRoom.getLongDescription());
            }
        }
    }
    /**
     * "Take" was entered. Check the rest of the command to see
     * whether we really want to take.
     *
     * @param command The command to be processed
     */
    private void take(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Take what?");
            return;
        }
        if (backpack != null) {
            System.out.println("Your backpack already has items.");
            return;
        }

        String itemName = command.getSecondWord().toLowerCase();
        if (itemName.equals("merchant")) {
            System.out.println("You cannot take the Merchant.");
            return;
        }

        if (bonusCarry <= 0 && !itemName.equals(cookie)) {
            System.out.println("A cookie must be ate before taking any more items.");
            return;
        }
        backpack = currentRoom.removeItem(itemName);

        if (backpack == null) {
            System.out.println(itemName  + " is not in this room.");
        } else {
            System.out.println("You picked up: " + itemName);
            bonusCarry--;
        }
    }
    
    /**
     * "Drop" was entered. Check the rest of the command to see
     * whether we really want to drop.
     *
     * @param command The command to be processed
     */
    private void drop(Command command) { 
        if(command.hasSecondWord()) {
            System.out.println("Drop what?");
        } else if (backpack == null) {
            System.out.println("Backpack is empty, nothing is dropped.");
        } else {
            currentRoom.addItem(backpack);
            System.out.println("You have dropped: " + backpack.getItemName());
            backpack = null;
        }
    }

    /**
     * "Charge" was entered. Check the rest of the command to see
     * whether we really want to charge the beamer.
     * @param command The command to be processed
     */
    private void charge(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Charge what?");
        } else if (backpack == null || !backpack.getItemName().equals(beamer)) {
            System.out.println("You are not holding " + beamer);
        } else {
            Beamer chargeBeamer = (Beamer) backpack;
            if (chargeBeamer.isCharged()) {
                System.out.println("Beamer is already charged");
            } else {
                chargeBeamer.charge(currentRoom);
                System.out.println(beamer + " has now been charged.");
            }
        }
    }

    /**
     * "Fire" was entered. Check the rest of the command to see
     * whether we really want to fire the beamer.
     * @param command The command to be processed
     */
    private void fire(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Fire what?");
        } else if (backpack == null || !backpack.getItemName().equals(beamer)) {
            System.out.println("You are not holding " + beamer);
        } else {
            Beamer chargedBeamer = (Beamer) backpack;
            if (!chargedBeamer.isCharged()) {
                System.out.println("Beamer has not been charged yet.");
            } else {
                System.out.println(beamer + "has been charged.");
                pastRoom = currentRoom;
                pastRoomStack.push(currentRoom);
                currentRoom = chargedBeamer.fire();
                System.out.println(currentRoom.getLongDescription());
            }
        }
    }

    /**
     * "Pay" was entered. Check the rest of the command to see
     * whether we really want to pay the merchant.
     *
     * PART B
     * Merchants can be found within the world of zuul, using the item
     * "gold" as a form of payment. Players can purchase a random item
     * from the Merchant. Players cannot buy the merchant himself and
     * cannot "double-dip" or purchase from the same merchant multiple times.
     *
     * @param command The command to be processed
     */
    private void pay(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Pay what?");
        }
        if (backpack == null || !backpack.getItemName().equals(gold)) {
            System.out.println("You are not holding " + gold);
        } else if (merchant.getItemName().equals(gold)) {
            System.out.println("This merchant is not selling at this moment.");
        } else {
            Item payment = backpack;
            backpack = merchant;
            merchant = payment;
            System.out.println("You have paid merchant gold for " + merchant.getItemName());
        }
    }

    /**
     * "Open" was entered. Check the rest of the command to
     * see whether we really want to open a chest.
     *
     * PART B
     * Chests with random loot can be found around the world of
     * zuul. Players can open a chest for a chance at a random item
     * as long as player has consumed a cookie prior, has no items in
     * their backpack and the room has a chest within it. Players can pick
     * up chests and store them for later use.
     *
     * @param command The command to be processed
     */
    private void open(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Open what?");
        }
        if (bonusCarry <= 0) {
            System.out.println("A cookie must be ate before opening any chests.");
            return;
        }
        if (backpack != null) {
            System.out.println("Your backpack already has items, no space for chest equipment.");
        } else {
            backpack = currentRoom.removeItem(chests);
            if (backpack == null) {
                System.out.println("There is no chest in this room to open.");
            } else {
                Chests openChest = new Chests();
                openChest.fillChest(openChest);
                if (openChest.getContents() != null) {
                    backpack = openChest.getRandomItem();
                    System.out.println("You opened the chest and got " + backpack.getItemName());

                }
            }
        }
    }

    /**
     * "myBackpack" was entered. Check the rest of the command to
     * see whether we really want to view our backpack.
     *
     * PART B
     * myBackpack displays what is currently stored in the players backpack. For
     * example, an event that the player enters the room where there's a merchant,
     * they are able to view if they have gold in their backpack.
     * @param command
     */
    private void myBackpack(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Backpack what?");
        }
        if (backpack == null) {
            System.out.println("Backpack is currently empty.");
        } else {
            System.out.println("You're currently holding " + backpack.getDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * 
     * @param command The command to be processed
     * @return true, if this command quits the game, false otherwise
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
