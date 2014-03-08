package ayrat.salavatovich.gmail.com.day_137.customwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.widget.RemoteViews;

public class MyWidget extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);

		SharedPreferences sharedPreferences = context.getSharedPreferences(
				ConfigActivity.WIDGET_PREFERENCES, Context.MODE_PRIVATE);

		// перебираем все ID экземпляров, которые необходимо обновить
		for (int widgetId : appWidgetIds)
			updateWidget(context, appWidgetManager, sharedPreferences, widgetId);
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		super.onDeleted(context, appWidgetIds);

		// Удаляем Preferences
		Editor editor = context.getSharedPreferences(
				ConfigActivity.WIDGET_PREFERENCES, Context.MODE_PRIVATE).edit();

		for (int widgetId : appWidgetIds) {
			editor.remove(ConfigActivity.getKey(ConfigActivity.WIDGET_TEXT_KEY,
					widgetId));
			editor.remove(ConfigActivity.getKey(
					ConfigActivity.WIDGET_COLOR_KEY, widgetId));
		}

		editor.commit();
	}

	static void updateWidget(final Context context,
			final AppWidgetManager appWidgetManager,
			final SharedPreferences sharedPreferences, final int widgetId) {
		// Читаем параметры Preferences
		final String text = sharedPreferences
				.getString(ConfigActivity.getKey(
						ConfigActivity.WIDGET_TEXT_KEY, widgetId), null);
		if (TextUtils.isEmpty(text))
			return;

		int color = sharedPreferences.getInt(ConfigActivity.getKey(
				ConfigActivity.WIDGET_COLOR_KEY, widgetId), 0);

		// Настраиваем внешний вид виджета с помощью RemoteViews,
		// предназначенного для межпроцессной работы с view.
		RemoteViews view = new RemoteViews(context.getPackageName(),
				R.layout.widget);
		view.setTextViewText(R.id.textView, text);
		view.setInt(R.id.textView, "setBackgroundColor", color);

		// Обновляем виджет
		appWidgetManager.updateAppWidget(widgetId, view);
	}

}
