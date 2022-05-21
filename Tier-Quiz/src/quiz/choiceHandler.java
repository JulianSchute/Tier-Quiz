package quiz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class choiceHandler {
	
	public static Page recursiveGameChoiceHandler(String input) {
		Page page;
		if(input.equals("1") || input.equals("AnimalMatching")) {
			page = new AnimalMatching();
			return page;
		}
		if(input.equals("2") || input.equals("AnimalQuiz")) {
			page = new AnimalQuiz();
			return page;
		}
		if(input.equals("3") || input.equals("SmallerBigger")) {
			page = new SmallerBigger();
			return page;
		}
		if(input.equals("4") || input.equals("WhichAnimalIsIt")) {
			page = new WhichAnimalIsIt();
			return page;		
		}
		if(input.equals("D") || input.equals("AnimalDesigner")) {
			page = new AnimalDesigner();
			return page;	
		}
		if(input.equals("S") || input.equals("Scoreboard") ) {
			page = new Scoreboard();
			return page;
		}
		if(input.equals("E") || input.equals("Exit") ) {
			page = new Exit();
			return page;
		}

		System.out.println("Wir konnten leider nicht verstehen was du tun möchtest, versuche es nochmal!");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			input = in.readLine();
			System.out.println(input);
			return recursiveGameChoiceHandler(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		page = new AnimalQuiz();
		return page;
	}
	
	public static GameType recursiveScoreChoiceHandler(String input) {
		GameType game = null;
		if(input.equals("1") || input.equals("AnimalMatching")) {
			game = GameType.ANIMALMATCHING;
			return game;
		}
		if(input.equals("2") || input.equals("AnimalQuiz")) {
			game = GameType.ANIMALQUIZ;
			return game;
		}
		if(input.equals("3") || input.equals("SmallerBigger")) {
			game = GameType.SMALLERBIGGER;
			return game;
		}
		if(input.equals("4") || input.equals("WhichAnimalIsIt")) {
			game = GameType.WHICHANIMALISIT;
			return game;		
		}
		System.out.println("Wir konnten leider nicht verstehen was du tun möchtest, versuche es nochmal!");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			input = in.readLine();
			System.out.println(input);
			return recursiveScoreChoiceHandler(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return game;		
	}

}
