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
		// ����� ������ ���������� ������� �/��� �����������
		showBitmap(new WidgetImage(mContext).getBitmap());
	}

	@Override
	public void onStopRefresh() {
		// ����� ������ �������� ���� �������.
	}

	@Override
	public void onTouch(int type, int x, int y) {
		// ���� �������� ��� ������ ���������� - ������ �� ������
		if (!SmartWatchConst.ACTIVE_WIDGET_TOUCH_AREA.contains(x, y))
			return;

		// �� ����� (�������� ��� �������) ��������� �������� ���� ���������
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
