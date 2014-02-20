package ayrat.salavatovich.gmail.com.day_129.dynamicactionbar;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment2 extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// Включаем режим вывода элементов фрагмента в ActionBar
		setHasOptionsMenu(true);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.fragment_2, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_2, null);
	}

}
