package ayrat.salavatovich.gmail.com.day_107.servicestop;

import android.os.Build;

public abstract class Log {

	public static final int VERBOSE = android.util.Log.VERBOSE;

	public static final int DEBUG = android.util.Log.DEBUG;

	public static final int INFO = android.util.Log.INFO;

	public static final int WARN = android.util.Log.WARN;

	public static final int ERROR = android.util.Log.ERROR;

	public static final int ASSERT = android.util.Log.ASSERT;

	public static int v(String tag, String msg) {
		return android.util.Log.v(tag, msg);
	}

	public static int v(String tag, String msg, Throwable tr) {
		return android.util.Log.v(tag, msg, tr);
	}

	public static int d(String tag, String msg) {
		return android.util.Log.d(tag, msg);
	}

	public static int d(String tag, String msg, Throwable tr) {
		return android.util.Log.d(tag, msg, tr);
	}

	public static int i(String tag, String msg) {
		return android.util.Log.i(tag, msg);
	}

	public static int i(String tag, String msg, Throwable tr) {
		return android.util.Log.i(tag, msg, tr);
	}

	public static int w(String tag, String msg) {
		return android.util.Log.w(tag, msg);
	}

	public static int w(String tag, String msg, Throwable tr) {
		return android.util.Log.w(tag, msg, tr);
	}

	public static boolean isLoggable(String tag, int level) {
		return android.util.Log.isLoggable(tag, level);
	}

	public static int w(String tag, Throwable tr) {
		return android.util.Log.w(tag, tr);
	}

	public static int e(String tag, String msg) {
		return android.util.Log.e(tag, msg);
	}

	public static int e(String tag, String msg, Throwable tr) {
		return android.util.Log.e(tag, msg, tr);
	}

	public static int wtf(String tag, String msg) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
			return android.util.Log.wtf(tag, msg, null);
		} else {
			return android.util.Log.e(tag, msg);
		}
	}

	public static int wtf(String tag, Throwable tr) {
		return wtf(tag, tr.getMessage(), tr);
	}

	public static int wtf(String tag, Object msg, Throwable tr) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
			return android.util.Log.wtf(tag, "" + msg, tr);
		} else {
			return android.util.Log.e(tag, "" + msg, tr);
		}
	}

}
