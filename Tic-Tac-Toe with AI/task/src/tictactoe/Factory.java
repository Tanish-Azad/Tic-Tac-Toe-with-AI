package tictactoe;

import tictactoe.Players.*;

/**
 * Makes players
 */
public class Factory {
    /**
     * @param type Player type
     * @param grid Game board
     * @param character Player Character
     * @return Player of the wanted type
     */
    public static Player playerFactory(String type, char[][] grid, char character) {
        switch (type) {
            case "user": // Human player
                return new User(grid, character);
            case "easy": // Easy AI
                return new EasyAI(grid, character);
            case "medium": // Medium AI
                return new MediumAI(grid, character);
            case "hard": // Hard AI
                return new HardAI(grid, character);
            default: // No such player, return null
                return null;
        }
    }
}
