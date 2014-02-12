package ayrat.salavatovich.gmail.com.day_120.multitouch;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	private final String LINE_SEPARATOR = "\n";
	private final int MAX_POINTERS = 10;

	private TextView textViewContentView;
	private float textSize = 10.f;

	private StringBuilder result = new StringBuilder();

	private boolean inTouch = false;
	private int upActionPointer = 0;
	private int downActionPointer = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		textViewContentView = new TextView(this);
		textViewContentView.setTextSize(textSize);
		textViewContentView.setOnTouchListener(myTouchListener);

		setContentView(textViewContentView);
	}

	private OnTouchListener myTouchListener = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// событие
			final int actionMasked = event.getActionMasked();
			// индекс касания
			final int actionIndex = event.getActionIndex();
			// число касаний
			final int pointerCount = event.getPointerCount();

			switch (actionMasked) {
			case MotionEvent.ACTION_DOWN: // первое касание

				inTouch = true;
				downActionPointer = actionIndex;

				break;

			case MotionEvent.ACTION_POINTER_DOWN: // последующие касания

				downActionPointer = actionIndex;

				break;

			case MotionEvent.ACTION_MOVE: // движение

				result.setLength(0);

				for (int p = 0; p < MAX_POINTERS; p++) {
					if (p < pointerCount)
						result.append(
								getString(
										R.string.action_move,
										getString(R.string.index, p),
										getString(R.string.id,
												event.getPointerId(p)),
										getString(R.string.x, event.getX(p)),
										getString(R.string.y, event.getY(p))))
								.append(LINE_SEPARATOR);
				}

				break;

			case MotionEvent.ACTION_POINTER_UP: // прерывания касаний

				upActionPointer = actionIndex;

				break;

			case MotionEvent.ACTION_UP: // прерывание последнего касания
			case MotionEvent.ACTION_CANCEL:

				result.setLength(0);

				inTouch = false;

				break;

			default:
				break;
			}

			String pointer = getString(R.string.down, downActionPointer)
					+ LINE_SEPARATOR + getString(R.string.up, upActionPointer)
					+ LINE_SEPARATOR;

			if (inTouch)
				pointer += getString(R.string.pointer_count, pointerCount)
						+ LINE_SEPARATOR;

			outputInfo(pointer + result.toString());

			return true;
		}
	};

	private void outputInfo(final String text) {
		textViewContentView.setText(text);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
