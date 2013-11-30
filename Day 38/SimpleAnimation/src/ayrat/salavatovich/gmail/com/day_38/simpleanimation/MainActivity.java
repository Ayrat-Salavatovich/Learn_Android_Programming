package ayrat.salavatovich.gmail.com.day_38.simpleanimation;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textView = (TextView) findViewById(R.id.textView);
		registerForContextMenu(textView);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		switch (v.getId()) {
		case R.id.textView:
			getMenuInflater().inflate(R.menu.context_menu, menu);

			break;

		default:
			break;
		}
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		Animation animation = null;
		switch (item.getItemId()) {
		case R.id.alpha:
			animation = AnimationUtils.loadAnimation(this, R.anim.my_alpha);

			break;
		case R.id.scale:
			animation = AnimationUtils.loadAnimation(this, R.anim.my_scale);

			break;
		case R.id.translate:
			animation = AnimationUtils.loadAnimation(this, R.anim.my_translate);

			break;
		case R.id.rotate:
			animation = AnimationUtils.loadAnimation(this, R.anim.my_rotate);

			break;
		case R.id.set:
			animation = AnimationUtils.loadAnimation(this, R.anim.my_set);

			break;

		default:
			break;
		}

		textView.startAnimation(animation);

		return super.onContextItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private TextView textView;

}
