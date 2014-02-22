package ayrat.salavatovich.gmail.com.day_132.multiplescreen;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TitlesFragment extends ListFragment {

	OnItemClickListener myOnItemClickListener;

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		myOnItemClickListener.itemClick(position);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		myOnItemClickListener = (OnItemClickListener) activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				getActivity(), R.array.headers,
				android.R.layout.simple_list_item_1);
		setListAdapter(adapter);
	}

}
