package ayrat.salavatovich.gmail.com.day_166.customview;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		MyRectView myRectView1, myRectView2, myRectView3;
		MyCircleView myCircleView1, myCircleView2, myCircleView3;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.main_fragment, container,
					false);
			myRectView1 = (MyRectView) rootView.findViewById(R.id.myRectView1);
			myRectView2 = (MyRectView) rootView.findViewById(R.id.myRectView2);
			myRectView3 = (MyRectView) rootView.findViewById(R.id.myRectView3);
			myCircleView1 = (MyCircleView) rootView
					.findViewById(R.id.myCircleView1);
			myCircleView2 = (MyCircleView) rootView
					.findViewById(R.id.myCircleView2);
			myCircleView3 = (MyCircleView) rootView
					.findViewById(R.id.myCircleView3);

			myRectView1.setCrazyColorView();
			myRectView2.setCrazyColorView();
			myRectView3.setCrazyColorView();
			myCircleView1.setCrazyColorView();
			myCircleView2.setCrazyColorView();
			myCircleView3.setCrazyColorView();

			return rootView;
		}
	}

}
