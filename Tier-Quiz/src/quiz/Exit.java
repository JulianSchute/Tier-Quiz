package quiz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import objects.Player;

public class Exit extends Page {

	private boolean willExit = false;
	
	@Override
	public void startPage(Player player) {
		System.out.println("M�chtest du das Spiel verlassen, " + player.getName() + "?");		
		try {
			willExit = this.accept();
			if(willExit == true) {
				System.out.println("Auf Wiedersehen!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void witchPageIsIt() {
		System.out.println("Du hast Exit ausgew�hlt");		
	}

	@Override
	public boolean closePage(Player player) {
		
		return !willExit;
		// TODO Auto-generated method stub
		
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
		System.out.println("Wir konnten leider nicht verstehen was du tun m�chtest, versuche es nochmal!");
		return accept();
	}
}
