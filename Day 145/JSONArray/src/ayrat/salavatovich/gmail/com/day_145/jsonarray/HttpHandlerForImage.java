package ayrat.salavatovich.gmail.com.day_145.jsonarray;

import android.graphics.Bitmap;

public abstract class HttpHandlerForImage {

	public abstract void onResponse(Bitmap result);

	public void execute(String args) {
		new HttpAcyncTaskLoadImage(this).execute(args);
	}
}