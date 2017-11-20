package Assignment2;

import java.util.LinkedList;

/**
 * A buffer class with one character at the time
 *
 * @author Jonatan Fridsten
 */
public class CharacterBuffer {

    private volatile Character character;

    /**
     * Default constructor
     */
    public CharacterBuffer() {
    }

    public synchronized Character getFromBuffer() {
        if (character == null) {
            return null;
        } else {
            char temp = character;
            character = null;
            return temp;
        }
    }

    /**
     * Add a character to the buffer.
     *
     * @param c Character to add
     */
    public synchronized boolean addCharacter(char c) {
        if (character != null) {
            return false;
        } else {
            character = c;
            return true;
        }
    }

    /**
     * Adds an character non sync
     *
     * @param c
     */
    public void addCharacters(char c) {
        character = c;
    }

    /**
     * Get character non sync
     *
     * @return a char
     */
    public Character getCharacter() {
        Character temp = character;
        character = null;
        if (temp != null) {
            return temp;
        }
        return character;
    }

    /**
     * Returns if there is a character or not
     *
     * @return the character
     */
    public boolean hasCharacter() {
        return character != null;
    }
}
