package ayrat.salavatovich.gmail.com.day_87.parcelable;

import java.util.logging.Logger;

import android.util.Log;

public class LogUtils {

	static {
		TAG = Logger.class.getCanonicalName();
		EMPTY = "";
		LOGS_ENABLE = BuildConfig.DEBUG;
	}

	public static void d(final String tag, final String format, Object... args) {
		if (LOGS_ENABLE)
			Log.d(tag, format(format, args));
	}

	public static void d(String tag, String msg, Throwable e) {
		if (LOGS_ENABLE)
			Log.d(tag, msg, e);
	}

	public static void d(String tag, String format, Throwable e, Object... args) {
		if (LOGS_ENABLE)
			Log.d(tag, format(format, args), e);
	}

	public static void v(final String tag, final String format, Object... args) {
		if (LOGS_ENABLE) {
			Log.v(tag, format(format, args));
		}
	}

	public static void v(String tag, String msg, Throwable e) {
		if (LOGS_ENABLE)
			Log.v(tag, msg, e);
	}

	public static void v(String tag, String format, Throwable e, Object... args) {
		if (LOGS_ENABLE)
			Log.v(tag, format(format, args), e);
	}

	public static void i(final String tag, final String format, Object... args) {
		if (LOGS_ENABLE) {
			Log.i(tag, format(format, args));
		}
	}

	public static void i(String tag, String msg, Throwable e) {
		if (LOGS_ENABLE)
			Log.i(tag, msg, e);
	}

	public static void i(String tag, String format, Throwable e, Object... args) {
		if (LOGS_ENABLE)
			Log.i(tag, format(format, args), e);
	}

	public static void w(final String tag, final String format, Object... args) {
		if (LOGS_ENABLE) {
			Log.w(tag, format(format, args));
		}
	}

	public static void w(String tag, String msg, Throwable e) {
		if (LOGS_ENABLE)
			Log.w(tag, msg, e);
	}

	public static void w(String tag, String format, Throwable e, Object... args) {
		if (LOGS_ENABLE)
			Log.w(tag, format(format, args), e);
	}

	public static void e(final String tag, final String format, Object... args) {
		if (LOGS_ENABLE) {
			Log.e(tag, format(format, args));
		}
	}

	public static void e(String tag, String msg, Throwable e) {
		if (LOGS_ENABLE)
			Log.e(tag, msg, e);
	}

	public static void e(String tag, String format, Throwable e, Object... args) {
		if (LOGS_ENABLE)
			Log.e(tag, format(format, args), e);
	}

	private static String format(String format, Object... args) {
		try {
			return String.format(format == null ? EMPTY : format, args);
		} catch (RuntimeException e) {
			w(TAG, "format error. reason=%s, format=%s", e.getMessage(), format);
			return String.format(EMPTY, format);
		}

	}

	private static final String TAG;
	private static final String EMPTY;
	private static final boolean LOGS_ENABLE;

}
