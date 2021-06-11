package tictactoe;

import tictactoe.Players.EasyAI;
import tictactoe.Players.MediumAI;
import tictactoe.Players.Player;
import tictactoe.Players.User;

public class Factory {
    public static Player playerFactory(String type, char[][] grid, char character) {
        switch (type) {
            case "user":
                return new User(grid, character);
            case "easy":
                return new EasyAI(grid, character);
            case "medium":
                return new MediumAI(grid, character);
            default:
                return null;
        }
    }
}
