package ayrat.salavatovich.gmail.com.day_136.simplewidget;

import java.util.Arrays;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.util.Log;

public class MyWidget extends AppWidgetProvider {

	public static final String TAG = MyWidget.class.getCanonicalName();

	/**
	 * вызывается при удалении каждого экземпляра виджета
	 */
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		Log.d(TAG, "onDeleted " + Arrays.toString(appWidgetIds));
		super.onDeleted(context, appWidgetIds);
	}

	/**
	 * вызывается при удалении последнего экземпляра виджета
	 */
	@Override
	public void onDisabled(Context context) {
		Log.d(TAG, "onDisabled");
		super.onDisabled(context);
	}

	/**
	 * вызывается системой при создании первого экземпляра виджета
	 */
	@Override
	public void onEnabled(Context context) {
		Log.d(TAG, "onEnabled");
		super.onEnabled(context);
	}

	/**
	 * вызывается при обновлении виджета
	 */
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		Log.d(TAG, "onUpdate " + Arrays.toString(appWidgetIds));
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}

}
