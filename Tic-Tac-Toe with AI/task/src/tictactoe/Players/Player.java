package tictactoe.Players;

public abstract class Player {
    private final char[][] grid; // Game board
    private final char character; // Character of the player 'X' or 'O'

    // Constructor of class Player
    public Player(char[][] grid, char character) {
        // Sets it to local variable grid
        this.grid = grid;

        // Sets it to local variable character
        this.character = character;
    }

    /**
     * @return Game board
     */
    public char[][] getGrid() {
        return grid;
    }

    /**
     * @return Player character
     */
    public char getCharacter() {
        return character;
    }

    public abstract void makeMove();
}
