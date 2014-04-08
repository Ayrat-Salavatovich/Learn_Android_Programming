package ayrat.salavatovich.gmail.com.day_145.json;

public class Pressure {

	public static double paToMMHg(double pa) {
		return pa / 133.3;
	}

	public static double mmHgToPa(double mmHg) {
		return mmHg * 133.3;
	}

}
