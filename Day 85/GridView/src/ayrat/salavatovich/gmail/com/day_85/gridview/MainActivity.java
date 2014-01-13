package ayrat.salavatovich.gmail.com.day_85.gridview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class MainActivity extends Activity {

	ArrayAdapter<CharSequence> adapter;
	GridView gridView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		adapter = ArrayAdapter.createFromResource(getApplicationContext(),
				R.array.portuguese_alphabet, R.layout.item);

		gridView = (GridView) findViewById(R.id.gridView);
		gridView.setAdapter(adapter);

		setupGridView();
	}

	private void setupGridView() {
		// кол-во столбцов в сетке
		gridView.setNumColumns(GridView.AUTO_FIT);
		// ширина столбцов
		gridView.setColumnWidth(95);
		// горизонтальный отступ
		gridView.setHorizontalSpacing(5);
		// вертикальный отступ
		gridView.setVerticalSpacing(5);
		// использованние свободного пространства
		gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
