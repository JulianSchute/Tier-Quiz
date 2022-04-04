package quiz;

import objects.Player;
import objects.Score;

public abstract class Game extends Page{

	//--properties-------------------------------
	protected GameType type;
	protected Score score;
	protected Player currentPlayer;
	//-------------------------------------------


	public GameType getType() {
		return type;
	}

	protected void setType(GameType type) {
		this.type = type;
	}

	public Game(){

	}	

	protected void saveHighscore() {

	}
	
	protected abstract void generateGame();
	
	
	
	



}
