package quiz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import data.DataLoader;
import data.tools;
import objects.Animal;
import objects.Player;
import objects.Score;

public class Main {	


	public static void main(String[] args) {
		System.out.println("Willkommen zum Tier-Quiz!");
		Player player = helloPlayer();
		Boolean willContinue = true;
		while(willContinue) {
			willContinue = programStart(player);
		}
	}

	public static Player helloPlayer() {
		System.out.println("Wie möchtest du heißen?");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			String input = in.readLine();
			System.out.println("Bist du zufrieden mit dem Namen " + input + "?");
			if(tools.accept()==true) {
				Player player  = new Player(input);
				return player;
			}else {
				return helloPlayer();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Player("Niemand");
	}



	public static boolean programStart(Player player) {
		Page page;

		System.out.println("Hallo " + player.getName()+ " Welches Spiel möchtest du starten?\n 1-AnimalMatching\n 2-AnimalQuiz\n 3-SmallerBigger\n 4-WhichAnimalIsIt");
		System.out.println("Oder wähle: \n S-Scoreboard \n D-AnimalDesigner \n E-Exit");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			String input = in.readLine();
			System.out.println(input);			
			page = choiceHandler.recursiveGameChoiceHandler(input);
			System.out.println(page.witchPageIsIt());
			page.startPage(player);
			return page.closePage(player);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return true;
	}



}
