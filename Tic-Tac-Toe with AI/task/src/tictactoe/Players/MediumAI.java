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

        int[] rowAndColumn = blockOrComplete(super.getGrid(), super.getCharacter());
        if (rowAndColumn[0] != -1 && rowAndColumn[1] != -1) {
            super.getGrid()[rowAndColumn[0]][rowAndColumn[1]] = super.getCharacter();
            return;
        }
        
        rowAndColumn = blockOrComplete(super.getGrid(), TicTacToe.opponentCharacter(super.getCharacter()));
        if (rowAndColumn[0] != -1 && rowAndColumn[1] != -1) {
            super.getGrid()[rowAndColumn[0]][rowAndColumn[1]] = super.getCharacter();
            return;
        }

        super.easyAIMove(super.getGrid(), super.getCharacter());
    }

    private int[] blockOrComplete(char[][] grid, char character) {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (grid[r][c] == Characters.Empty.getCharacter()) {
                    grid[r][c] = character;
                    if (!(TicTacToe.winner(grid).equals("Draw"))) {
                        grid[r][c] = Characters.Empty.getCharacter();
                        return new int[]{r, c};
                    }
                    grid[r][c] = Characters.Empty.getCharacter();
                }
            }
        }
        return new int[]{-1, -1};
    }
}
