package ayrat.salavatovich.gmail.com.day_4.capitalofportugal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChooseActivity extends Activity {

	public final static String CAPITAL = "ayrat.salavatovich.gmail.com.day_4.capitalofportugal.CAPITAL";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_answers);
	}

	public void onRadioClick(View v) {
		Intent answerInent = new Intent();

		switch (v.getId()) {
		case R.id.radioLisbon:
			answerInent.putExtra(CAPITAL, R.string.lisbon);
			break;
		case R.id.radioRome:
			answerInent.putExtra(CAPITAL, R.string.rome);
			break;
		case R.id.radioAmsterdam:
			answerInent.putExtra(CAPITAL, R.string.amsterdam);
			break;

		default:
			break;
		}

		setResult(RESULT_OK, answerInent);
		finish();
	}

}
