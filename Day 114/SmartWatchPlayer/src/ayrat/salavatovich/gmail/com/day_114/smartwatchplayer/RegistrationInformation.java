package ayrat.salavatovich.gmail.com.day_114.smartwatchplayer;

import com.sonyericsson.extras.liveware.aef.registration.Registration;
import com.sonyericsson.extras.liveware.extension.util.ExtensionUtils;

import android.content.ContentValues;
import android.content.Context;

public class RegistrationInformation
		extends
		com.sonyericsson.extras.liveware.extension.util.registration.RegistrationInformation {

	private Context context;

	public RegistrationInformation(Context context) {
		if (context != null)
			this.context = context;
		else
			throw new IllegalArgumentException();
	}

	@Override
	public int getRequiredNotificationApiVersion() {
		// ����������� (Notification) � ����������� ���������-�����������
		// ���� �� ����� - 0
		return 0;
	}

	@Override
	public ContentValues getExtensionRegistrationConfiguration() {
		// ������ � ������ ���������� � ��������
		String iconHostapp = ExtensionUtils.getUriString(context,
				R.drawable.ic_launcher);

		ContentValues values = new ContentValues();

		// ��������, �������������� � ���� "��������� ����������".
		// ���� ��� �� ����� - ������� ��������.
		values.put(Registration.ExtensionColumns.CONFIGURATION_ACTIVITY,
				MainActivity.class.getName());

		// �����, ������������� � �������� ������ ���� ��������� ����������
		// ������.
		// ���� ��� �� ����� - ������� ��������.
		values.put(Registration.ExtensionColumns.CONFIGURATION_TEXT,
				context.getString(R.string.configuration_text));

		// ���, ������������ � ������ ���������� ������ ��������� ����������
		// ������
		values.put(Registration.ExtensionColumns.NAME,
				context.getString(R.string.name));

		// ���������� ���� ����������
		values.put(Registration.ExtensionColumns.EXTENSION_KEY,
				Constants.EXTENSION_KEY);

		// ������ � ������ ���������� � ��������
		values.put(Registration.ExtensionColumns.HOST_APP_ICON_URI, iconHostapp);

		// ������ � ������ ���������� �� ����� �����, � ������ 48x48
		values.put(Registration.ExtensionColumns.EXTENSION_ICON_URI,
				iconHostapp);

		// ������ ������ ��������� �����������
		values.put(Registration.ExtensionColumns.NOTIFICATION_API_VERSION,
				getRequiredNotificationApiVersion());

		values.put(Registration.ExtensionColumns.PACKAGE_NAME,
				context.getPackageName());

		return values;
	}

	@Override
	public int getRequiredWidgetApiVersion() {
		// ����������� � ������ ��������
		// ������ ������ ����� ������� �� ������������
		// ��������� ������ ������ �����
		return 1;
	}

	@Override
	public boolean isWidgetSizeSupported(int width, int height) {
		return (width == Constants.WIDGET_WIDTH_SMARTWATCH && height == Constants.WIDGET_HEIGHT_SMARTWATCH);
	}

	@Override
	public int getRequiredControlApiVersion() {
		// ��������� ������ ������ �����
		// ���� �� �������� 2 � �������������� �� ������ Smartwatch 2, �� ������
		// �� �� �����������.
		return 1;
	}

	@Override
	public int getTargetControlApiVersion() {
		// ������� ������ API
		// ��������� ������ ������ �����
		return 2;
	}

	@Override
	public boolean isDisplaySizeSupported(int width, int height) {
		// �������� ������� ������ ���������� � ����������, ����� �� ��
		// ����������� �� �� ��� ���.
		return (width == Constants.CONTROL_WIDTH_SMARTWATCH_2 && height == Constants.CONTROL_HEIGHT_SMARTWATCH_2)
				|| (width == Constants.CONTROL_WIDTH_SMARTWATCH && height == Constants.CONTROL_HEIGHT_SMARTWATCH);
	}

	@Override
	public int getRequiredSensorApiVersion() {
		// ���������� � ��������
		// ��������� ������ �������� (�������������� � �.�.) �
		// ���� �� ����� - 0.
		return 0;
	}

}
