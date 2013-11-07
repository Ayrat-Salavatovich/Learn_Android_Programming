package ayrat.salavatovich.gmail.com.day_14.europeanarticlenumber13;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private final String PATH_TYPEFACE = "fonts/EanP72Tt Normal.ttf";
	private final EAN13BarcodeBuilder ean13 = new EAN13BarcodeBuilder();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		tf = Typeface.createFromAsset(getAssets(), PATH_TYPEFACE);
		textViewEan13Barcode = (TextView) findViewById(R.id.textViewEan13Barcode);
		textViewEan13Barcode.setTypeface(tf);

		editTextBarcodeNumber = (EditText) findViewById(R.id.editTextBarcodeNumber);
		editTextBarcodeNumber
				.setFilters(new InputFilter[] { new InputFilter.LengthFilter(
						EAN13BarcodeBuilder.DIGITS_PRODUCT_CODE) });
		editTextBarcodeNumber
				.setOnFocusChangeListener(new OnFocusChangeListener() {

					@Override
					public void onFocusChange(View v, boolean hasFocus) {
						switch (v.getId()) {
						case R.id.editTextBarcodeNumber:
							if (editTextBarcodeNumber.getText().toString()
									.length() < EAN13BarcodeBuilder.DIGITS_PRODUCT_CODE) {
								editTextBarcodeNumber
										.setError(getResources()
												.getString(
														R.string.info_error_barcode_number));
							}
							break;

						default:
							break;
						}
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void showErrorEan13Barcode() {
		Toast.makeText(getApplicationContext(),
				R.string.info_error_barcode_number, Toast.LENGTH_LONG).show();
	}

	public void onClickGenerateEan13Barcode(View v) {
		if (editTextBarcodeNumber.getText().toString().length() != EAN13BarcodeBuilder.DIGITS_PRODUCT_CODE) {
			showErrorEan13Barcode();
			editTextBarcodeNumber.requestFocus();
		} else
			try {
				textViewEan13Barcode.setText(ean13
						.getCode(editTextBarcodeNumber.getText().toString()));
			} catch (NumberFormatException ignore) {
				showErrorEan13Barcode();
			} catch (IllegalArgumentException ignore) {
				showErrorEan13Barcode();
			}
	}

	private TextView textViewEan13Barcode;
	private EditText editTextBarcodeNumber;
	private Typeface tf;

}
