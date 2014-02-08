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
		// Нотификации (Notification) — всплывающие сообщения-уведомления
		// Если не нужно - 0
		return 0;
	}

	@Override
	public ContentValues getExtensionRegistrationConfiguration() {
		// Иконка в списке приложений в телефоне
		String iconHostapp = ExtensionUtils.getUriString(context,
				R.drawable.ic_launcher);

		ContentValues values = new ContentValues();

		// Активити, отображающееся в меню "настройки расширения".
		// Если оно не нужно - убираем параметр.
		values.put(Registration.ExtensionColumns.CONFIGURATION_ACTIVITY,
				MainActivity.class.getName());

		// Текст, отображащийся в качестве пункта меню программы управления
		// часами.
		// Если оно не нужно - убираем параметр.
		values.put(Registration.ExtensionColumns.CONFIGURATION_TEXT,
				context.getString(R.string.configuration_text));

		// Имя, отображаемое в списке приложений внутри программы управления
		// часами
		values.put(Registration.ExtensionColumns.NAME,
				context.getString(R.string.name));

		// Уникальный ключ расширения
		values.put(Registration.ExtensionColumns.EXTENSION_KEY,
				Constants.EXTENSION_KEY);

		// Иконка в списке приложений в телефоне
		values.put(Registration.ExtensionColumns.HOST_APP_ICON_URI, iconHostapp);

		// Иконка в списке приложений на самих часах, в идеале 48x48
		values.put(Registration.ExtensionColumns.EXTENSION_ICON_URI,
				iconHostapp);

		// Нужная версия механизма уведомлений
		values.put(Registration.ExtensionColumns.NOTIFICATION_API_VERSION,
				getRequiredNotificationApiVersion());

		values.put(Registration.ExtensionColumns.PACKAGE_NAME,
				context.getPackageName());

		return values;
	}

	@Override
	public int getRequiredWidgetApiVersion() {
		// Изображение в списке виджетов
		// Вторая версия часов виджеты не поддерживает
		// Поддержка первой версии часов
		return 1;
	}

	@Override
	public boolean isWidgetSizeSupported(int width, int height) {
		return (width == Constants.WIDGET_WIDTH_SMARTWATCH && height == Constants.WIDGET_HEIGHT_SMARTWATCH);
	}

	@Override
	public int getRequiredControlApiVersion() {
		// Поддержка первой версии часов
		// Если бы передали 2 – поддерживались бы только Smartwatch 2, на первых
		// бы не запустилось.
		return 1;
	}

	@Override
	public int getTargetControlApiVersion() {
		// Целевая версия API
		// Поддержка второй версии часов
		return 2;
	}

	@Override
	public boolean isDisplaySizeSupported(int width, int height) {
		// Получаем размеры экрана устройства и определяем, хотим ли мы
		// запускаться на нём или нет.
		return (width == Constants.CONTROL_WIDTH_SMARTWATCH_2 && height == Constants.CONTROL_HEIGHT_SMARTWATCH_2)
				|| (width == Constants.CONTROL_WIDTH_SMARTWATCH && height == Constants.CONTROL_HEIGHT_SMARTWATCH);
	}

	@Override
	public int getRequiredSensorApiVersion() {
		// Информация с сенсоров
		// Поддержка разных сенсоров (акселерометров и т.п.) –
		// Если не нужно - 0.
		return 0;
	}

}
