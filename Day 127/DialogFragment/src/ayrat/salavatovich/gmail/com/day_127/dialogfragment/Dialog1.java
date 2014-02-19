package ayrat.salavatovich.gmail.com.day_127.dialogfragment;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

public class Dialog1 extends DialogFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		getDialog().setTitle(R.string.title);

		View v = inflater.inflate(R.layout.dialog_1, null);
		v.findViewById(R.id.buttonYes).setOnClickListener(myOnClickListener);
		v.findViewById(R.id.buttonNo).setOnClickListener(myOnClickListener);
		v.findViewById(R.id.buttonCancel).setOnClickListener(myOnClickListener);

		return v;
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
		public void onClick(View v) {
			int i = 0;
			switch (v.getId()) {
			case R.id.buttonYes:

				i = R.string.yes;

				break;

			case R.id.buttonNo:

				i = R.string.no;

				break;

			case R.id.buttonCancel:

				i = R.string.cancel;
				dismiss();

				break;

			default:
				break;
			}

			if (i > 0)
				Toast.makeText(getActivity(), i, Toast.LENGTH_SHORT).show();
		}
	};
}
