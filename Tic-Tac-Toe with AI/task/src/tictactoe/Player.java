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

@SuppressWarnings("unused")
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

class EasyAI extends Player {
    public EasyAI(char[][] grid, char character) {
        super(grid, character);
    }

    @Override
    public void play() {
        System.out.println("Making move level \"easy\"");

        randomMove(super.getGrid(), super.getCharacter());
    }

    protected void randomMove(char[][] grid, char character) {
        while (true) {
            Random random = new Random();

            int row = random.nextInt(grid.length);
            int column = random.nextInt(grid.length);

            if (!TicTacToe.isOccupiedCoordinate(grid, row, column)) {
                grid[row][column] = character;
                break;
            }
        }
    }
}

class MediumAI extends EasyAI {
    public MediumAI(char[][] grid, char character) {
        super(grid, character);
    }

    @Override
    public void play() {
        System.out.println("Making move level \"medium\"");

        if (canBlockOrCompleteRows(super.getGrid())) {
            blockOrCompleteRows(super.getGrid(), super.getCharacter());
        } else if (canBlockOrCompleteColumns(super.getGrid())) {
            blockOrCompleteColumns(super.getGrid(), super.getCharacter());
        } else if (canBlockOrCompleteDiagonal(super.getGrid())) {
            blockOrCompleteDiagonal(super.getGrid(), super.getCharacter());
        } else {
            super.randomMove(super.getGrid(), super.getCharacter());
        }
    }

    protected void blockOrCompleteRows(char[][] grid, char character) {
        int xCount, oCount, blankCount;
        int[] blankCords = new int[2];

        for (int i = 0; i < grid.length; i++) {
            xCount = 0;
            oCount = 0;
            blankCount = 0;

            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == Characters.X.getCharacter()) {
                    xCount++;
                }

                if (grid[i][j] == Characters.O.getCharacter()) {
                    oCount++;
                }

                if (grid[i][j] == Characters.Empty.getCharacter()) {
                    blankCount++;

                    if (blankCount >= 2) {
                        break;
                    } else {
                        blankCords[0] = i;
                        blankCords[1] = j;
                    }
                }

                if (!TicTacToe.isOccupiedCoordinate(grid, blankCords[0], blankCords[1])) {
                    if (xCount == 0 && oCount == 2) {
                        grid[blankCords[0]][blankCords[1]] = character;
                        return;
                    }

                    if (xCount == 2 && oCount == 0) {
                        grid[blankCords[0]][blankCords[1]] = character;
                        return;
                    }
                }
            }
        }
    }

    protected void blockOrCompleteColumns(char[][] grid, char character) {
        int xCount, oCount, blankCount;
        int[] blankCords = new int[2];

        for (int i = 0; i < grid.length; i++) {
            xCount = 0;
            oCount = 0;
            blankCount = 0;

            for (int j = 0; j < grid.length; j++) {
                if (grid[j][i] == Characters.X.getCharacter()) {
                    xCount++;
                }

                if (grid[j][i] == Characters.O.getCharacter()) {
                    oCount++;
                }

                if (grid[j][i] == Characters.Empty.getCharacter()) {
                    blankCount++;

                    if (blankCount >= 2) {
                        break;
                    } else {
                        blankCords[0] = j;
                        blankCords[1] = i;
                    }
                }

                if (!TicTacToe.isOccupiedCoordinate(grid, blankCords[0], blankCords[1])) {
                    if (xCount == 0 && oCount == 2) {
                        grid[blankCords[0]][blankCords[1]] = character;
                        return;
                    }

                    if (xCount == 2 && oCount == 0) {
                        grid[blankCords[0]][blankCords[1]] = character;
                        return;
                    }
                }
            }
        }
    }

    protected void blockOrCompleteDiagonal(char[][] grid, char character) {
        int xCount, oCount, blankCount;
        int[] blankCords = new int[2];

        xCount = 0;
        oCount = 0;
        blankCount = 0;

        for (int i = 0; i < grid.length; i++) {
            if (grid[i][i] == Characters.X.getCharacter()) {
                xCount++;
            }

            if (grid[i][i] == Characters.O.getCharacter()) {
                oCount++;
            }

            if (grid[i][i] == Characters.Empty.getCharacter()) {
                blankCount++;

                if (blankCount >= 2) {
                    return;
                } else {
                    blankCords[0] = i;
                    blankCords[1] = i;
                }
            }
        }

        if (!TicTacToe.isOccupiedCoordinate(grid, blankCords[0], blankCords[1])) {
            if (xCount == 0 && oCount == 2) {
                grid[blankCords[0]][blankCords[1]] = character;
                return;
            }

            if (xCount == 2 && oCount == 0) {
                grid[blankCords[0]][blankCords[1]] = character;
                return;
            }
        }

        xCount = 0;
        oCount = 0;
        blankCount = 0;

        for (int i = grid.length - 1, j = 0; i >= 0 && j < grid.length; i--, j++) {
            if (grid[i][j] == Characters.X.getCharacter()) {
                xCount++;
            }

            if (grid[i][j] == Characters.O.getCharacter()) {
                oCount++;
            }

            if (grid[i][j] == Characters.Empty.getCharacter()) {
                blankCount++;

                if (blankCount >= 2) {
                    return;
                } else {
                    blankCords[0] = i;
                    blankCords[1] = j;
                }
            }

            if (!TicTacToe.isOccupiedCoordinate(grid, blankCords[0], blankCords[1])) {
                if (xCount == 0 && oCount == 2) {
                    grid[blankCords[0]][blankCords[1]] = character;
                    return;
                }

                if (xCount == 2 && oCount == 0) {
                    grid[blankCords[0]][blankCords[1]] = character;
                    return;
                }
            }
        }
    }


    protected boolean canBlockOrCompleteRows(char[][] grid) {
        int xCount, oCount, blankCount;
        int[] blankCords = new int[2];

        for (int i = 0; i < grid.length; i++) {
            xCount = 0;
            oCount = 0;
            blankCount = 0;

            for (int j = 0; j < grid.length; j++) {
                if (grid[j][i] == Characters.X.getCharacter()) {
                    xCount++;
                }

                if (grid[j][i] == Characters.O.getCharacter()) {
                    oCount++;
                }

                if (grid[j][i] == Characters.Empty.getCharacter()) {
                    blankCount++;

                    if (blankCount >= 2) {
                        break;
                    } else {
                        blankCords[0] = j;
                        blankCords[1] = i;
                    }
                }

                if (!TicTacToe.isOccupiedCoordinate(grid, blankCords[0], blankCords[1])) {
                    if (xCount == 0 && oCount == 2) {
                        return true;
                    }

                    if (xCount == 2 && oCount == 0) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    protected boolean  canBlockOrCompleteColumns(char[][] grid) {
        int xCount, oCount, blankCount;
        int[] blankCords = new int[2];

        for (int i = 0; i < grid.length; i++) {
            xCount = 0;
            oCount = 0;
            blankCount = 0;

            for (int j = 0; j < grid.length; j++) {
                if (grid[j][i] == Characters.X.getCharacter()) {
                    xCount++;
                }

                if (grid[j][i] == Characters.O.getCharacter()) {
                    oCount++;
                }

                if (grid[j][i] == Characters.Empty.getCharacter()) {
                    blankCount++;

                    if (blankCount >= 2) {
                        break;
                    } else {
                        blankCords[0] = j;
                        blankCords[1] = i;
                    }
                }

                if (!TicTacToe.isOccupiedCoordinate(grid, blankCords[0], blankCords[1])) {
                    if (xCount == 0 && oCount == 2) {
                        return true;
                    }

                    if (xCount == 2 && oCount == 0) {
                        return true;
                    }
                }
            }
        }

        return false;
    }


    protected boolean canBlockOrCompleteDiagonal(char[][] grid) {
        int xCount, oCount, blankCount;
        int[] blankCords = new int[2];

        xCount = 0;
        oCount = 0;
        blankCount = 0;

        for (int i = 0; i < grid.length; i++) {
            if (grid[i][i] == Characters.X.getCharacter()) {
                xCount++;
            }

            if (grid[i][i] == Characters.O.getCharacter()) {
                oCount++;
            }

            if (grid[i][i] == Characters.Empty.getCharacter()) {
                blankCount++;

                if (blankCount >= 2) {
                    return false;
                } else {
                    blankCords[0] = i;
                    blankCords[1] = i;
                }
            }
        }

        if (!TicTacToe.isOccupiedCoordinate(grid, blankCords[0], blankCords[1])) {
            if (xCount == 0 && oCount == 2) {
                return true;
            }

            if (xCount == 2 && oCount == 0) {
                return true;
            }
        }

        xCount = 0;
        oCount = 0;
        blankCount = 0;

        for (int i = grid.length - 1, j = 0; i >= 0 && j < grid.length; i--, j++) {
            if (grid[i][j] == Characters.X.getCharacter()) {
                xCount++;
            }

            if (grid[i][j] == Characters.O.getCharacter()) {
                oCount++;
            }

            if (grid[i][j] == Characters.Empty.getCharacter()) {
                blankCount++;

                if (blankCount >= 2) {
                    return false;
                } else {
                    blankCords[0] = i;
                    blankCords[1] = j;
                }
            }

            if (!TicTacToe.isOccupiedCoordinate(grid, blankCords[0], blankCords[1])) {
                if (xCount == 0 && oCount == 2) {
                    return true;
                }

                if (xCount == 2 && oCount == 0) {
                    return true;
                }
            }
        }

        return false;
    }
}
