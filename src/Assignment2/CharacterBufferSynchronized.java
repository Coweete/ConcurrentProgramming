package Assignment2;

import java.util.LinkedList;

public class CharacterBufferSynchronized {

    private volatile Character character;

    public CharacterBufferSynchronized() {
    }

    public synchronized boolean hasCharacter() {
        return character != null;
    }

    public synchronized void addCharacter(char c) {
        character = c;
    }

    public synchronized char removeCharacter() {
        char temp = character;
        character = null;
        return temp;
    }
}
