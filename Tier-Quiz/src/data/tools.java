package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import objects.Score;

public class tools {

	public static void ClearConsole(){
		
    }
	
	public static boolean accept() throws IOException {
		System.out.println("Antworte mit 'j' 'ja' oder 'n' 'nein'!");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String input = in.readLine();
		if(input.equals("j") || input.equals("ja")) {
			return true;
		}
		if(input.equals("n") || input.equals("nein")) {
			return false;
		}
		System.out.println("Wir konnten leider nicht verstehen was du tun möchtest, versuche es nochmal!");
		return accept();
	}
	
	public static void scoreResult(Score score) {
		if(score.getPoints()>600) {
			System.out.println("Herzlichen Glückwunsch, du hast einen Score von " + score.getPoints() +"!");
		}else {
			System.out.println("Du hast einen Score von "+ score.getPoints() +"! Nächstes mal knackst du den Highscore!");
		}
		DataSaver.safeScore(score);
	}
}
