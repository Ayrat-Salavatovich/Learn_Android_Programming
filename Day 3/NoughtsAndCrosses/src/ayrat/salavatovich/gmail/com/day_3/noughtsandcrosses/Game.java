package ayrat.salavatovich.gmail.com.day_3.noughtsandcrosses;

public class Game {

	public static final int COUNT_PLAYERS = 2;
	public static final int SIZE_X = 3;
	public static final int SIZE_Y = 3;

	public Game() {
		field = new Square[SIZE_Y][SIZE_X];
		fillField();
		players = new Player[COUNT_PLAYERS];
		initLinesmans();
	}

	private void initLinesmans() {
		winnerCheckers = new WinnerCheckerInterface[Checker.size()];
		winnerCheckers[Checker.HORIZONTAL.getValue()] = new WinnerCheckerHorizontal(
				this);
		winnerCheckers[Checker.VERTICAL.getValue()] = new WinnerCheckerVertical(
				this);
		winnerCheckers[Checker.DIAGONAL_LEFT.getValue()] = new WinnerCheckerDiagonalLeft(
				this);
		winnerCheckers[Checker.DIAGONAL_RIGHT.getValue()] = new WinnerCheckerDiagonalRight(
				this);
	}

	public Player checkWinner() {
		for (WinnerCheckerInterface winChecker : winnerCheckers) {
			Player winner = winChecker.checkWinner();
			if (winner != null)
				return winner;
		}
		return null;
	}

	public Player getPlayer(int x, int y) {
		return field[y][x].getPlayer();
	}

	private void fillField() {
		for (int y = 0; y < SIZE_Y; y++)
			for (int x = 0; x < SIZE_X; x++)
				field[y][x] = new Square();
	}

	private void switchPlayers() {
		setCurrentActivePlayer((getCurrentActivePlayer() == players[0]) ? players[1]
				: players[0]);
	}

	public boolean makeTurn(int x, int y) {
		if (field[x][y].isFilled())
			return false;

		field[x][y].fill(getCurrentActivePlayer());
		filled++;
		switchPlayers();
		return true;
	}

	public Player getCurrentActivePlayer() {
		return activePlayer;
	}

	public void reset() {
		resetField();
		resetPlayers();
	}

	private void resetField() {
		for (int y = 0; y < SIZE_Y; y++)
			for (int x = 0; x < SIZE_X; x++)
				field[y][x].fill(null);
		filled = 0;
	}

	public void start() {
		resetPlayers();
	}

	private void resetPlayers() {
		players[0] = new Player("X");
		players[1] = new Player("O");
		setCurrentActivePlayer(players[0]);
	}

	private void setCurrentActivePlayer(Player player) {
		activePlayer = player;
	}

	public boolean isFieldFilled() {
		return squareCount == filled;
	}

	private final int squareCount = SIZE_X * SIZE_Y;
	private Square[][] field;
	private Player[] players;
	private Player activePlayer;
	private int filled;
	private WinnerCheckerInterface[] winnerCheckers;

	private enum Checker {
		HORIZONTAL(0), VERTICAL(1), DIAGONAL_LEFT(2), DIAGONAL_RIGHT(3);

		public int getValue() {
			return value;
		}

		public static int size() {
			return values().length;
		}

		private int value;

		private Checker(int value) {
			this.value = value;
		}

	};

}
