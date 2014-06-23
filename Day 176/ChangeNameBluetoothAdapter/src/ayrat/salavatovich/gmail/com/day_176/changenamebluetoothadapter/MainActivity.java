package ayrat.salavatovich.gmail.com.day_176.changenamebluetoothadapter;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	private EditText editTextName;
	private BluetoothAdapter bluetoothAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		init();
	}

	private void init() {
		bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		editTextName = (EditText) findViewById(R.id.editTextName);
		editTextName.setText(getCurrentNameBluetoothAdapter());
	}

	public void setNameBluetoothAdapter(View v) {
		if (bluetoothAdapter.isEnabled())
			bluetoothAdapter.setName(getNewNameBluetoothAdapter());
	}

	private String getNewNameBluetoothAdapter() {
		return editTextName.getText().toString();
	}

	private String getCurrentNameBluetoothAdapter() {
		if (bluetoothAdapter.isEnabled())
			return bluetoothAdapter.getName();

		return "";
	}

}
