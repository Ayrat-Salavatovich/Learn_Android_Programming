package ayrat.salavatovich.gmail.com.day_115.intentservicesample;

import android.app.Activity;
import android.app.IntentService;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Intent;

public class MyService extends IntentService {

	public MyService() {
		super("my_thread");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		double number1 = intent
				.getDoubleExtra(getString(R.string.number_1), 1.);
		double number2 = intent
				.getDoubleExtra(getString(R.string.number_2), 1.);
		OperationObject operation = (OperationObject) intent
				.getParcelableExtra(getString(R.string.operation));

		Intent result = new Intent().putExtra(getString(R.string.result),
				operation.apply(number1, number2));

		PendingIntent pendingIntent = intent
				.getParcelableExtra(getString(R.string.pending_intent));

		try {
			pendingIntent.send(MyService.this, Activity.RESULT_OK, result);
		} catch (CanceledException e) {
			e.printStackTrace();
		}
	}

}
