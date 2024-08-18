import java.util.ArrayList;
import java.util.Random;

/**
 * Subclass - Chests - a special item that has multiple random items
 * stored within it. Allowing player a chance at a random item.
 *
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 *
 * PART B
 * Player can open a chest for a chance at a random value if the
 * room holds a chest, the player has an empty backpack and if the
 * player has already consumed a cookie.
 *
 * @author Mahad Ahmed 101220427
 * @version March 14, 2023
 */

public class Chests extends Item {
    // The contents of the chest placed into an ArrayList of type Item
    private final ArrayList<Item> contents = new ArrayList<Item>();
    /**
     * Constructor for class Chests.
     */
    public Chests() {
        super("a treasure chest", 30, "chest");
    }

    /**
     * Returns the contents of the chest in an ArrayList.
     *
     * @return The contents of the chest.
     */
    public ArrayList<Item> getContents() {
        return contents;
    }

    /**
     * Returns a random item from the chest and removes it from the contents list.
     *
     * @return A random item from the chest.
     */
    public Item getRandomItem() {
        Random rand = new Random();
        if (contents.size() > 0) {
            Item randomItem = contents.get(rand.nextInt(contents.size()));
            contents.remove(randomItem);
            return randomItem;
        }
        return null;
    }

    /**
     * Fill up the chest with some items, then generate a random
     * index to grab a random item from the chest
     * @param aChest The chest we want to fill with items
     * @return returns a random item from the given chest
     */
    public Item fillChest(Chests aChest) {
        Item box, chair1, chair2, computer1, computer2, desk, sword, shield,
                gold, car, cookie1, cookie2, cookie3, cookie4, cookie5;

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

        aChest.contents.add(box);
        aChest.contents.add(chair1);
        aChest.contents.add(chair2);
        aChest.contents.add(computer1);
        aChest.contents.add(computer2);
        aChest.contents.add(desk);
        aChest.contents.add(sword);
        aChest.contents.add(shield);
        aChest.contents.add(gold);
        aChest.contents.add(car);
        aChest.contents.add(cookie1);
        aChest.contents.add(cookie2);
        aChest.contents.add(cookie3);
        aChest.contents.add(cookie4);
        aChest.contents.add(cookie5);

        int randomVal = (int) (Math.random() * contents.size());
        return aChest.contents.get(randomVal);
    }
}
