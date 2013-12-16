package ayrat.salavatovich.gmail.com.day_53.sqlitetransaction;

public class Log {
	public static StringBuilder sb = new StringBuilder();

	public static void append(String text) {
		sb.append(text).append(System.lineSeparator());
	}

	public static void clear() {
		sb = new StringBuilder();
	}

	public static String trace() {
		return sb.toString();
	}

}
