/**
 * Copyright 2013 Maarten Pennings
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * If you use this software in a product, an acknowledgment in the product
 * documentation would be appreciated but is not required.
 */

package ayrat.salavatovich.gmail.com.day_164.customkeyboard;

import android.app.Activity;
import android.app.Fragment;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.text.Editable;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class CustomKeyboard {

	/** A link to the KeyboardView that is used to render this CustomKeyboard. */
	private KeyboardView keyboardView;
	/** A link to the activity that hosts the {@link #keyboardView}. */
	private Activity hostActivity;
	private View rootView;

	/** The key (code) handler. */
	private OnKeyboardActionListener onKeyboardActionListener = new OnKeyboardActionListener() {

		public final static int CODE_DELETE = -5; // Keyboard.KEYCODE_DELETE
		public final static int CODE_CANCEL = -3; // Keyboard.KEYCODE_CANCEL
		// Для клавиш, которые не имеют кода, например, перемещение курсора,
		// нужно создать свои собственные коды, начиная с числа 55000 и выше.
		public final static int CODE_PREV = 55000;
		public final static int CODE_ALL_LEFT = 55001;
		public final static int CODE_LEFT = 55002;
		public final static int CODE_RIGHT = 55003;
		public final static int CODE_ALL_RIGHT = 55004;
		public final static int CODE_NEXT = 55005;
		public final static int CODE_CLEAR = 55006;

		/**
		 * Вызывается когда пользователь нажимает клавишу
		 */
		@Override
		public void onKey(int primaryCode, int[] keyCodes) {
			// NOTE We can say '<Key android:codes="49,50" ... >' in the xml
			// file; all codes come in keyCodes, the first in this list in
			// primaryCode
			// Get the EditText and its Editable
			View focusCurrent = hostActivity.getWindow().getCurrentFocus();
			if (focusCurrent == null
					|| focusCurrent.getClass() != EditText.class)
				return;
			EditText editText = (EditText) focusCurrent;
			Editable editable = editText.getText();
			int start = editText.getSelectionStart();
			// Apply the key to the edittext
			if (primaryCode == CODE_CANCEL) {
				hideCustomKeyboard();
			} else if (primaryCode == CODE_DELETE) {
				if (notNull(editable) && start > 0)
					editable.delete(start - 1, start);
			} else if (primaryCode == CODE_CLEAR) {
				if (notNull(editable))
					editable.clear();
			} else if (primaryCode == CODE_LEFT) {
				if (start > 0)
					editText.setSelection(start - 1);
			} else if (primaryCode == CODE_RIGHT) {
				if (start < editText.length())
					editText.setSelection(start + 1);
			} else if (primaryCode == CODE_ALL_LEFT) {
				editText.setSelection(0);
			} else if (primaryCode == CODE_ALL_RIGHT) {
				editText.setSelection(editText.length());
			} else if (primaryCode == CODE_PREV) {
				View focusNew = editText.focusSearch(View.FOCUS_BACKWARD);
				if (notNull(focusNew))
					focusNew.requestFocus();
			} else if (primaryCode == CODE_NEXT) {
				View focusNew = editText.focusSearch(View.FOCUS_FORWARD);
				if (notNull(focusNew))
					focusNew.requestFocus();
			} else { // вставка cимвола
				// система попытается вызвать нужный символ, считывая атрибут
				// android:keyLabel. Если в атрибуте находится слово, то
				// используется его первый символ.
				editable.insert(start, Character.toString((char) primaryCode));
			}
		}

		@Override
		public void onPress(int primaryCode) {
		}

		@Override
		public void onRelease(int primaryCode) {
		}

		@Override
		public void onText(CharSequence text) {
		}

		@Override
		public void swipeDown() {
		}

		@Override
		public void swipeLeft() {
		}

		@Override
		public void swipeRight() {
		}

		@Override
		public void swipeUp() {
		}

	};

	/**
	 * Create a custom keyboard, that uses the KeyboardView (with resource id
	 * <var>viewid</var>) of the <var>host</var> activity, and load the keyboard
	 * layout from xml file <var>layoutid</var> (see {@link Keyboard} for
	 * description). Note that the <var>host</var> activity must have a
	 * <var>KeyboardView</var> in its layout (typically aligned with the bottom
	 * of the activity). Note that the keyboard layout xml file may include key
	 * codes for navigation; see the constants in this class for their values.
	 * Note that to enable EditText's to use this custom keyboard, call the
	 * {@link #registerEditText(int)}.
	 * 
	 * @param host
	 *            The hosting activity.
	 * @param viewid
	 *            The id of the KeyboardView.
	 * @param layoutId
	 *            The id of the xml file containing the keyboard layout.
	 */
	public CustomKeyboard(Activity host, View view, int viewid, int layoutId) {
		hostActivity = host;
		rootView = view;
		// Cоздаем клавиатуру из ресурсов
		final Keyboard keyboard = new Keyboard(hostActivity, layoutId);
		keyboardView = (KeyboardView) rootView.findViewById(viewid);
		// Присоединяем клавиатуру к KeyboardView
		keyboardView.setKeyboard(keyboard);
		keyboardView.setPreviewEnabled(false); // NOTE Do not show the preview
												// balloons
		keyboardView.setOnKeyboardActionListener(onKeyboardActionListener);
		// Hide the standard keyboard initially
		hostActivity.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	}

	/** Returns whether the CustomKeyboard is visible. */
	public boolean isCustomKeyboardVisible() {
		return keyboardView.getVisibility() == View.VISIBLE;
	}

	/**
	 * Make the CustomKeyboard visible, and hide the system keyboard for view v.
	 */
	public void showCustomKeyboard(View v) {
		keyboardView.setVisibility(View.VISIBLE);
		keyboardView.setEnabled(true);
		if (notNull(v))
			((InputMethodManager) hostActivity
					.getSystemService(Activity.INPUT_METHOD_SERVICE))
					.hideSoftInputFromWindow(v.getWindowToken(), 0);
	}

	/** Make the CustomKeyboard invisible. */
	public void hideCustomKeyboard() {
		keyboardView.setVisibility(View.GONE);
		keyboardView.setEnabled(false);
	}

	/**
	 * Register <var>EditText<var> with resource id <var>resid</var> (on the
	 * hosting activity) for using this custom keyboard.
	 * 
	 * @param resid
	 *            The resource id of the EditText that registers to the custom
	 *            keyboard.
	 */
	public void registerEditText(int resid) {
		// Find the EditText 'resid'
		EditText editText = (EditText) rootView.findViewById(resid);
		// Make the custom keyboard appear
		editText.setOnFocusChangeListener(new OnFocusChangeListener() {
			// NOTE By setting the on focus listener, we can show the custom
			// keyboard when the edit box gets focus, but also hide it when the
			// edit box loses focus
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus)
					showCustomKeyboard(v);
				else
					hideCustomKeyboard();
			}
		});
		editText.setOnClickListener(new OnClickListener() {
			// NOTE By setting the on click listener, we can show the custom
			// keyboard again, by tapping on an edit box that already had focus
			// (but that had the keyboard hidden).
			@Override
			public void onClick(View v) {
				showCustomKeyboard(v);
			}
		});
		// Disable standard keyboard hard way
		// NOTE There is also an easy way:
		// 'edittext.setInputType(InputType.TYPE_NULL)' (but you will not have a
		// cursor, and no 'edittext.setCursorVisible(true)' doesn't work )
		editText.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				EditText edittext = (EditText) v;
				int inType = edittext.getInputType(); // Backup the input type
				edittext.setInputType(InputType.TYPE_NULL); // Disable standard
															// keyboard
				edittext.onTouchEvent(event); // Call native handler
				edittext.setInputType(inType); // Restore input type
				return true; // Consume touch event
			}
		});
		// Disable spell check (hex strings look like words to Android)
		editText.setInputType(editText.getInputType()
				| InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
	}

	public static boolean notNull(Object obj) {
		if (obj == null) {
			return false;
		}

		return true;
	}

}
