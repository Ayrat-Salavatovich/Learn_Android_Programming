package ayrat.salavatovich.gmail.com.day_178.simpleongesturelistener;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textViewGestureEvent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		init();
	}

	private void init() {
		textViewGestureEvent = (TextView) findViewById(R.id.textViewGestureEvent);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return gestureDetector.onTouchEvent(event);
	}

	SimpleOnGestureListener simpleongesturelistener = new SimpleOnGestureListener() {

		/**
		 * Двойное касание
		 */
		@Override
		public boolean onDoubleTap(MotionEvent event) {
			output("onDoubleTap: \n" + event.toString());
			return super.onDoubleTap(event);
		}

		/**
		 * Движение по экрану с ускорением
		 */
		@Override
		public boolean onFling(MotionEvent event1, MotionEvent event2,
				float velocityX, float velocityY) {
			output("onFling: \n" + event1.toString() + "\n" + event2.toString()
					+ "\n" + "velocityX= " + String.valueOf(velocityX) + "\n"
					+ "velocityY= " + String.valueOf(velocityY) + "\n");
			return super.onFling(event1, event2, velocityX, velocityY);
		}

		/**
		 * Долгое касание
		 */
		@Override
		public void onLongPress(MotionEvent event) {
			output("onLongPress: \n" + event.toString());
			super.onLongPress(event);
		}

		/**
		 * Одиночное касание
		 */
		@Override
		public boolean onSingleTapConfirmed(MotionEvent event) {
			output("onSingleTapConfirmed: \n" + event.toString());
			return super.onSingleTapConfirmed(event);
		}

		@Override
		public boolean onScroll(MotionEvent event1, MotionEvent event2,
				float distanceX, float distanceY) {
			output("onScroll: \n" + event1.toString() + "\n"
					+ event2.toString() + "\n" + "distanceX= "
					+ String.valueOf(distanceX) + "\n" + "distanceY= "
					+ String.valueOf(distanceY) + "\n");
			return super.onScroll(event1, event2, distanceX, distanceY);
		}

		@Override
		public boolean onSingleTapUp(MotionEvent event) {
			output("onSingleTapUp: \n" + event.toString());
			return super.onSingleTapUp(event);
		}

		@Override
		public void onShowPress(MotionEvent event) {
			output("onShowPress: \n" + event.toString());
			super.onShowPress(event);
		}

		@Override
		public boolean onDown(MotionEvent event) {
			output("event: \n" + event.toString());
			return super.onDown(event);
		}

		@Override
		public boolean onDoubleTapEvent(MotionEvent event) {
			output("onDoubleTapEvent: \n" + event.toString());
			return super.onDoubleTapEvent(event);
		}

	};

	private void output(String info) {
		textViewGestureEvent.setText(info);
	}

	GestureDetector gestureDetector = new GestureDetector(getBaseContext(),
			simpleongesturelistener);

}
