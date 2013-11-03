package ayrat.salavatovich.gmail.com.day_8.createcontextmenu;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	public static final int IDM_OPEN = 101;
	public static final int IDM_SAVE = 102;

	private TextView textViewEvent;
	private TextView textViewColor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		textViewEvent = (TextView) findViewById(R.id.textViewEvent);
		textViewColor = (TextView) findViewById(R.id.textViewColor);
		registerForContextMenu(textViewEvent);
		registerForContextMenu(textViewColor);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		switch (v.getId()) {
		case R.id.textViewEvent:
			menu.add(Menu.NONE, IDM_OPEN, Menu.NONE, R.string.title_open);
			menu.add(Menu.NONE, IDM_SAVE, Menu.NONE, R.string.title_save);
			break;
		case R.id.textViewColor:
			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.context_menu, menu);
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		String message = getResources().getString(R.string.select_menu_item);
		switch (item.getItemId()) {
		case IDM_OPEN:
			message += " " + getResources().getString(R.string.title_open);
			break;
		case IDM_SAVE:
			message += " " + getResources().getString(R.string.title_open);
			break;
		case R.id.change_color:
			int tempColor = ((ColorDrawable) textViewColor.getBackground())
					.getColor();
			textViewColor.setBackgroundColor(((ColorDrawable) textViewEvent
					.getBackground()).getColor());
			textViewEvent.setBackgroundColor(tempColor);
			message = getResources().getString(R.string.title_change_color);
			break;
		case R.id.change_textsize:
			float tempFontSizeTextViewColor = textViewColor.getTextSize();
			textViewColor.setTextSize(TypedValue.COMPLEX_UNIT_PX,
					textViewEvent.getTextSize());
			textViewEvent.setTextSize(TypedValue.COMPLEX_UNIT_PX,
					tempFontSizeTextViewColor);
			message = getResources().getString(R.string.title_change_text_size);
			break;
		default:
			return super.onContextItemSelected(item);
		}
		Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
		return true;
	}

}
