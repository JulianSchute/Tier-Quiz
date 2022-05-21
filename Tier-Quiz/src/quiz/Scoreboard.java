package quiz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import data.DataLoader;
import objects.Player;
import objects.Score;

public class Scoreboard extends Page {

	@Override
	public void startPage(Player player) {
		showYourScores(player);

	}

	@Override
	public String witchPageIsIt() {
		return "Du hast Scoreboard ausgewählt";

	}


	public void showYourScores(Player player) {
		System.out.println("Deine Persönlichen Highscores");
		System.out.println();
		System.out.println(player.getAnimalMatchingHighscore().getReadableString());
		System.out.println(player.getAnimalQuizHighscore().getReadableString());
		System.out.println(player.getSmallerBiggerHighscore().getReadableString());
		System.out.println(player.getWhichAnimalIsItHighscore().getReadableString());
		GameType game = this.moreScoreboard(player);
		List<Score> scores = getGameScores(game);
		for(int i = 0; i<scores.size();i++) {
			if(scores.get(i).getPlayer().getName().equals(  player.getName())) {
				System.out.println("---" +scores.get(i).toString());

			}else {
				System.out.println(scores.get(i).toString());
			}
		}
	}

	//Ask wich Game is intersting and shows all scores to the game, should highlight player scores with -- 
	public GameType moreScoreboard(Player player) {
		String input;
		System.out.println();
		System.out.println("Von welchem Spiel möchtest du alle Scores sehen? \n 1-AnimalMatching\n 2-AnimalQuiz\n 3-SmallerBigger\n 4-WhichAnimalIsIt");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			input = in.readLine();
			System.out.println(input);
			return choiceHandler.recursiveScoreChoiceHandler(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Score> getGameScores(GameType game){
		Player dummyPlayer = new Player("NoData");
		List<Score> returnScores = new ArrayList<Score>();

		List<Score> playerScores = DataLoader.loadScoreData();
		for(int i =0; i < playerScores.size();i++) {
			if(playerScores.get(i).getGame() == game) {
				returnScores.add(playerScores.get(i));
			}
		}	
		if(returnScores.size() ==0) {
			returnScores.add(new Score(dummyPlayer,game, -1));
		}
		return returnScores;		
	}



	
}
