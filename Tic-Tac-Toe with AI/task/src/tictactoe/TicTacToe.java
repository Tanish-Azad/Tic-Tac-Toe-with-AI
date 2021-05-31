package tictactoe;

import java.util.Arrays;

enum Characters {
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

public class TicTacToe {
    public static void fillGrid(char[][] grid, char character) {
        for (char[] chars : grid) {
            Arrays.fill(chars, character);
        }
    }

    public static void displayGrid(char[][] grid) {
        System.out.println("---------");

        for (int i = 0; i < 3; i++) {
            System.out.print("| ");

            for (int j = 0; j < 3; j++) {
                System.out.print(grid[i][j] + " ");
            }

            System.out.println("|");
        }

        System.out.println("---------");
    }

    public static boolean isOccupiedCoordinate(char[][] grid, int row, int column) {
        return (!(grid[row][column] == Characters.Empty.getCharacter()));
    }

    public static boolean isGameFinished(char[][] grid) {
        int blanks = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == Characters.Empty.getCharacter()) blanks++;
            }
        }

        if (winner(grid).equalsIgnoreCase("X wins")) {
            return true;
        } else if (winner(grid).equalsIgnoreCase("O wins")) {
            return true;
        } else return (winner(grid).equalsIgnoreCase("Draw") && blanks == 0);
    }

    // I know this looks ugly someone please help me to fix this
    public static String winner(char[][] grid) {
        boolean xWins = false,
                oWins = false;

        int row1 = grid[0][0] + grid[0][1] + grid[0][2],
            row2 = grid[1][0] + grid[1][1] + grid[1][2],
            row3 = grid[2][0] + grid[2][1] + grid[2][2];

        int col1 = grid[0][0] + grid[1][0] + grid[2][0],
            col2 = grid[0][1] + grid[1][1] + grid[2][1],
            col3 = grid[0][2] + grid[1][2] + grid[2][2];

        int diagonal1 = grid[0][0] + grid[1][1] + grid[2][2],
            diagonal2 = grid[0][2] + grid[1][1] + grid[2][0];

        if (row1 == 264 || row2 == 264 || row3 == 264) {
            xWins = true;
        } else if (row1 == 237 || row2 == 237 || row3 == 237) {
            oWins = true;
        }

        if (col1 == 264 || col2 == 264 || col3 == 264) {
            xWins = true;
        } else if (col1 == 237 || col2 == 237 || col3 == 237) {
            oWins = true;
        }

        if (diagonal1 == 264 || diagonal2 == 264) {
            xWins = true;
        } else if (diagonal1 == 237 || diagonal2 == 237) {
            oWins = true;
        }

        if (xWins) {
            return Characters.X.getCharacter() + " wins";
        } else if (oWins) {
            return Characters.O.getCharacter() + " wins";
        } else {
            return "Draw";
        }
    }
}
