package ayrat.salavatovich.gmail.com.day_3.noughtsandcrosses;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Game game;
	private Button[][] buttons;
	private TableLayout tableLayout;

	public MainActivity() {
		game = new Game();
		buttons = new Button[Game.SIZE_Y][Game.SIZE_X];
		game.start();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		tableLayout = (TableLayout) findViewById(R.id.tableLayout);

		buildGameField();
	}

	private void buildGameField() {
		for (int y = 0; y < Game.SIZE_Y; y++) {
			TableRow row = new TableRow(this);
			for (int x = 0; x < Game.SIZE_X; x++) {
				Button button = new Button(this);
				buttons[y][x] = button;
				button.setOnClickListener(new Listener(x, y));
				row.addView(button, new TableRow.LayoutParams(
						TableLayout.LayoutParams.WRAP_CONTENT,
						TableLayout.LayoutParams.WRAP_CONTENT, 1.f));
			}
			tableLayout.addView(row, new TableLayout.LayoutParams(
					TableLayout.LayoutParams.WRAP_CONTENT,
					TableLayout.LayoutParams.WRAP_CONTENT));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public class Listener implements OnClickListener {

		public Listener(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public void onClick(View view) {
			Button button = (Button) view;
			Player player = game.getCurrentActivePlayer();
			if (makeTurn(x, y))
				button.setText(player.getName());

			Player winner = game.checkWinner();
			if (winner != null)
				gameOver(winner);

			if (game.isFieldFilled())
				gameOver();
		}

		private int x;
		private int y;

	}

	private boolean makeTurn(int x, int y) {
		return game.makeTurn(x, y);
	}

	private void gameOver(Player player) {
		CharSequence text = "Player \"" + player.getName() + "\" won!";
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
		game.reset();
		refresh();
	}

	private void gameOver() {
		CharSequence text = "Draw";
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
		game.reset();
		refresh();
	}

	private void refresh() {
		for (int y = 0; y < Game.SIZE_Y; y++)
			for (int x = 0; x < Game.SIZE_X; x++)
				if (game.getPlayer(x, y) == null) {
					buttons[y][x].setText("");
				} else {
					buttons[y][x].setText(game.getPlayer(x, y).getName());
				}
	}

}
