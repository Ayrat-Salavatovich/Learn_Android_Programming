package ayrat.salavatovich.gmail.com.day_130.longclickwithactionmode;

import android.os.Bundle;
import android.app.Activity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AbsListView.MultiChoiceModeListener;

public class MainActivity extends Activity {

	private ListView listViewActionMode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.numbers,
				android.R.layout.simple_list_item_activated_1);
		listViewActionMode = (ListView) findViewById(R.id.listViewActionMode);
		listViewActionMode.setAdapter(adapter);
		// Включаем режим выбора
		listViewActionMode.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		listViewActionMode
				.setMultiChoiceModeListener(myMultiChoiceModeListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private MultiChoiceModeListener myMultiChoiceModeListener = new MultiChoiceModeListener() {

		/**
		 * Вызывается при обновлении ActionMode
		 * 
		 * @return Возвращаем true, если ActionMode можно обновить.
		 */
		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			return false;
		}

		/**
		 * Вызывается при закрытии ActionMode
		 */
		@Override
		public void onDestroyActionMode(ActionMode mode) {

		}

		/**
		 * Вызывается при создании ActionMode
		 * 
		 * @return Возвращаем true, если можно создавать ActionMode
		 */
		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			mode.getMenuInflater().inflate(R.menu.context, menu);
			return true;
		}

		/**
		 * Обработка нажатия на какой-либо пункт ActionMode
		 */
		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			mode.finish();
			return false;
		}

		/**
		 * Получаем инфу о выделенных пунктах списка
		 */
		@Override
		public void onItemCheckedStateChanged(ActionMode mode, int position,
				long id, boolean checked) {

		}
	};

}
