package ayrat.salavatovich.gmail.com.day_3.noughtsandcrosses;

public abstract class WinnerChecker implements WinnerCheckerInterface {
	
	protected Game game;

    public WinnerChecker(Game game) {
        this.game = game;
    }

	@Override
	public abstract Player checkWinner();

}
