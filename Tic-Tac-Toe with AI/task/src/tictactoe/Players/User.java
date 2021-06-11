package tictactoe.Players;

import tictactoe.TicTacToe;

import java.util.InputMismatchException;
import java.util.Scanner;

@SuppressWarnings("unused")
public class User extends Player {
    public User(char[][] grid, char character) {
        super(grid, character);
    }

    @Override
    public void makeMove() {
        Scanner scanner = new Scanner(System.in);
        int row, column;

        while (true) {
            System.out.println("Enter the coordinates: ");

            try {
                row = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                String s = scanner.nextLine();
                continue;
            }

            try {
                column = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                String s = scanner.nextLine();
                continue;
            }

            if (row <= super.getGrid().length && column <= super.getGrid().length) {
                if (TicTacToe.isOccupiedCoordinate(super.getGrid(), row - 1, column - 1)) {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    super.getGrid()[row - 1][column - 1] = super.getCharacter();
                    break;
                }
            } else {
                System.out.println("Coordinates should be from 1 to 3!");
            }
        }
    }
}
