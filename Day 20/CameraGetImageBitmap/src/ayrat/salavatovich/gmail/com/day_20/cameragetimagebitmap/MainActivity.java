package ayrat.salavatovich.gmail.com.day_20.cameragetimagebitmap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

	public final int CAMERA_RESULT = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		imageViewCameral = (ImageView) findViewById(R.id.imageViewCamera);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClickCameraTurnOn(View v) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, CAMERA_RESULT);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAMERA_RESULT) {
			Bitmap bm = (Bitmap) data.getExtras().get("data");
			imageViewCameral.setImageBitmap(bm);
		} else
			super.onActivityResult(requestCode, resultCode, data);
	}

	private ImageView imageViewCameral;

}
