package tictactoe;

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
