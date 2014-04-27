package ayrat.salavatovich.gmail.com.day_170.myapplication;

import android.app.Application;
import android.content.res.Configuration;

public class MyApplication extends Application {

	private String country = "Portuguese Republic";
	private String capital = "Lisbon";
	private String officialLanguages = "Portuguese";
	private int foundation = 868;

	public static class Singleton {
		public static final MyApplication INSTANCE = new MyApplication();
	}

	public static MyApplication getInstance() {
		return Singleton.INSTANCE;
	}

	// Вызывается при создании приложения, переопределяется для инициализации
	// синглтона программы и создания и инициализации свойств, в которых
	// хранятся состояния приложения или общие ресурсы.
	@Override
	public void onCreate() {
		super.onCreate();
	}

	// В отличие от объектов Activity ваше приложение не перезапускается при
	// изменениях конфигурации. Переопределяйте этот метод, если необходимо
	// обрабатывать изменения конфигурации на уровне приложения.
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	// Предоставляет возможность хорошо оптимизированным приложениям освобождать
	// дополнительную память, когда система работает в условиях ограниченных
	// ресурсов. Как правило, вызывается в ситуациях, когда фоновые процессы уже
	// закрыты, а памяти для приложений, находящихся на переднем плане, все еще
	// не хватает.
	// Переопределяйте этот метод, чтобы очищать кэш или освобождать ненужные
	// ресурсы.
	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}

	// Может быть вызван при преждевременном завершении работы объекта
	// Application. Хотя нет никакой гарантии, что так всё и будет. Если работа
	// приложения прерывается ядром, чтобы освободить ресурсы для других
	// программ, процесс завершится без предупреждения и без вызова метода
	// onTerminate из объекта Application.
	@Override
	public void onTerminate() {
		super.onTerminate();
	}

	// Альтернатива методу onLowMemory()
	// Появилась в Android 4.0 (API 14). Вызывается, когда система решает, что
	// данное приложение должно освободить занимаемую им избыточную память,
	// например, при переходе в фоновый режим. Содержит параметр level, с
	// помощью которого предоставляется контекст относительно запроса. Для
	// параметра используются различные константы
	@Override
	public void onTrimMemory(int level) {
		super.onTrimMemory(level);
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getOfficialLanguages() {
		return officialLanguages;
	}

	public void setOfficialLanguages(String officialLanguages) {
		this.officialLanguages = officialLanguages;
	}

	public int getFoundation() {
		return foundation;
	}

	public void setFoundation(int foundation) {
		this.foundation = foundation;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
