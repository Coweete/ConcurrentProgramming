package Assignment2;

import java.util.LinkedList;

public class CharacterBuffer {

    private LinkedList<Character> linkedList;

    public CharacterBuffer(){
        linkedList = new LinkedList<>();
    }

    public synchronized boolean hasCharacter(){
        return linkedList.size() >= 1;
    }

    public synchronized void addCharacter(char c){
        linkedList.addFirst(c);
    }

    public synchronized char removeCharacter(){
        return linkedList.removeFirst();
    }
}
