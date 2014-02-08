package ayrat.salavatovich.gmail.com.day_114.smartwatchplayer;

import android.content.Context;
import android.widget.LinearLayout;

import com.sonyericsson.extras.liveware.extension.util.widget.SmartWatchWidgetImage;

public class WidgetImage extends SmartWatchWidgetImage {

	public WidgetImage(Context context) {
		super(context);
		setInnerLayoutResourceId(R.layout.music_widget_image);
	}

	@Override
	protected void applyInnerLayout(LinearLayout innerLayout) {
	}

}
