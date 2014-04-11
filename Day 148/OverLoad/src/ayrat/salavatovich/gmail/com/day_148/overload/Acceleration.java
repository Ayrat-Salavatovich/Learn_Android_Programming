package ayrat.salavatovich.gmail.com.day_148.overload;

import android.hardware.SensorManager;

public class Acceleration {
	public final double STANDARD_GRAVITY = SensorManager.STANDARD_GRAVITY;
	private double calibration;

	private double x;
	private double y;
	private double z;

	public Acceleration() {
	}

	public Acceleration(final double x, final double y, final double z) {
		this(x, y, z, SensorManager.STANDARD_GRAVITY);
	}

	public Acceleration(final double x, final double y, final double z,
			final double calibration) {
		this.x = x;
		this.y = y;
		this.z = z;

		this.calibration = calibration;
	}

	public void setValues(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public double getCalibration() {
		return calibration;
	}

	public void setX(final double x) {
		this.x = x;
	}

	public void setY(final double y) {
		this.y = y;
	}

	public void setZ(final double z) {
		this.z = z;
	}

	public void setCalibration(final double calibration) {
		this.calibration = calibration;
	}

	public double getAcceleration() {
		final double a = Math.round(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)
				+ Math.pow(z, 2)));
		return Math.abs((float) (a - calibration));
	}

	public static Acceleration max(final Acceleration a1, final Acceleration a2) {
		final Acceleration acceleration = new Acceleration();
		if (a1.getAcceleration() >= a2.getAcceleration()) {
			acceleration.setCalibration(a1.getCalibration());
			acceleration.setValues(a1.getX(), a1.getY(), a1.getZ());
		} else {
			acceleration.setCalibration(a2.getCalibration());
			acceleration.setValues(a2.getX(), a2.getY(), a2.getZ());
		}

		return acceleration;
	}

	public double getGravity() {
		return getAcceleration() / STANDARD_GRAVITY;
	}

}
