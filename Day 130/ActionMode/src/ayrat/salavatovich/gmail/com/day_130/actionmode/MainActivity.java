package ayrat.salavatovich.gmail.com.day_130.actionmode;

import android.app.Activity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	private ActionMode actionMode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onClick(View v) {
		if (actionMode == null)
			actionMode = startActionMode(myCallback);
		else
			actionMode.finish();
	}

	private ActionMode.Callback myCallback = new ActionMode.Callback() {

		/**
		 * Обработка нажатия на какой-либо пункт ActionMode
		 */
		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			return false;
		}

		/**
		 * Вызывается при создании ActionMode
		 * 
		 * @return Возвращаем true, если можно создавать ActionMode
		 */
		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			// Наполняем ActionMode пунктами меню (через объект Menu).
			mode.getMenuInflater().inflate(R.menu.context, menu);
			return true;
		}

		/**
		 * Вызывается при закрытии ActionMode
		 */
		@Override
		public void onDestroyActionMode(ActionMode mode) {
			actionMode = null;
		}

		/**
		 * Вызывается при обновлении ActionMode
		 * 
		 * @return Возвращаем true, если ActionMode можно обновить.
		 */
		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			return false;
		}

	};
}
