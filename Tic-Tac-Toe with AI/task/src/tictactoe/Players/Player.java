package tictactoe.Players;

public abstract class Player {
    private final char[][] grid;
    private final char character;

    public Player(char[][] grid, char character) {
        this.grid = grid;
        this.character = character;
    }

    public char[][] getGrid() {
        return grid;
    }

    public char getCharacter() {
        return character;
    }

    public abstract void makeMove();
}
