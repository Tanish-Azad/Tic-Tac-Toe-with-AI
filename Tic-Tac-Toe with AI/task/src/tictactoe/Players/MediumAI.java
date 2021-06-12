package tictactoe.Players;

import tictactoe.Characters;
import tictactoe.TicTacToe;

public class MediumAI extends EasyAI {
    public MediumAI(char[][] grid, char character) {
        super(grid, character);
    }

    @Override
    public void makeMove() {
        System.out.println("Making move level \"medium\"");

        if (this.canBlockOrComplete(super.getGrid(), super.getCharacter())) {
            this.blockOrComplete(super.getGrid(), super.getCharacter());
        } else {
            super.randomMove(super.getGrid(), super.getCharacter());
        }
    }

    protected boolean canBlockOrComplete(char[][] grid, char character) {
        char[][] cloneGrid = new char[grid.length][grid.length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                cloneGrid[i][j] = grid[i][j];
            }
        }

        char opponentCharacter = TicTacToe.opponentCharacter(character);

        for (int i = 0; i < cloneGrid.length; i++) {
            for (int j = 0; j < cloneGrid.length; j++) {
                if (cloneGrid[i][j] == Characters.Empty.getCharacter()) {
                    cloneGrid[i][j] = character;
                    if (!(TicTacToe.winner(cloneGrid).equals("Draw"))) {
                        return true;
                    }

                    cloneGrid[i][j] = opponentCharacter;
                    if (!(TicTacToe.winner(cloneGrid).equals("Draw"))) {
                        return true;
                    }

                    cloneGrid[i][j] = Characters.Empty.getCharacter();
                }
            }
        }

        return false;
    }

    protected void blockOrComplete(char[][] grid, char character) {
        char opponentCharacter = TicTacToe.opponentCharacter(character);

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == Characters.Empty.getCharacter()) {
                    grid[i][j] = character;
                    if (TicTacToe.winner(grid).equals("Draw")) {
                        grid[i][j] = Characters.Empty.getCharacter();
                    } else {
                        return;
                    }

                    grid[i][j] = opponentCharacter;
                    if (TicTacToe.winner(grid).equals("Draw")) {
                        grid[i][j] = Characters.Empty.getCharacter();
                    } else {
                        grid[i][j] = character;
                        return;
                    }
                }
            }
        }
    }
}
