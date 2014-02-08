package ayrat.salavatovich.gmail.com.day_115.intentservicesample;

public enum Operation {
	ADD {
		double eval(double x, double y) {
			return x + y;
		}
	},
	SUBTRACT {
		double eval(double x, double y) {
			return x - y;
		}
	},
	MULTIPLY {
		double eval(double x, double y) {
			return x * y;
		}
	},
	DIVIDE {
		double eval(double x, double y) {
			return x / y;
		}
	};

	abstract double eval(double x, double y);
}
