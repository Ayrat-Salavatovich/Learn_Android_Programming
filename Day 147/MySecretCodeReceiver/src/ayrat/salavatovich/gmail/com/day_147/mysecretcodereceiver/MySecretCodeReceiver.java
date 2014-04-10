package ayrat.salavatovich.gmail.com.day_147.mysecretcodereceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MySecretCodeReceiver extends BroadcastReceiver {
	
	private static final String SECRET_CODE_ACTION =
	        "android.provider.Telephony.SECRET_CODE";

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(SECRET_CODE_ACTION)) {
			Intent myIntent = new Intent(Intent.ACTION_MAIN);
			myIntent.setClass(context, MainActivity.class);
			myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(myIntent);
		}
	}

}
