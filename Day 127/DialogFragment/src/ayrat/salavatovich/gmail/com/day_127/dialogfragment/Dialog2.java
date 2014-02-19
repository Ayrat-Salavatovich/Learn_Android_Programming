package ayrat.salavatovich.gmail.com.day_127.dialogfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.widget.Toast;

public class Dialog2 extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
				.setTitle(R.string.title).setMessage(R.string.message)
				.setPositiveButton(R.string.yes, myOnClickListener)
				.setNegativeButton(R.string.no, myOnClickListener)
				.setNeutralButton(R.string.cancel, myOnClickListener);

		return builder.create();
	}

	/**
	 * Срабатывает, когда диалог отменяют кнопкой Назад
	 */
	@Override
	public void onCancel(DialogInterface dialog) {
		super.onCancel(dialog);
		Toast.makeText(getActivity(), R.string.on_cancel, Toast.LENGTH_SHORT)
				.show();
	}

	/**
	 * Срабатывает, когда диалог закрывается
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();
		Toast.makeText(getActivity(), R.string.on_destroy, Toast.LENGTH_SHORT)
				.show();
	}

	private OnClickListener myOnClickListener = new OnClickListener() {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			int i = 0;
			switch (which) {
			case Dialog.BUTTON_POSITIVE:

				i = R.string.yes;

				break;

			case Dialog.BUTTON_NEGATIVE:

				i = R.string.no;

				break;

			case Dialog.BUTTON_NEUTRAL:

				i = R.string.cancel;

				break;

			default:
				break;
			}

			if (i > 0)
				Toast.makeText(getActivity(), i, Toast.LENGTH_SHORT).show();
		}
	};
}
