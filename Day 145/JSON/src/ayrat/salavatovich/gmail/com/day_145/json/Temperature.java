package ayrat.salavatovich.gmail.com.day_145.json;

public class Temperature {

	public static double kelvinToCelsius(double kelvin) {
		return kelvin - 273.16;
	}

	public static double celsiusToKelvin(double celsius) {
		return celsius + 273.16;
	}

	public static double fahrenheitToKelvin(double fahrenheit) {
		return (5 / 9 * (fahrenheit - 32) + 273);
	}

	public static double kelvinToFahrenheit(double kelvin) {
		return (((kelvin - 273) * 9 / 5) + 32);
	}

	public static double fahrenheitToCelsius(double fahrenheit) {
		return (fahrenheit - 32) * 5 / 9;
	}

	public static double celsiusToFahrenheit(double celsius) {
		return (celsius * 9 / 5) + 32;
	}

}
