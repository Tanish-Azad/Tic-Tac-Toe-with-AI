package tictactoe;

import java.util.Arrays;

/**
 * Contains Useful methods
 *
 * Public static methods
 */
public class TicTacToe {
    /**
     * Fills the board with a character
     *
     * @param grid Game board
     * @param character Character to fill in the board
     */
    public static void fillGrid(char[][] grid, char character) {
        // Loop through the board and change each element with the character
        for (char[] chars : grid) {
            Arrays.fill(chars, character);
        }
    }

    /**
     * Displays the board graphically
     *
     * @param grid Game board
     */
    public static void displayGrid(char[][] grid) {
        String line = "";

        for (int i = 0; i < grid.length; i++) {
            line += "---";
        }

        System.out.println(line);

        for (char[] chars : grid) {
            System.out.print("| ");

            for (int j = 0; j < grid.length; j++) {
                System.out.print(chars[j] + " ");
            }

            System.out.println("|");
        }

        System.out.println(line);
    }

    /**
     * Checks if the game is finished or not
     *
     * @param grid Game board
     * @return a boolean which determines the game is finished or not
     * true = Game is finished
     * false = Game is not finished
     */
    public static boolean isGameFinished(char[][] grid) {
        // Stores the number of empty characters on the board
        int blanks = 0;

        for (char[] characters : grid) {
            for (char character : characters) {
                if (character == Characters.Empty.getCharacter()) blanks++;
            }
        }

        if (blanks <= 0) { // If there is no more available slots, so the game is finished
            return true;
        } else {
            // If someone wins then the game is finished
            return (winner(grid).contains("wins"));
        }
    }

    /**
     * Finds who is the winner
     *
     * @param grid Game board
     * @return A String
     * "X wins" -> Player playing with the character X is the winner
     * "O wins" -> Player playing with the character O is the winner
     * "Draw" -> No one wins its a tie or game is not finished yet
     */
    public static String winner(char[][] grid) {
        boolean xWins = false,
                oWins = false;

        // Checks for rows and columns
        for (int i = 0; i < grid.length; i++) {
            int row = 0,
                column = 0;

            for (int j = 0; j < grid.length; j++) {
                row += grid[i][j];
                column += grid[i][j];
            }

            if (row == (Characters.X.getCharacter() * grid.length)) {
                xWins = true;
            } else if (row == (Characters.O.getCharacter() * grid.length)) {
                oWins = true;
            }

            if (column == (Characters.X.getCharacter() * grid.length)) {
                xWins = true;
            } else if (column == (Characters.O.getCharacter() * grid.length)) {
                oWins = true;
            }

            if (xWins || oWins) {
                break;
            }
        }

        // Checks for diagonals
        for (int i = 0; i < grid.length; i++) {
            int diagonal1 = 0,
                diagonal2 = 0;

            diagonal1 += grid[i][i];
            diagonal2 += grid[i][grid.length - 1 - i];

            if (diagonal1 == (Characters.X.getCharacter() * grid.length)) {
                xWins = true;
            } else if (diagonal1 == (Characters.O.getCharacter() * grid.length)) {
                oWins = true;
            }

            if (diagonal2 == (Characters.X.getCharacter() * grid.length)) {
                xWins = true;
            } else if (diagonal2 == (Characters.O.getCharacter() * grid.length)) {
                oWins = true;
            }

            if (xWins || oWins) {
                break;
            }
        }

        if (xWins) {
            return Characters.X.getCharacter() + " wins";
        } else if (oWins) {
            return Characters.O.getCharacter() + " wins";
        } else {
            return "Draw";
        }
    }

    /**
     * @param character Player character
     * @return Opponent Character
     */
    public static char opponentCharacter(char character) {
        return (character == Characters.X.getCharacter() ? Characters.O.getCharacter() : Characters.X.getCharacter());
    }

    /**
     * @param grid Game board to copy
     * @return Copied board
     */
    public static char[][] cloneGrid(char[][] grid) {
        char[][] cloneGrid = new char[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            System.arraycopy(grid[i], 0, cloneGrid[i], 0, grid[i].length);
        }

        return cloneGrid;
    }
}
