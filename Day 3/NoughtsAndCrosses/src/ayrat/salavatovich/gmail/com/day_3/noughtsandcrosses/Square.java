package ayrat.salavatovich.gmail.com.day_3.noughtsandcrosses;

public class Square {

	public void fill(Player player) {
		this.player = player;
	}

	public boolean isFilled() {
		if (null != getPlayer())
			return true;

		return false;
	}

	public Player getPlayer() {
		return player;
	}

	private Player player;

}
