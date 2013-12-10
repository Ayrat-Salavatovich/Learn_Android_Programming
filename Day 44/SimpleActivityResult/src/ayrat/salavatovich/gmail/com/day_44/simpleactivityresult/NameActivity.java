package ayrat.salavatovich.gmail.com.day_44.simpleactivityresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class NameActivity extends Activity implements OnClickListener {

	Button buttonOk;
	EditText editTextName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.form);

		editTextName = (EditText) findViewById(R.id.editTextName);

		buttonOk = (Button) findViewById(R.id.buttonOk);
		buttonOk.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		intent.putExtra(Name.NAME, getName());
		setResult(RESULT_OK, intent);
		finish();
	}

	private String getName() {
		return editTextName.getText().toString();
	}

}
