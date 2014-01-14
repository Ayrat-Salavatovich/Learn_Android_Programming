package ayrat.salavatovich.gmail.com.day_87.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

public class MyObject implements Parcelable {

	public static final String TAG = MyObject.class.getCanonicalName();

	public String str;
	public int i;

	public MyObject(String str, int i) {
		LogUtils.d(TAG, "MyObject(String str, int i)");

		this.str = str;
		this.i = i;
	}

	private MyObject(Parcel parcel) {
		LogUtils.d(TAG, "MyObject(Parcel parcel)");

		str = parcel.readString();
		i = parcel.readInt();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		LogUtils.d(TAG, "writeToParcel(Parcel dest, int flags)");

		dest.writeString(str);
		dest.writeInt(i);
	}

	public static final Parcelable.Creator<MyObject> CREATOR = new Parcelable.Creator<MyObject>() {

		@Override
		public MyObject createFromParcel(Parcel in) {
			LogUtils.d(TAG, "createFromParcel(Parcel in)");

			return new MyObject(in);
		}

		@Override
		public MyObject[] newArray(int size) {
			return new MyObject[size];
		}

	};

}
