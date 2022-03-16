package model;

public class GameLevel {

	private int gameLevel;
	private int gameRoom;

	public GameLevel(int gameLevel, int gameRoom) {
		this.gameLevel = gameLevel;
		this.gameRoom = gameRoom;
	}

	public int getGameLevel() {
		return this.gameLevel;
	}

	public int getGameRoom() {
		return this.gameRoom;
	}

	public void setGameLevel(int newGameLevel) {
		this.gameLevel = newGameLevel;
	}

	public void setGameRoom(int newGameRoom) {
		this.gameRoom = newGameRoom;
	}

}
