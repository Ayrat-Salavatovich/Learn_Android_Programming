package ayrat.salavatovich.gmail.com.day_114.smartwatchplayer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ExtensionReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		intent.setClass(context, ReceiverService.class);
		context.startService(intent);
	}

}
