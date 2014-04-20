package ayrat.salavatovich.gmail.com.day_160.deviceid;

import java.lang.reflect.Method;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.SystemProperties;
import android.provider.Settings;
import android.app.Activity;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textViewIMEI;
	private TextView textViewMEIDOrESN;
	private TextView textViewIMSI;
	private TextView textViewMAC;
	private TextView textViewSerial;
	private TextView textViewAndroidID;
	private TextView textViewPhoneNumber;
	private TextView textViewSoftwareVersion;
	private TextView textViewOperatorName;
	private TextView textViewSIMCountryCode;
	private TextView textViewSIMOperator;
	private TextView textViewSIMSerial;
	private TextView textViewPhoneType;
	private TextView textViewNetworkType;
	private final String NONE = "NONE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		initFields();
		setupFields();
	}

	void initFields() {
		textViewIMEI = findTextViewById(R.id.textViewIMEI);
		textViewMEIDOrESN = findTextViewById(R.id.textViewMEIDOrESN);
		textViewIMSI = findTextViewById(R.id.textViewIMSI);
		textViewMAC = findTextViewById(R.id.textViewMAC);
		textViewSerial = findTextViewById(R.id.textViewSerial);
		textViewAndroidID = findTextViewById(R.id.textViewAndroidID);
		textViewPhoneNumber = findTextViewById(R.id.textViewPhoneNumber);
		textViewSoftwareVersion = findTextViewById(R.id.textViewSoftwareVersion);
		textViewOperatorName = findTextViewById(R.id.textViewOperatorName);
		textViewSIMCountryCode = findTextViewById(R.id.textViewSIMCountryCode);
		textViewSIMOperator = findTextViewById(R.id.textViewSIMOperator);
		textViewSIMSerial = findTextViewById(R.id.textViewSIMSerial);
		textViewPhoneType = findTextViewById(R.id.textViewPhoneType);
		textViewNetworkType = findTextViewById(R.id.textViewNetworkType);
	}

	private TextView findTextViewById(final int id) {
		return (TextView) findViewById(id);
	}

	void setupFields() {
		textViewIMEI.setText(getString(R.string.text_view_imei, getIMEI()));
		textViewMEIDOrESN.setText(getString(R.string.text_view_meid_or_esn,
				getMEIDOrESN()));
		textViewIMSI.setText(getString(R.string.text_view_imsi, getIMSI()));
		textViewMAC.setText(getString(R.string.text_view_mac, getMAC()));
		textViewSerial.setText(getString(R.string.text_view_serial,
				getSerialMethod1()));
		textViewAndroidID.setText(getString(R.string.text_view_android_id,
				getAndroidID()));
		textViewPhoneNumber.setText(getString(R.string.text_view_phone_number,
				getPhoneNumber()));
		textViewSoftwareVersion.setText(getString(
				R.string.text_view_software_version, getSoftwareVersion()));
		textViewOperatorName.setText(getString(
				R.string.text_view_operator_name, getOperatorName()));
		textViewSIMCountryCode.setText(getString(
				R.string.text_view_sim_country_code, getSIMCountryCode()));
		textViewSIMOperator.setText(getString(R.string.text_view_sim_operator,
				getSIMOperator()));
		textViewSIMSerial.setText(getString(R.string.text_view_sim_serial,
				getSIMSerial()));
		textViewPhoneType.setText(getString(R.string.text_view_phone_type,
				getPhoneType()));
		textViewNetworkType.setText(getString(R.string.text_view_network_type,
				getNetworkType()));
	}

	private String getIMEI() {
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		if (telephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_GSM)
			return telephonyManager.getDeviceId();

		return NONE;
	}

	private String getMEIDOrESN() {
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		if (telephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_CDMA)
			return telephonyManager.getDeviceId();

		return NONE;
	}

	private String getIMSI() {
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		if (telephonyManager.getPhoneType() == TelephonyManager.PHONE_TYPE_GSM)
			return telephonyManager.getSubscriberId();

		return NONE;
	}

	private String getSerialMethod1() {
		// 1
		String serial = NONE;
		serial = SystemProperties.get("ro.serialno", "unknown");
		return serial;
	}

	private String getSerialMethod2() {
		String serial = NONE;
		try {
			Class<?> c = Class.forName("android.os.SystemProperties");
			Method get = c.getMethod("get", String.class, String.class);
			serial = (String) (get.invoke(c, "ro.serialno", "unknown"));
		} catch (Exception ignored) {
		}
		return serial;
	}

	private String getSerialMethod3() {
		String serial = NONE;
		try {
			Class<?> c = Class.forName("android.os.SystemProperties");
			Method[] methods = c.getMethods();
			Object[] params = new Object[] { new String("ro.serialno"),
					new String("Unknown") };
			serial = (String) (methods[2].invoke(c, params));

		} catch (Exception ignored) {
		}

		return serial;
	}

	private String getMAC() {
		String macAddress = NONE;
		WifiManager manager = (WifiManager) getSystemService(WIFI_SERVICE);
		macAddress = manager.getConnectionInfo().getMacAddress();
		if (macAddress != null)
			return macAddress;

		return NONE;
	}

	private String getAndroidID() {
		return Settings.Secure.getString(getContentResolver(),
				Settings.Secure.ANDROID_ID);
	}

	private String getPhoneNumber() {
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		return telephonyManager.getLine1Number();
	}

	private String getSoftwareVersion() {
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		return telephonyManager.getDeviceSoftwareVersion();
	}

	private String getOperatorName() {
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		return telephonyManager.getNetworkOperatorName();
	}

	private String getSIMCountryCode() {
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		return telephonyManager.getSimCountryIso();
	}

	private String getSIMOperator() {
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		return telephonyManager.getSimOperatorName();
	}

	private String getSIMSerial() {
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		return telephonyManager.getSimSerialNumber();
	}

	private String getPhoneType() {
		String phoneType = NONE;
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		switch (telephonyManager.getPhoneType()) {
		case TelephonyManager.PHONE_TYPE_GSM:
			phoneType = "GSM";
			break;
		case TelephonyManager.PHONE_TYPE_CDMA:
			phoneType = "CDMA";
			break;
		case TelephonyManager.PHONE_TYPE_SIP:
			phoneType = "SIP";
			break;
		case TelephonyManager.PHONE_TYPE_NONE:
			phoneType = "UNKNOWN";
			break;
		default:
			phoneType = "UNKNOWN";
			break;
		}

		return phoneType;
	}

	private String getNetworkType() {
		String networkType = NONE;
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

		switch (telephonyManager.getNetworkType()) {
		case TelephonyManager.NETWORK_TYPE_EDGE:
			networkType = "EDGE";
			break;
		case TelephonyManager.NETWORK_TYPE_CDMA:
			networkType = "CDMA";
			break;
		case TelephonyManager.NETWORK_TYPE_LTE:
			networkType = "LTE";
			break;
		case TelephonyManager.NETWORK_TYPE_GPRS:
			networkType = "GPRS";
			break;
		case TelephonyManager.NETWORK_TYPE_UMTS:
			networkType = "UMTS";
			break;
		default:
			networkType = "UNKNOWN";
			break;
		}

		return networkType;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
