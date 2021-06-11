package tictactoe;

public enum Characters {
    Empty(' '),
    X('X'),
    O('O');

    private final char character;

    Characters(char character) {
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }
}
