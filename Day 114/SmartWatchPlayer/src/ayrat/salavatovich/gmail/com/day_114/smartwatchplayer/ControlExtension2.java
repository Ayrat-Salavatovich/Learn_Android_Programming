package ayrat.salavatovich.gmail.com.day_114.smartwatchplayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.sonyericsson.extras.liveware.aef.control.Control;
import com.sonyericsson.extras.liveware.extension.util.control.ControlTouchEvent;

public class ControlExtension2 extends ControlExtension {

	public ControlExtension2(Context context, String hostAppPackageName) {
		super(context, hostAppPackageName);
	}

	@Override
	public void onTouch(final ControlTouchEvent event) {// реакция на клики
		if (event.getAction() == Control.Intents.CLICK_TYPE_SHORT) {
			if (Constants.buttonStopPlaySmartWatch2.contains(event.getX(),
					event.getY())) {
				MusicBackgroundControlWrapper.TogglePausePlay(mContext);
			}
		}
	}

	@Override
	public void onResume() {
		Bitmap mPicture = BitmapFactory.decodeResource(mContext.getResources(),
				R.drawable.control_picture2);
		showBitmap(mPicture);
	}
}
