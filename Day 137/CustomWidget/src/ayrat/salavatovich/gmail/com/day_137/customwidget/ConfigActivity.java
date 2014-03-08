package ayrat.salavatovich.gmail.com.day_137.customwidget;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class ConfigActivity extends Activity {

	private int widgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
	private Intent resultValue;

	public final static String WIDGET_PREFERENCES = "widget_preferences";
	public final static String WIDGET_TEXT_KEY = "text[%d]";
	public final static String WIDGET_COLOR_KEY = "color[%d]";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// извлекаем ID конфигурируемого виджета из Intent
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		if (extras != null)
			widgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
					AppWidgetManager.INVALID_APPWIDGET_ID);

		// если получен некорректный ID, выходим
		if (widgetId == AppWidgetManager.INVALID_APPWIDGET_ID)
			finish();

		// формируем intent ответа
		resultValue = new Intent();
		resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);

		// отрицательный ответ
		// если пользователь нажмет Назад, система получит ответ, что виджет
		// создавать не надо
		setResult(RESULT_CANCELED, resultValue);

		setContentView(R.layout.config);
	}

	public void onClick(View v) {
		final int selectColor = ((RadioGroup) findViewById(R.id.radioGroupColors))
				.getCheckedRadioButtonId();
		int color = getResources().getColor(R.color.blue);
		switch (selectColor) {
		case R.id.radioButtonBlue:

			color = getResources().getColor(R.color.blue);

			break;

		case R.id.radioButtonGreen:

			color = getResources().getColor(R.color.green);

			break;

		case R.id.radioButtonRed:

			color = getResources().getColor(R.color.red);

			break;

		default:
			color = getResources().getColor(R.color.blue);

		}

		EditText editText = (EditText) findViewById(R.id.editText);

		// Записываем значения с экрана в Preferences
		SharedPreferences sharedPreferences = getSharedPreferences(
				WIDGET_PREFERENCES, MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		editor.putString(getKey(WIDGET_TEXT_KEY, widgetId), editText.getText()
				.toString());
		editor.putInt(getKey(WIDGET_COLOR_KEY, widgetId), color);
		editor.commit();

		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
		MyWidget.updateWidget(this, appWidgetManager, sharedPreferences,
				widgetId);

		// положительный ответ
		setResult(RESULT_OK, resultValue);

		finish();
	}

	public static String getKey(final String format, final int arg) {
		return String.format(format, arg);
	}

}
