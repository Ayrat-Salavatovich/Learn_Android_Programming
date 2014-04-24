package ayrat.salavatovich.gmail.com.day_167.eggtimer;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.TypedArray;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NumberPickerView extends LinearLayout {

	public static interface OnChangeListener {
		void onChange(View v, int oldValue, int newValue);
	}

	private static class DisableRunnable implements Runnable {
		private View _view;
		private boolean _enabled;

		DisableRunnable(View v, boolean enabled) {
			_view = v;
			_enabled = enabled;
		}

		public void run() {
			_view.setPressed(false);
			_view.clearFocus();
			_view.setEnabled(_enabled);
		}
	}

	private static class HotsRunnable implements Runnable {
		private NumberPickerView _view;
		private int _value;

		HotsRunnable(NumberPickerView v, int value) {
			_view = v;
			_value = value;
		}

		public void run() {
			if (_view.getNumber() == _value)
				_view.addHot();
		}
	}

	private class ChangerRunnable implements Runnable {
		private int _addValue;

		ChangerRunnable(int addValue) {
			_addValue = addValue;
		}

		public void run() {
			select(_current + _addValue);
			if (_current + _addValue * _interval >= _min
					&& _current + _addValue * _interval <= _max)
				_handler.postDelayed(this, DEFAULT_DELAY);
		}
	}

	private static final int DEFAULT_DELAY = 300;
	private static Handler _handler = new Handler();
	private static int _sHotSystems[];
	private static int _sLastSystems[];

	private TextView _hintView;
	private TextView _numberView;
	private ImageButton _downButton;
	private ImageButton _upButton;

	private int _min, _max;
	private int _interval;
	private int _current;

	private OnChangeListener _onChangeListener;
	private ChangerRunnable _upChanger = new ChangerRunnable(1);
	private ChangerRunnable _downChanger = new ChangerRunnable(-1);

	public NumberPickerView(Context context) {
		super(context);
		initialize(context, null);
	}

	public NumberPickerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize(context, attrs);
	}

	private void initialize(Context context, AttributeSet attrs) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.number_picker, this, true);

		_hintView = (TextView) findViewById(R.id.textViewHint);
		_numberView = (TextView) findViewById(R.id.textViewNumber);
		_downButton = (ImageButton) findViewById(R.id.imageButtonNumberDown);
		_upButton = (ImageButton) findViewById(R.id.imageButtonNumberUp);

		TypedArray params = context.obtainStyledAttributes(attrs,
				R.styleable.NumberPickerView);

		String hint = params
				.getString(R.styleable.NumberPickerView_android_hint);
		if (hint != null)
			_hintView.setText(hint);

		setInterval(params.getInteger(R.styleable.NumberPickerView_interval, 1));
		setRange(params.getInteger(R.styleable.NumberPickerView_min, 0),
				params.getInteger(R.styleable.NumberPickerView_max, 99));

		params.recycle();

		_downButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					_handler.postDelayed(_downChanger, DEFAULT_DELAY);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					select(_current - 1);
					_handler.removeCallbacks(_downChanger);
					_handler.postDelayed(new HotsRunnable(
							NumberPickerView.this, _current), 3000);
				}

				return false;
			}
		});

		_upButton.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					_handler.postDelayed(_upChanger, DEFAULT_DELAY);
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					select(_current + 1);
					_handler.removeCallbacks(_upChanger);
					_handler.postDelayed(new HotsRunnable(
							NumberPickerView.this, _current), 3000);
				}

				return false;
			}
		});

		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(context);
		if (_sHotSystems == null) {
			_sHotSystems = new int[35];
			if (pref != null) {
				for (int i = 0; i < _sHotSystems.length; i++)
					_sHotSystems[i] = pref.getInt("hots" + (i + 2), 0);
			}
		}
		if (_sLastSystems == null) {
			_sLastSystems = new int[10];
			if (pref != null) {
				for (int i = 0; i < _sLastSystems.length; i++)
					_sLastSystems[i] = pref.getInt("last" + i, 0);
			}
		}
	}

	public int getNumber() {
		return _current;
	}

	public void setRange(int min, int max) {
		_min = min;
		_max = max;

		if (_max < _min) {
			int temp = _max;
			_max = _min;
			_min = temp;
		}

		select(_current);
	}

	public void setInterval(int interval) {
		_interval = interval;
		if (_interval < 1)
			_interval = 1;
	}

	public void select(int number) {
		int oldValue = _current;

		if (number < _min)
			_current = _min;
		else if (number > _max)
			_current = _max;
		else
			_current = number;
		if (_interval > 1)
			_current -= (_current - _min) % _interval;
		_numberView.setText(String.valueOf(_current));

		if (_onChangeListener != null)
			_onChangeListener.onChange(this, oldValue, _current);

		_handler.postDelayed(new DisableRunnable(_upButton, _current
				+ _interval <= _max), 50);
		_handler.postDelayed(new DisableRunnable(_downButton, _current
				- _interval >= _min), 50);
	}

	public void setEnabled(boolean enabled) {
		_numberView.setEnabled(enabled);
		_upButton.setEnabled(enabled && _current + _interval <= _max);
		_downButton.setEnabled(enabled && _current - _interval >= _min);
	}

	public void setHint(CharSequence hint) {
		_hintView.setText(hint);
	}

	public void setOnChangeListener(OnChangeListener l) {
		_onChangeListener = l;
	}

	private void addHot() {
		addHot(getContext(), _current);
	}

	private static void addHot(Context context, int system) {
		if (system < 2 || system > 36)
			return;

		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		boolean fixHots = prefs.getBoolean("hots_fix", true);
		if (fixHots
				&& (system == 2 || system == 8 || system == 10 || system == 16))
			return;

		int systemsAction = Integer.valueOf(prefs.getString("systems_action",
				"1"));

		if (systemsAction == 1) {
			_sHotSystems[system - 2]++;
			prefs.edit().putInt("hots" + system, _sHotSystems[system - 2])
					.commit();
			return;
		} else if (systemsAction == 2) {
			for (int i : _sLastSystems)
				if (i == system)
					return;

			for (int i = 0; i < _sLastSystems.length; i++)
				if (_sLastSystems[i] == 0) {
					_sLastSystems[i] = system;
					prefs.edit().putInt("last" + i, system).commit();
					return;
				}

			for (int i = 0; i < _sLastSystems.length - 1; i++) {
				_sLastSystems[i] = _sLastSystems[i + 1];
			}

			_sLastSystems[_sLastSystems.length - 1] = system;

			Editor editor = prefs.edit();
			for (int i = 0; i < _sLastSystems.length; i++) {
				editor.putInt("last" + i, _sLastSystems[i]);
			}
			editor.commit();
		} else
			return;
	}
	
	public void setSolidRightStyle(boolean yes) {
		_upButton
				.setBackgroundResource(yes ? R.drawable.solid_button_background
						: R.drawable.right_button_background);
		LinearLayout.LayoutParams lp = null;
		if (yes)
			lp = new LinearLayout.LayoutParams(getResources()
					.getDimensionPixelSize(R.dimen.number_picker_button_slim),
					LinearLayout.LayoutParams.WRAP_CONTENT);
		else
			lp = new LinearLayout.LayoutParams(getResources()
					.getDimensionPixelSize(R.dimen.number_picker_button),
					LinearLayout.LayoutParams.WRAP_CONTENT);
		_upButton.setLayoutParams(lp);
		_downButton.setLayoutParams(lp);
	}

}
