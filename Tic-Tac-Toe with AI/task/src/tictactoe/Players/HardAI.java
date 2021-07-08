package tictactoe.Players;

import tictactoe.Characters;
import tictactoe.TicTacToe;

public class HardAI extends MediumAI {
    public HardAI(char[][] grid, char character) {
        // Constructor of super class MediumAI
        super(grid, character);
    }

    @Override
    public void makeMove() {
        // Calls the calling function of minimax algorithm
        callMinimax(super.getGrid(), super.getCharacter());
    }

    /**
     * Calls the minimax function
     *
     * @param grid Game board
     * @param character Player character
     */
    protected void callMinimax(char[][] grid, char character) {
        int bestScore = -2_000_000_000;
        int bestRow = -1;
        int bestColumn = -1;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == Characters.Empty.getCharacter()) {
                    grid[i][j] = character;

                    int score = minimax(grid, character, 0, false);

                    grid[i][j] = Characters.Empty.getCharacter();

                    if (score > bestScore) {
                        bestScore = score;
                        bestRow = i;
                        bestColumn = j;
                    }
                }
            }
        }

        // Plays the best move
        grid[bestRow][bestColumn] = character;
    }

    /**
     * Minimax is a kind of backtracking algorithm that is used in decision making and game theory to find the optimal
     * move for a player, assuming that your opponent also plays optimally. It is widely used in two player turn-based
     * games such as Tic-Tac-Toe, Backgammon, Mancala, Chess, etc.
     *
     * Check https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-3-tic-tac-toe-ai-finding-optimal-move/
     * for more information
     *
     * @param grid Game board
     * @param character Player character
     * @param depth Depth in the tree
     * @param isMaximizing true = is maximizing player; false = is minimizing player
     * @return score
     */
    protected int minimax(char[][] grid, char character, int depth, boolean isMaximizing) {
        char opponentCharacter = TicTacToe.opponentCharacter(character);

        if (TicTacToe.isGameFinished(grid)) {
            if (TicTacToe.winner(grid).charAt(0) == character) {
                return 10;
            } else if (TicTacToe.winner(grid).charAt(0) == opponentCharacter) {
                return -10;
            } else {
                return  0;
            }
        }

        int bestScore;

        if (isMaximizing) {
            bestScore = -2_000_000_000;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid.length; j++) {
                    if (grid[i][j] == Characters.Empty.getCharacter()) {
                        grid[i][j] = character;

                        int score = minimax(grid, character, depth + 1, false);

                        grid[i][j] = Characters.Empty.getCharacter();

                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
        } else {
            bestScore = 2_000_000_000;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid.length; j++) {
                    if (grid[i][j] == Characters.Empty.getCharacter()) {
                        grid[i][j] = opponentCharacter;

                        int score = minimax(grid, character, depth + 1, true);

                        grid[i][j] = Characters.Empty.getCharacter();

                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
        }

        return bestScore;
    }
}
