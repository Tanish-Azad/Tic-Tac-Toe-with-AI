package tictactoe.Players;

import tictactoe.Characters;

import java.util.InputMismatchException;
import java.util.Scanner;

public class User extends Player {
    /**
     * @param grid Game board
     * @param character Player Character
     */
    public User(char[][] grid, char character) {
        // Constructor of super class player
        super(grid, character);
    }

    /**
     * Abstract method of super class Player
     *
     * Asks the user to make a move on the board
     */
    @Override
    public void makeMove() {
        Scanner scanner = new Scanner(System.in);
        int row, column;

        while (true) {
            System.out.println("Enter the coordinates: ");

            try {
                // Takes the row number
                row = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                // Takes the extra line
                scanner.nextLine();
                continue;
            }

            try {
                // Takes the column number
                column = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                // Takes the extra line
                scanner.nextLine();
                continue;
            }

            // Checks if the input row or column is not greater or smaller than the Game board
            if ((row <= super.getGrid().length && row >= 1) && (column <= super.getGrid().length && column >= 1)) {
                if (super.getGrid()[row - 1][column - 1] == Characters.Empty.getCharacter()) {
                    // Places a character if the position is empty
                    super.getGrid()[row - 1][column - 1] = super.getCharacter();
                    break;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            } else {
                // Input row or column is greater than board length or less than 1
                System.out.println("Coordinates should be from 1 to 3!");
            }
        }
    }
}
