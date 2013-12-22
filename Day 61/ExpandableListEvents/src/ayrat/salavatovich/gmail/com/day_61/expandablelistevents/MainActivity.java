package ayrat.salavatovich.gmail.com.day_61.expandablelistevents;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView textViewInfo;
	AdapterHelper adapterHelper;
	SimpleExpandableListAdapter adapter;
	ExpandableListView expandableListViewMain;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textViewInfo = (TextView) findViewById(R.id.textViewInfo);
		adapterHelper = new AdapterHelper(this);
		adapter = adapterHelper.getAdapter();

		expandableListViewMain = (ExpandableListView) findViewById(R.id.expandableListViewMain);
		expandableListViewMain.setAdapter(adapter);

		// нажатие на элемент
		expandableListViewMain
				.setOnChildClickListener(new OnChildClickListener() {

					@Override
					public boolean onChildClick(ExpandableListView parent,
							View v, int groupPosition, int childPosition,
							long id) {
						textViewInfo.setText(adapterHelper.getGroupChildText(
								groupPosition, childPosition));
						return false;
					}
				});

		// нажатие на группу
		expandableListViewMain
				.setOnGroupClickListener(new OnGroupClickListener() {

					@Override
					public boolean onGroupClick(ExpandableListView parent,
							View v, int groupPosition, long id) {
						textViewInfo.setText(adapterHelper
								.getGroupText(groupPosition));

						// блокируем дальнейшую обработку события для группы с
						// позицией 1
						if (groupPosition == 1)
							return true;

						return false;
					}
				});

		// сворачивание группы
		expandableListViewMain
				.setOnGroupCollapseListener(new OnGroupCollapseListener() {

					@Override
					public void onGroupCollapse(int groupPosition) {
						textViewInfo.setText("Свернули "
								+ adapterHelper.getGroupText(groupPosition));
					}
				});

		// разворачивание группы
		expandableListViewMain
				.setOnGroupExpandListener(new OnGroupExpandListener() {

					@Override
					public void onGroupExpand(int groupPosition) {
						textViewInfo.setText("Равзвернули "
								+ adapterHelper.getGroupText(groupPosition));
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
