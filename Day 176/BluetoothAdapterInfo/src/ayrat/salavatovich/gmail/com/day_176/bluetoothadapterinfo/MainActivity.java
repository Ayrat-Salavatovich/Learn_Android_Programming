package ayrat.salavatovich.gmail.com.day_176.bluetoothadapterinfo;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	private TextView textViewOutput;
	private ToggleButton toggleButtonOnOff;
	private BluetoothAdapter bluetoothAdapter;
	final String NEW_LINE = "\n";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		init();
		registerReceivers();
	}

	private void init() {
		textViewOutput = (TextView) findViewById(R.id.textViewOutput);
		bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		toggleButtonOnOff = (ToggleButton) findViewById(R.id.toggleButtonOnOff);
		toggleButtonOnOff.setChecked(bluetoothAdapter.isEnabled());
	}

	private void registerReceivers() {
		IntentFilter filter = new IntentFilter(
				BluetoothAdapter.ACTION_STATE_CHANGED);
		registerReceiver(broadcastReceiver, filter);
	}

	private void unregisterReceiver() {
		unregisterReceiver(broadcastReceiver);
	}

	public void getState(View v) {
		cleanInfoOutput();
		appendInfoOutput(getBluetoothAdapterInfoState());
		appendInfoOutput(getBluetoothAdapterName());
		appendInfoOutput(getBluetoothAdapterAddress());
		appendInfoOutput(getBluetoothAdapterScanMode());
	}

	private void cleanInfoOutput() {
		textViewOutput.setText("");
	}

	private void appendInfoOutput(final String text) {
		String currentInfo = textViewOutput.getText().toString();
		textViewOutput.setText(currentInfo + NEW_LINE + text);
	}

	private String getBluetoothAdapterInfoState() {
		if (bluetoothAdapter == null) {
			return "Bluetooth на вашем устройстве не поддерживается";
		} else {
			if (bluetoothAdapter.isEnabled()) {
				if (bluetoothAdapter.isDiscovering()) {
					return "Bluetooth в процессе включения.";
				} else {
					return "Bluetooth доступен.";
				}
			} else {
				return "Bluetooth не доступен!";
			}
		}
	}

	private String getBluetoothAdapterName() {
		String result = "Имя устройства: ";
		if (bluetoothAdapter.isEnabled())
			return result + bluetoothAdapter.getName();

		return result;
	}

	public void turnOnOff(View v) {
		if (toggleButtonOnOff.isChecked()) {
			bluetoothAdapter.enable();
		} else {
			bluetoothAdapter.disable();
		}
		if (broadcastReceiver.isOrderedBroadcast())
			unregisterReceiver();
		registerReceivers();
	}

	private String getBluetoothAdapterAddress() {
		String result = "Адрес устройства: ";
		if (bluetoothAdapter.isEnabled())
			return result + bluetoothAdapter.getAddress();

		return result;
	}

	private String getBluetoothAdapterScanMode() {
		String result = "Режим сканирования: ";
		switch (bluetoothAdapter.getScanMode()) {
		case BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE:
			result += "SCAN_MODE_CONNECTABLE_DISCOVERABLE";

			break;
		case BluetoothAdapter.SCAN_MODE_CONNECTABLE:
			result += "SCAN_MODE_CONNECTABLE";

			break;
		case BluetoothAdapter.SCAN_MODE_NONE:
			result += "SCAN_MODE_NONE";

			break;

		default:
			break;
		}
		return result;
	}

	private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			Log.w("MainActivity", intent.getAction());
			String prevStateExtra = BluetoothAdapter.EXTRA_PREVIOUS_STATE;
			String stateExtra = BluetoothAdapter.EXTRA_STATE;
			int state = intent.getIntExtra(stateExtra, BluetoothAdapter.ERROR);
			int previousState = intent.getIntExtra(prevStateExtra,
					BluetoothAdapter.ERROR);
			String tt = "";

			switch (state) {
			case (BluetoothAdapter.STATE_TURNING_ON): {
				tt = "Bluetooth turning on";
				break;
			}
			case (BluetoothAdapter.STATE_ON): {
				tt = "Bluetooth on";
				unregisterReceiver(this);
				break;
			}
			case (BluetoothAdapter.STATE_TURNING_OFF): {
				tt = "Bluetooth turning off";
				break;
			}
			case (BluetoothAdapter.STATE_OFF): {
				tt = "Bluetooth off";
				break;
			}
			default:
				break;
			}

			getState(null);
			toggleButtonOnOff.setChecked(bluetoothAdapter.isEnabled());
		}
	};

	@Override
	public void onDestroy() {
		super.onDestroy();
		unregisterReceiver();
	}

}
