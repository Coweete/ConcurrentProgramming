package Assignment2;

import java.util.LinkedList;

/**
 * A buffer class with one character at the time
 *
 * @author Jonatan Fridsten
 */
public class CharacterBufferSynchronized {

    private volatile Character character;

    /**
     * Default constructor
     */
    public CharacterBufferSynchronized() {
    }

    /**
     * Checks if there is a character or not.
     *
     * @return True: Character, False: No Character
     */
    public synchronized boolean hasCharacter() {
        return character != null;
    }

    /**
     * Add a character to the buffer.
     *
     * @param c Character to add
     */
    public synchronized void addCharacter(char c) {
        character = c;
    }

    /**
     * Returns and remove the character from
     * the buffer.
     *
     * @return The char in buffer.
     */
    public synchronized char removeCharacter() {
        char temp = character;
        character = null;
        return temp;
    }
}
