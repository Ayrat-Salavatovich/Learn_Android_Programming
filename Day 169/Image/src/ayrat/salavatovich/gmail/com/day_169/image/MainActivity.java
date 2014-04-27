package ayrat.salavatovich.gmail.com.day_169.image;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null)
			getSupportFragmentManager()
					.beginTransaction()
					.add(R.id.frameLayoutRootContainer,
							new PlaceholderFragment()).commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonOpenActivityInvertImage:

			startActivity(new Intent(this, ActivityInvertImage.class));

			break;

		case R.id.buttonOpenActivityGrayscaleImage:

			startActivity(new Intent(this, ActivityGrayscaleImage.class));

			break;

		case R.id.buttonOpenActivityRotateImage:

			startActivity(new Intent(this, ActivityRotateImage.class));

			break;

		case R.id.buttonOpenActivityChannelMixerImage:

			startActivity(new Intent(this, ActivityChannelMixerImage.class));

			break;

		case R.id.buttonOpenActivityFilterImage:

			startActivity(new Intent(this, ActivityFilterImage.class));

			break;

		case R.id.buttonOpenActivityGammaImage:

			startActivity(new Intent(this, ActivityGammaImage.class));

			break;

		case R.id.buttonOpenActivitySepiaImage:

			startActivity(new Intent(this, ActivitySepiaImage.class));

			break;

		case R.id.buttonOpenActivityWaterMarkImage:

			startActivity(new Intent(this, ActivityWaterMarkImage.class));

			break;

		case R.id.buttonOpenActivityColorDepthImage:

			startActivity(new Intent(this, ActivityColorDepthImage.class));

			break;

		case R.id.buttonOpenActivityContrastImage:

			startActivity(new Intent(this, ActivityContrastImage.class));

			break;

		case R.id.buttonOpenActivityBrightnessImage:

			startActivity(new Intent(this, ActivityBrightnessImage.class));

			break;

		case R.id.buttonOpenActivitySnowEffectImage:

			startActivity(new Intent(this, ActivitySnowEffectImage.class));

			break;

		case R.id.buttonOpenActivityBlackFilterImage:

			startActivity(new Intent(this, ActivityBlackFilterImage.class));

			break;

		case R.id.buttonOpenActivityNoiseEffectImage:

			startActivity(new Intent(this, ActivityNoiseEffectImage.class));

			break;

		case R.id.buttonOpenActivitySaturationImage:

			startActivity(new Intent(this, ActivitySaturationImage.class));

			break;

		case R.id.buttonOpenActivityHueImage:

			startActivity(new Intent(this, ActivityHueImage.class));

			break;

		case R.id.buttonOpenActivityShadingFilterImage:

			startActivity(new Intent(this, ActivityShadingFilterImage.class));

			break;

		case R.id.buttonOpenActivityHighlightImage:

			startActivity(new Intent(this, ActivityHighlightImage.class));

			break;

		case R.id.buttonOpenActivityEmbossEffectImage:

			startActivity(new Intent(this, ActivityEmbossEffectImage.class));

			break;

		case R.id.buttonOpenActivityGaussianBlurEffectImage:

			startActivity(new Intent(this,
					ActivityGaussianBlurEffectImage.class));

			break;

		case R.id.buttonOpenActivityEngravingEffectImage:

			startActivity(new Intent(this, ActivityEngravingEffectImage.class));

			break;

		case R.id.buttonOpenActivitySharpenEffectImage:

			startActivity(new Intent(this, ActivitySharpenEffectImage.class));

			break;

		case R.id.buttonOpenActivityMeanRemovalEffectImage:

			startActivity(new Intent(this, ActivityMeanRemovalEffectImage.class));

			break;

		case R.id.buttonOpenActivitySmoothEffectImage:

			startActivity(new Intent(this, ActivitySmoothEffectImage.class));

			break;

		default:
			break;
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings)
			return true;

		return super.onOptionsItemSelected(item);
	}

	public static class PlaceholderFragment extends Fragment {

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);

			return rootView;
		}

	}

}
