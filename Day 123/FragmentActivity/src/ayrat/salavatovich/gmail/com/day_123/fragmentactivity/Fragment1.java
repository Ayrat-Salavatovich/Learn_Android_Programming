package ayrat.salavatovich.gmail.com.day_123.fragmentactivity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment1 extends Fragment {

	private Activity activity;
	private TextView textView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View fragment = inflater.inflate(R.layout.fragment_1, null);

		activity = getActivity();

		textView = (TextView) fragment.findViewById(R.id.textView);
		Button button = (Button) fragment.findViewById(R.id.button);
		button.setOnClickListener(myOnClickListener);

		return fragment;
	}

	private OnClickListener myOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			((Button) activity.findViewById(R.id.button))
					.setText(R.string.access_to_fragment1);
			textView.setText(R.string.click_in_fragment1);
		}
	};

}
