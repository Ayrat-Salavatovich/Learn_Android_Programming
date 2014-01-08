package ayrat.salavatovich.gmail.com.day_78.simpleadaptercustomwithprogressbar;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

public class MyViewBinder implements SimpleAdapter.ViewBinder {

	@Override
	public boolean setViewValue(View view, Object data,
			String textRepresentation) {
		int i = Integer.MAX_VALUE;
		switch (view.getId()) {
		case R.id.linearLayoutItem:
			i = ((Integer) data).intValue();
			if (i < 40)
				view.setBackgroundResource(R.color.green);
			else if (i < 70)
				view.setBackgroundResource(R.color.orange);
			else
				view.setBackgroundResource(R.color.red);

			return true;
		case R.id.progressBarItem:
			i = ((Integer) data).intValue();
			ProgressBar progressBar = ((ProgressBar) view);
			progressBar.setProgress(i);

			return true;

		default:
			break;
		}
		return false;
	}

}
