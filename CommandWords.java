/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
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

public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
        "go", "quit", "help", "look", "eat", "back", "stackBack", "take",
        "drop", "charge", "fire", "pay", "open", "myBackpack"
    };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word. 
     * 
     * @param aString The String to check
     * @return true if it is valid, false otherwise
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    /**
     * Returns a String containing all valid commands
     * 
     * @return a String containing all valid commands
     */
    public String getCommandList() 
    {
        String commands = "";
        for (String command : validCommands) {
            commands += command + "  ";
        }
        return commands.trim();
    }
}
