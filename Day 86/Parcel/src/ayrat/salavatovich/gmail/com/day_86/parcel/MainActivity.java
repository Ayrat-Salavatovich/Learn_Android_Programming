package ayrat.salavatovich.gmail.com.day_86.parcel;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	private Parcel parcel;
	private final String TAG = "ayrat.salavatovich.gmail.com.day_86.parcel.MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		writeToParcel();

		unmarshall(marshall());

		readFromParcel();
	}

	private void writeToParcel() {
		parcel = Parcel.obtain();

		byte b = 0;
		int i = 1;
		long l = 2L;
		float f = 3.0F;
		double d = 4.0D;
		CharSequence cs = "5";

		outputWrite("before writing");
		parcel.writeByte(b);
		outputWrite("byte");
		parcel.writeInt(i);
		outputWrite("int");
		parcel.writeLong(l);
		outputWrite("long");
		parcel.writeFloat(f);
		outputWrite("float");
		parcel.writeDouble(d);
		outputWrite("double");
		TextUtils.writeToParcel(cs, parcel,
				Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
		outputWrite("CharSequence");
		outputWrite("after writing");
	}

	private void outputWrite(String info) {
		Log.d(TAG, getString(R.string.write, info, parcel.dataSize()));
	}

	private void readFromParcel() {
		outputRead("before reading");
		parcel.setDataPosition(0);
		outputRead("byte = " + parcel.readByte());
		outputRead("int = " + parcel.readInt());
		outputRead("long = " + parcel.readLong());
		outputRead("float = " + parcel.readFloat());
		outputRead("double = " + parcel.readDouble());
		outputRead("CharSequence = "
				+ TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel));
		outputWrite("after reading");
	}

	private void outputRead(String info) {
		Log.d(TAG, getString(R.string.read, info, parcel.dataPosition()));
	}

	private byte[] marshall() {
		byte[] data = parcel.marshall();
		parcel.recycle();
		outputData(byteToString(data));
		return data;
	}

	private void unmarshall(byte[] data) {
		parcel = Parcel.obtain();
		parcel.unmarshall(data, 0, data.length);
		parcel.setDataPosition(0);
	}

	private String byteToString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes)
			sb.append(b);

		return sb.toString();
	}

	private void outputData(String data) {
		Log.d(TAG, getString(R.string.data, data));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
