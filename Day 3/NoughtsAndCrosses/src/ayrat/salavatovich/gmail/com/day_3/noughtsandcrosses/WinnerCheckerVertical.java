package ayrat.salavatovich.gmail.com.day_3.noughtsandcrosses;

public class WinnerCheckerVertical extends WinnerChecker {

	public WinnerCheckerVertical(Game game) {
		super(game);
	}

	@Override
	public Player checkWinner() {
        Player currPlayer;
        Player lastPlayer = null;
        for (int y = 0; y < Game.SIZE_Y; y++) {
            lastPlayer = null;
            int successCounter = 1;
            for (int x = 0; x < Game.SIZE_X; x++) {
                currPlayer = game.getPlayer(y, x);
                if (currPlayer != null && lastPlayer != null && currPlayer == lastPlayer) {
                    successCounter++;
                    if (successCounter == Game.SIZE_Y)
                        return currPlayer;
                }
                lastPlayer = currPlayer;
            }
        }
        return null;
	}

}
