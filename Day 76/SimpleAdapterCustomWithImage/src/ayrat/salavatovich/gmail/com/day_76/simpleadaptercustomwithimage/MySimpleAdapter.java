package ayrat.salavatovich.gmail.com.day_76.simpleadaptercustomwithimage;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MySimpleAdapter extends SimpleAdapter {

	public static final int UP_ARROW = R.drawable.arrow_up;
	public static final int DOWN_ARROW = R.drawable.arrow_down;

	public MySimpleAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to) {
		super(context, data, resource, from, to);
	}

	@Override
	public void setViewImage(ImageView v, int value) {
		super.setViewImage(v, value);
	}

	@Override
	public void setViewText(TextView v, String text) {
		super.setViewText(v, text);

		switch (v.getId()) {
		case R.id.textViewValue:
			int color = Color.BLACK;
			if (Integer.parseInt(text) > 0)
				color = Color.GREEN;
			else if (Integer.parseInt(text) < 0)
				color = Color.RED;

			v.setTextColor(color);
			break;

		default:
			break;
		}
	}

}
