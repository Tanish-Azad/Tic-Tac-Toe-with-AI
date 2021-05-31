package tictactoe;

import java.util.Scanner;

public class Main {
    private static final char[][] grid = new char[3][3];
    private static final Scanner scanner = new Scanner(System.in);
    private static final String badParameters = "Bad parameters!";

    public static void main(String[] args) {
        TicTacToe.fillGrid(grid, Characters.Empty.getCharacter());

        while (true) {
            System.out.println("Input command:");
            String input = scanner.nextLine();
            String[] inputArray = input.split("\\s+");

            if (inputArray.length == 1) {
                if (inputArray[0].equalsIgnoreCase("exit")) {
                    break;
                } else {
                    System.out.println(badParameters);
                }
            } else if (inputArray.length == 3) {
                TicTacToe.displayGrid(grid);

                if (inputArray[0].equalsIgnoreCase("start")) {
                    if (inputArray[1].matches("(user|easy)") && inputArray[2].matches("(user|easy)")) {
                        Player player1 = Factory.playerFactory(inputArray[1], grid, Characters.X.getCharacter());
                        Player player2 = Factory.playerFactory(inputArray[2], grid, Characters.O.getCharacter());

                        while (!TicTacToe.isGameFinished(grid)) {
                            if (player1 != null) {
                                player1.play();
                                TicTacToe.displayGrid(grid);
                            }

                            if (player2 != null) {
                                player2.play();
                                TicTacToe.displayGrid(grid);
                            }
                        }

                        System.out.println(TicTacToe.winner(grid));
                    } else {
                        System.out.println(badParameters);
                    }
                } else {
                    System.out.println(badParameters);
                }
            } else {
                System.out.println(badParameters);
            }
        }
    }
}
