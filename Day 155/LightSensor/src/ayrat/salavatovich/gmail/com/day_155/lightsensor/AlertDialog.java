package ayrat.salavatovich.gmail.com.day_155.lightsensor;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

public class AlertDialog extends DialogFragment {

	static AlertDialog newInstance(String title, String message) {
		AlertDialog alertDialog = new AlertDialog();

		Bundle args = new Bundle();
		args.putSerializable("title", title);
		args.putSerializable("message", message);
		alertDialog.setArguments(args);

		return alertDialog;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final String title = (String) getArguments().getSerializable("title");
		final String message = (String) getArguments().getSerializable(
				"message");
		android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(
				getActivity()).setTitle(title).setMessage(message)
				.setNeutralButton(android.R.string.cancel, onClickListener);

		return builder.create();
	}

	@Override
	public void onCancel(DialogInterface dialog) {
		super.onCancel(dialog);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			switch (which) {
			case Dialog.BUTTON_NEUTRAL:
				dismiss();

				break;

			default:
				break;
			}
		}
	};
}
