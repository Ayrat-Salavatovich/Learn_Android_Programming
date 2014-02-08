package ayrat.salavatovich.gmail.com.day_114.smartwatchplayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.sonyericsson.extras.liveware.aef.control.Control;
import com.sonyericsson.extras.liveware.extension.util.control.ControlTouchEvent;

public class ControlExtension
		extends
		com.sonyericsson.extras.liveware.extension.util.control.ControlExtension {

	public ControlExtension(Context context, String hostAppPackageName) {
		super(context, hostAppPackageName);
	}

	@Override
	public void onResume() {
		// Рисуем изображение
		// Вызывается каждый раз, как оно программы будет видно, например, часы
		// вышли из спячки или была выбрана иконка приложения
		Bitmap picture = BitmapFactory.decodeResource(mContext.getResources(),
				R.drawable.control_picture);
		showBitmap(picture);
	}

	@Override
	public void onTouch(ControlTouchEvent event) {
		// Реакция на клики
		if (event.getAction() == Control.Intents.CLICK_TYPE_SHORT) {
			if (Constants.buttonStopPlaySmartWatch.contains(event.getX(),
					event.getY())) {
				MusicBackgroundControlWrapper.TogglePausePlay(mContext);
			}
		} else
			super.onTouch(event);
	}

	@Override
	public void onSwipe(int direction) {
		// Реакция на жесты
		if (direction == Control.Intents.SWIPE_DIRECTION_UP) {
			MusicBackgroundControlWrapper.VolumeUp(mContext);
		}
		if (direction == Control.Intents.SWIPE_DIRECTION_DOWN) {
			MusicBackgroundControlWrapper.VolumeDown(mContext);
		}
		if (direction == Control.Intents.SWIPE_DIRECTION_LEFT) {
			MusicBackgroundControlWrapper.Next(mContext);
		}
		if (direction == Control.Intents.SWIPE_DIRECTION_RIGHT) {
			MusicBackgroundControlWrapper.Prev(mContext);
		}
	}

}
