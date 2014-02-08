package ayrat.salavatovich.gmail.com.day_114.smartwatchplayer;

import android.content.Context;
import android.content.Intent;

import com.sonyericsson.extras.liveware.aef.control.Control;
import com.sonyericsson.extras.liveware.aef.registration.Registration;
import com.sonyericsson.extras.liveware.extension.util.SmartWatchConst;
import com.sonyericsson.extras.liveware.extension.util.widget.WidgetExtension;

public class Widget extends WidgetExtension {

	public Widget(Context context, String hostAppPackageName) {
		super(context, hostAppPackageName);
	}

	@Override
	public void onStartRefresh() {
		// Когда виджет становится видимым и/или обновляется
		showBitmap(new WidgetImage(mContext).getBitmap());
	}

	@Override
	public void onStopRefresh() {
		// Когда виджет перестаёт быть видимым.
	}

	@Override
	public void onTouch(int type, int x, int y) {
		// Если кликнули вне иконки приложения - ничего не делаем
		if (!SmartWatchConst.ACTIVE_WIDGET_TOUCH_AREA.contains(x, y))
			return;

		// По клику (быстрому или долгому) запускаем основное окно программы
		if (type == com.sonyericsson.extras.liveware.aef.widget.Widget.Intents.EVENT_TYPE_SHORT_TAP
				|| type == com.sonyericsson.extras.liveware.aef.widget.Widget.Intents.EVENT_TYPE_LONG_TAP) {
			Intent intent = new Intent(
					Control.Intents.CONTROL_START_REQUEST_INTENT);
			intent.putExtra(Control.Intents.EXTRA_AEA_PACKAGE_NAME,
					mContext.getPackageName());
			intent.setPackage(mHostAppPackageName);
			mContext.sendBroadcast(intent, Registration.HOSTAPP_PERMISSION);
		}
	}

}
