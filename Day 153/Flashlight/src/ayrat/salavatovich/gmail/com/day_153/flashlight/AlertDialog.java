package ayrat.salavatovich.gmail.com.day_153.flashlight;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class AlertDialog extends DialogFragment {

	private String message;

	public void setMessage(String message) {
		this.message = message;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		getDialog().setTitle(R.string.dialog_title);
		View view = inflater.inflate(R.layout.alert_dialod, null);
		((TextView) view.findViewById(R.id.textViewMessage)).setText(message);
		((Button) view.findViewById(R.id.buttonCancel))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						dismiss();
					}
				});
		return view;
	}

}
