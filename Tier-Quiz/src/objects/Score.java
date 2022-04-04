package objects;

import quiz.GameType;

public class Score {

	private Player player;
	private int points;
	private GameType game;
	private int multiplier = 0;

	public Score(Player player, GameType game) {
		this.player = player;
		this.game = game;
		points = 0;
	}
	public Score(Player player, GameType game, int points) {
		this.player = player;
		this.game = game;
		this.points = points;
	}

	public void incPoints() {
		points = points +600 + (multiplier *200);
		if(multiplier < 3) {
			multiplier++;
		}
	}
	
	public void addPoints(int p) {
		this.points = this.points + p;
	}

	public String toString() {		

		return (game.toString()+ ";"+ player.getName() + ";"+ points);
	}

	public String getReadableString() {
		if(points >=0) {
			return (game.toString()+ "- Dein Highscore: "+ points);
		}else {
			return (game.toString()+ "- Dein Highscore: -");
		}
	}

	public void noPoints() {
		multiplier = 0;
	}

	public Player getPlayer() {
		return player;
	}

	public int getPoints() {
		return points;
	}

	public GameType getGame() {
		return game;
	}

}
