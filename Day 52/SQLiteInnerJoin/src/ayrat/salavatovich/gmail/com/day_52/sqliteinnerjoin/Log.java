package ayrat.salavatovich.gmail.com.day_52.sqliteinnerjoin;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Log {

	public final static String LOG_TAG = "ayrat.salavatovich.gmail.com.day_52.sqliteinnerjoin.LOG";
	public static StringBuilder sb = new StringBuilder();

	public static void append(String text) {
		sb.append(text).append(System.lineSeparator());
	}

	public static void clear() {
		sb = new StringBuilder();
	}

	public static void trace(Context context, Class<?> cls) {
		trace(context, cls, sb.toString());
	}

	public static void trace(Context context, Class<?> cls, String message) {
		Intent intent = new Intent(context, cls);
		intent.putExtra(OutputActivity.RECORDS, message);
		context.startActivity(intent);
	}

	public static void trace(Context context, String action) {
		trace(context, action, sb.toString());
	}

	public static void trace(Context context, String action, String message) {
		Intent intent = new Intent(action);
		intent.putExtra(OutputActivity.RECORDS, message);
		context.startActivity(intent);
	}

	public static void info(Context context) {
		info(context, sb.toString());
	}

	public static void info(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}

	public static void debug(Context context) {
		debug(context, sb.toString());
	}

	public static void debug(Context context, String msg) {
		android.util.Log.w(LOG_TAG, msg);
	}
}
