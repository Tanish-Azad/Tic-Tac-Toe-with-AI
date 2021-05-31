package tictactoe;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

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

    public abstract void play();
}

class User extends Player {
    public User(char[][] grid, char character) {
        super(grid, character);
    }

    @Override
    public void play() {
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

            if (row <= 3 && column <= 3) {
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

class EasyAI extends Player {
    public EasyAI(char[][] grid, char character) {
        super(grid, character);
    }

    @Override
    public void play() {
        Random random = new Random();
        int row, column;

        System.out.println("Making move level \"easy\"");

        while (true) {
            row = random.nextInt(3);
            column = random.nextInt(3);

            if (!TicTacToe.isOccupiedCoordinate(super.getGrid(), row, column)) {
                super.getGrid()[row][column] = super.getCharacter();
                break;
            }
        }
    }
}