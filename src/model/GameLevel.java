package model;

/**
 * This class represents the gameLevel
 *
 * @author urliz
 * @version 1.0
 */
public class GameLevel {

    private int gameLevel;
    private int gameRoom;

    /**
     * Creates a new GameLevel
     *
     * @param gameLevel gameLevel
     * @param gameRoom  gameRoom
     */
    public GameLevel(int gameLevel, int gameRoom) {
        this.gameLevel = gameLevel;
        this.gameRoom = gameRoom;
    }

    /**
     * returns the gameLevel
     *
     * @return gameLevel
     */
    public int getGameLevel() {
        return this.gameLevel;
    }

    /**
     * sets the game level
     *
     * @param newGameLevel gameLevel
     */
    public void setGameLevel(int newGameLevel) {
        this.gameLevel = newGameLevel;
    }

    /**
     * returns the game room
     *
     * @return game room
     */
    public int getGameRoom() {
        return this.gameRoom;
    }

    /**
     * sets the game room
     *
     * @param newGameRoom game room
     */
    public void setGameRoom(int newGameRoom) {
        this.gameRoom = newGameRoom;
    }

}
