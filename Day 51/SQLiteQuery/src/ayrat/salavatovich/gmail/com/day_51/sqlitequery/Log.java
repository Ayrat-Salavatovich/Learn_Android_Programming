package ayrat.salavatovich.gmail.com.day_51.sqlitequery;

import android.content.Context;
import android.content.Intent;

public class Log {
	public static StringBuilder sb = new StringBuilder();

	public static void append(String text) {
		sb.append(text).append(System.lineSeparator());
	}

	public static void clear() {
		sb = new StringBuilder();
	}

	public static void show(Context context) {
		Intent intent = new Intent(context, OutActivity.class);
		intent.putExtra(MainActivity.RECORDS, sb.toString());
		context.startActivity(intent);
	}
}
