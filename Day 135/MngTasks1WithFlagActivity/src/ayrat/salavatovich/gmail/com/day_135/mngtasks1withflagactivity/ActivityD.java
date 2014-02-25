package ayrat.salavatovich.gmail.com.day_135.mngtasks1withflagactivity;

import android.content.Intent;
import android.view.View;

public class ActivityD extends MainActivity {

	@Override
	public void onClickStart(View v) {
		startActivity(new Intent(this, ActivityE.class));
	}

}
