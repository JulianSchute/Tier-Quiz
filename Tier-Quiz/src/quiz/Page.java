package quiz;

import objects.Player;

public abstract class Page {



	public abstract void startPage(Player player); 

	public abstract void witchPageIsIt();

	public  boolean closePage(Player player) {
		Page page = new Exit();
		page.startPage(player);
		return page.closePage(player);		
	}
}
