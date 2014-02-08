package ayrat.salavatovich.gmail.com.day_114.smartwatchplayer;

import android.content.Context;

import com.sonyericsson.extras.liveware.extension.util.registration.DeviceInfoHelper;

public class ReceiverService extends
		com.sonyericsson.extras.liveware.extension.util.ExtensionService {

	private Context context;

	public ReceiverService() {
		super(Constants.EXTENSION_KEY);

		context = this;
	}

	@Override
	protected com.sonyericsson.extras.liveware.extension.util.registration.RegistrationInformation getRegistrationInformation() {
		return new RegistrationInformation(context);
	}

	@Override
	protected boolean keepRunningWhenConnected() {
		// �� ����� ��������� ������� ������ ����������
		return false;
	}

	@Override
	public com.sonyericsson.extras.liveware.extension.util.widget.WidgetExtension createWidgetExtension(
			String hostAppPackageName) {
		// ���������� ������ �������
		return new Widget(this, hostAppPackageName);
	}

	@Override
	public com.sonyericsson.extras.liveware.extension.util.control.ControlExtension createControlExtension(
			String hostAppPackageName) {
		boolean isSmartWatch2 = DeviceInfoHelper
				.isSmartWatch2ApiAndScreenDetected(this, hostAppPackageName);

		// ���������� ������ �������� ���������
		if (isSmartWatch2)
			return new ControlExtension2(this, hostAppPackageName);
		else
			return new ControlExtension(this, hostAppPackageName);
	}

}
