package tictactoe;

import tictactoe.Players.Player;

import java.util.Scanner;

public class Main {
    // Tic-Tac-Toe board. Can work with any dimension but row needs to be equal to column
    private static final char[][] grid = new char[3][3];
    private static final Scanner scanner = new Scanner(System.in);
    private static final String badParameters = "Bad parameters!";

    public static void main(String[] args) {
        while (true) {
            TicTacToe.fillGrid(grid, Characters.Empty.getCharacter()); // Fill the character with empty character

            // Take the input for selecting game mode
            System.out.println("Input command:");
            String input = scanner.nextLine();
            String[] inputArray = input.split("\\s+");

            if (inputArray.length == 1) { // If there is only 1 argument
                if (inputArray[0].equalsIgnoreCase("exit")) { // Exit command
                    scanner.close();
                    break;
                } else {
                    System.out.println(badParameters); // Not supported command
                }
            } else if (inputArray.length == 3) { // If there is 3 argument
                // Displays the empty grid
                TicTacToe.displayGrid(grid);

                if (inputArray[0].equalsIgnoreCase("start")) {
                    /* Crate players of desired types
                     *
                     * It will only create player if correct type was selected otherwise it will be set to null
                     */
                    Player player1 = Factory.playerFactory(inputArray[1], grid, Characters.X.getCharacter());
                    Player player2 = Factory.playerFactory(inputArray[2], grid, Characters.O.getCharacter());

                    // Run the loop until game is not finished
                    while (!(TicTacToe.isGameFinished(grid))) {
                        if (player1 != null) {
                            player1.makeMove(); // player1 play the move
                            TicTacToe.displayGrid(grid); // Display the board
                        } else {
                            // Wrong type of player is selected
                            System.out.println(badParameters); // Not supported command
                        }

                        // Checking if the game is finished. Because, there is a case that player 1 makes move and game ends there
                        if (!TicTacToe.isGameFinished(grid)) {
                            if (player2 != null) {
                                player2.makeMove(); // player2 play the move
                                TicTacToe.displayGrid(grid); // Display the board
                            } else {
                                // Wrong type of player is selected
                                System.out.println(badParameters); // Not supported command
                            }
                        }
                    }

                    // If game is finished print the result
                    System.out.println(TicTacToe.winner(grid));
                } else {
                    System.out.println(badParameters); // Not supported command
                }
            } else {
                System.out.println(badParameters); // Not supported command
            }
        }
    }
}
