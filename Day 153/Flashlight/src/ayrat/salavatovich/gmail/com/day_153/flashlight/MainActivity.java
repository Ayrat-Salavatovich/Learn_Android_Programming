package ayrat.salavatovich.gmail.com.day_153.flashlight;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.view.Menu;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	private Camera camera;
	private ToggleButton toggleButton;
	private Parameters parameters;
	private final int FLASH_NOT_SUPPORTED = 0;
	private final int NO_CAMERA_PARAMETERS = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
		if (checkHasDeviceFlashlight())
			toggleButton.setOnCheckedChangeListener(onCheckedChangeListener);
		else {
			toggleButton.setEnabled(false);
			showDialogFragment(MainActivity.this, FLASH_NOT_SUPPORTED);
		}
	}

	private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			if (isChecked) {
				if (isCameraClosed(camera))
					camera = getCameraInstance();

				if (parameters == null) {
					showDialogFragment(MainActivity.this, NO_CAMERA_PARAMETERS);
				} else {
					List<String> flashModes = parameters
							.getSupportedFlashModes();
					if (flashModes.contains(Parameters.FLASH_MODE_TORCH)) {
						parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
						camera.setParameters(parameters);
						camera.startPreview();
					} else {
						showDialogFragment(MainActivity.this,
								FLASH_NOT_SUPPORTED);
					}
				}
			} else {
				parameters.setFlashMode(Parameters.FLASH_MODE_OFF);
				camera.setParameters(parameters);
				closeCamera(camera);
				camera = null;
			}
		}
	};

	void showDialogFragment(Context context, int dialogId) {
		AlertDialog dialog = new AlertDialog();
		switch (dialogId) {
		case FLASH_NOT_SUPPORTED:
			dialog.setMessage(getString(R.string.alert_support_torch));
			dialog.show(getFragmentManager(), "FLASH_NOT_SUPPORTED");

			break;
		case NO_CAMERA_PARAMETERS:
			dialog.setMessage(getString(R.string.alert_camera_parameters));
			dialog.show(getFragmentManager(), "NO_CAMERA_PARAMETERS");

			break;
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		closeCamera(camera);
		camera = null;
	}

	@Override
	protected void onStart() {
		super.onStart();
		camera = getCameraInstance();
		parameters = camera.getParameters();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private boolean checkHasDeviceFlashlight() {
		return getPackageManager().hasSystemFeature(
				PackageManager.FEATURE_CAMERA_FLASH);
	}

	boolean isCameraInUse() {
		Camera camera = null;
		try {
			camera = getCameraInstance();
		} catch (RuntimeException e) {
			return true;
		} finally {
			closeCamera(camera);
		}
		return false;
	}

	void closeCamera(Camera camera) {
		if (!isCameraClosed(camera)) {
			camera.stopPreview();
			camera.release();
		}
	}

	boolean isCameraClosed(final Camera camera) {
		return (camera == null && !isCameraInUse()) ? true : false;
	}

	public Camera getCameraInstance() {
		if (camera == null)
			return Camera.open();

		return camera;
	}

}
