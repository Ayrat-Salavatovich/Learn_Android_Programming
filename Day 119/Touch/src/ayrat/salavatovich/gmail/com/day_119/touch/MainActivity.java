package ayrat.salavatovich.gmail.com.day_119.touch;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.MotionEvent.PointerCoords;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	private enum Action {
		ACTION_MOVE(0), ACTION_UP(1), ACTION_DOWN(2);

		private int id;

		Action(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}

		public static final int size = Action.values().length;
	};

	private TextView textViewContentLayout;

	private final PointerCoords pointerCoords = new PointerCoords();
	private final String[] motionEventActions = new String[Action.size];

	private final String EMPTY = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		textViewContentLayout = new TextView(this);
		textViewContentLayout.setOnTouchListener(mTouchListener);

		setContentView(textViewContentLayout);
	}

	private OnTouchListener mTouchListener = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			pointerCoords.x = event.getX();
			pointerCoords.y = event.getY();

			switch (event.getAction()) {
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_CANCEL:

				motionEventActions[Action.ACTION_UP.getId()] = getString(
						R.string.up, pointerCoords.x, pointerCoords.y);
				motionEventActions[Action.ACTION_MOVE.getId()] = EMPTY;

				break;

			case MotionEvent.ACTION_DOWN:

				motionEventActions[Action.ACTION_DOWN.getId()] = getString(
						R.string.down, pointerCoords.x, pointerCoords.y);
				motionEventActions[Action.ACTION_UP.getId()] = EMPTY;
				motionEventActions[Action.ACTION_MOVE.getId()] = EMPTY;

				break;

			case MotionEvent.ACTION_MOVE:

				motionEventActions[Action.ACTION_MOVE.getId()] = getString(
						R.string.move, pointerCoords.x, pointerCoords.y);

				break;

			default:
				break;
			}

			outputInfo();
			return true; // сами обработали событие
		}
	};

	private void outputInfo() {
		textViewContentLayout.setText(getString(R.string.output,
				motionEventActions[Action.ACTION_DOWN.getId()],
				motionEventActions[Action.ACTION_MOVE.getId()],
				motionEventActions[Action.ACTION_UP.getId()]));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
