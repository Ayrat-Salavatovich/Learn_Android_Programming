package ayrat.salavatovich.gmail.com.day_3.noughtsandcrosses;

public class WinnerCheckerDiagonalRight extends WinnerChecker {

	public WinnerCheckerDiagonalRight(Game game) {
		super(game);
	}

	@Override
	public Player checkWinner() {
		Player currPlayer;
		Player lastPlayer = null;
		int successCounter = 1;
		for (int y = 0; y < Game.SIZE_Y; y++) {
			currPlayer = game.getPlayer(Game.SIZE_X - (y + 1), y);
			if (currPlayer != null)
				if (lastPlayer == currPlayer) {
					successCounter++;
					if (successCounter == Game.SIZE_Y)
						return currPlayer;
				}
			lastPlayer = currPlayer;
		}
		return null;
	}

}
