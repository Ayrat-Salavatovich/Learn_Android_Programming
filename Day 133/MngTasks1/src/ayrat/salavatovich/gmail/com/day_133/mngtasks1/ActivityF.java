package ayrat.salavatovich.gmail.com.day_133.mngtasks1;

import android.content.Intent;
import android.view.View;

public class ActivityF extends MainActivity {

	@Override
	public void onClickStart(View v) {
		startActivity(new Intent(this, ActivityF.class));
	}

}
