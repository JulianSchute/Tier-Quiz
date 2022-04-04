package objects;

import java.util.ArrayList;
import java.util.List;

import data.DataLoader;
import quiz.GameType;

public class Player {
	
	//--properties-------------------------------
	String name;
	
	//-------------------------------------------
	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	//get all scores for the player
	public List<Score> getListOfScores() {
		List<Score> allScores = DataLoader.loadScoreData();
		List<Score> playerScores = new ArrayList<Score>();
		for(int i =0; i < allScores.size();i++) {
			if(allScores.get(i).getPlayer().getName().equals(name)) {
				playerScores.add(allScores.get(i));
			}
		}
		return playerScores;
	}
	
	public Score getAnimalMatchingHighscore() {
		return getHighscoreFromGame(GameType.ANIMALMATCHING);
	}
	public Score getAnimalQuizHighscore() {
		return getHighscoreFromGame(GameType.ANIMALQUIZ);
	}
	public Score getSmallerBiggerHighscore() {
		return getHighscoreFromGame(GameType.SMALLERBIGGER);
	}
	public Score getWhichAnimalIsItHighscore() {
		return getHighscoreFromGame(GameType.WHICHANIMALISIT);
	}
	
	public Score getHighscoreFromGame(GameType gameType) {
		Score returnScore = new Score(this,gameType, -1);
		List<Score> playerScores = this.getListOfScores();
		for(int i =0; i < playerScores.size();i++) {
			if(playerScores.get(i).getGame() == gameType) {
				if(playerScores.get(i).getPoints() > returnScore.getPoints()) {
					returnScore = playerScores.get(i);
				}
			}
		}		
		return returnScore;		
	}
	
}
