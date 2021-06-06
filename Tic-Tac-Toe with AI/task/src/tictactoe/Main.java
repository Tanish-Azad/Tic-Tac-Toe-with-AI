package tictactoe;

import java.util.Scanner;

public class Main {
    private static final char[][] grid = new char[3][3];
    private static final Scanner scanner = new Scanner(System.in);
    private static final String badParameters = "Bad parameters!";

    public static void main(String[] args) {
        while (true) {
            TicTacToe.fillGrid(grid, Characters.Empty.getCharacter());

            System.out.println("Input command:");
            String input = scanner.nextLine();
            String[] inputArray = input.split("\\s+");

            if (inputArray.length == 1) {
                if (inputArray[0].equalsIgnoreCase("exit")) {
                    scanner.close();
                    break;
                } else {
                    System.out.println(badParameters);
                }
            } else if (inputArray.length == 3) {
                TicTacToe.displayGrid(grid);

                if (inputArray[0].equalsIgnoreCase("start")) {
                    Player player1 = Factory.playerFactory(inputArray[1], grid, Characters.X.getCharacter());
                    Player player2 = Factory.playerFactory(inputArray[2], grid, Characters.O.getCharacter());

                    boolean run = true;
                    while (run) {
                        if (player1 != null) {
                            player1.play();
                            TicTacToe.displayGrid(grid);
                        } else {
                            System.out.println(badParameters);
                        }

                        if (player2 != null) {
                            player2.play();
                            TicTacToe.displayGrid(grid);
                        } else {
                            System.out.println(badParameters);
                        }

                        run = !(TicTacToe.isGameFinished(grid));
                    }

                    System.out.println(TicTacToe.winner(grid));
                } else {
                    System.out.println(badParameters);
                }
            } else {
                System.out.println(badParameters);
            }
        }
    }
}
