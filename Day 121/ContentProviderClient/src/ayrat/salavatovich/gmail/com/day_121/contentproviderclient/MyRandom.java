package ayrat.salavatovich.gmail.com.day_121.contentproviderclient;

import java.util.Random;

public class MyRandom {
	final static String alphabet = "abcdefghijklmnopqrstuvwxyz";
	final static Random rand = new Random();

	public static String randomString(final int length) {
		StringBuilder name = new StringBuilder();

		for (int i = 0; i < length; i++) {
			char c = alphabet.charAt(randomInt(alphabet.length()));
			name.append(c);
		}

		return name.toString();
	}

	public static String randomName(final int length) {
		return capitalizeFirstLetter(randomString(length));
	}

	public static String capitalizeFirstLetter(String original) {
		if (original.length() == 0)
			return original;

		return original.substring(0, 1).toUpperCase() + original.substring(1);
	}

	public static String randomEmail() {
		return randomString(randomInt(5) + 5) + "@"
				+ randomString(randomInt(3) + 3) + "."
				+ randomString(randomInt(2) + 2);
	}

	public static int randomInt() {
		return rand.nextInt();
	}

	public static int randomInt(int n) {
		return rand.nextInt(n);
	}
}
