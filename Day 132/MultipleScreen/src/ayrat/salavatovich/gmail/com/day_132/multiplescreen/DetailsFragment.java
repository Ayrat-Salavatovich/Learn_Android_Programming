package ayrat.salavatovich.gmail.com.day_132.multiplescreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailsFragment extends Fragment {

	public static DetailsFragment newIstance(int position) {
		DetailsFragment detailsFragment = new DetailsFragment();
		Bundle arguments = new Bundle();
		arguments.putInt(Constants.POSITION, position);
		detailsFragment.setArguments(arguments);

		return detailsFragment;
	}

	public int getPosition() {
		// Система сохраняет аргументы фрагмента при его пересоздании
		return getArguments().getInt(Constants.POSITION, Constants.ZERO);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.details, container, false);
		TextView textView = (TextView) view.findViewById(R.id.textView);
		textView.setText(getResources().getStringArray(R.array.content)[getPosition()]);

		return view;
	}

}
