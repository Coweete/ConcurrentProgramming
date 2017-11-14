package Assignment2;

public class CharacterBuffer {

    private Character character;

    public CharacterBuffer() {

    }

    public boolean hasCharacter() {
        return character != null;
    }

    public void addCharacter(char c) {
        character = c;
    }

    public char getCharacter() {
        char temp = character;
        character = null;
        return temp;
    }
}
