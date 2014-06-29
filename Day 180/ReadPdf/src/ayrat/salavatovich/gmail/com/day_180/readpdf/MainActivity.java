package ayrat.salavatovich.gmail.com.day_180.readpdf;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textViewPdfSupported;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		init();
	}

	private void init() {
		textViewPdfSupported = (TextView) findViewById(R.id.textViewPdfSupported);
		if (canReadPdf(getBaseContext()))
			textViewPdfSupported.setText(R.string.text_view_text_pdf_supported);
		else
			textViewPdfSupported
					.setText(R.string.text_view_text_pdf_unsupported);
	}

	public boolean canReadPdf(Context c) {
		PackageManager pm = c.getPackageManager();
		Intent data = new Intent(Intent.ACTION_VIEW);
		data.setType("application/pdf");
		if (pm.queryIntentActivities(data, PackageManager.MATCH_DEFAULT_ONLY)
				.size() > 0)
			return true;

		return false;
	}

}
