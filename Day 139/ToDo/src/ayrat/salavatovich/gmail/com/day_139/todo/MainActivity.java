package ayrat.salavatovich.gmail.com.day_139.todo;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ListView taskListView;
	private Button buttonNewTask;
	private EditText editTextNewTask;
	private ToDoProvider provider;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		provider = new ToDoProvider(MainActivity.this);
		taskListView = (ListView) this.findViewById(R.id.taskListView);
		buttonNewTask = (Button) this.findViewById(R.id.buttonNewTask);
		editTextNewTask = (EditText) this.findViewById(R.id.editTextNewTask);
		buttonNewTask.setOnClickListener(handleNewTaskEvent);
		createPlaceholders();
		renderToDos();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	EditText getEditText() {
		return editTextNewTask;
	}

	private ToDoProvider getProvider() {
		return provider;
	}

	private ListView getTaskView() {
		return taskListView;
	}

	private OnClickListener handleNewTaskEvent = new OnClickListener() {
		@Override
		public void onClick(final View view) {
			provider.addTask(MainActivity.this.getEditText().getText()
					.toString());
			cleanEditText();
			renderToDos();
		}
	};

	private void cleanEditText() {
		editTextNewTask.setText("");
	}

	private void renderToDos() {
		List<String> beans = getProvider().findAll();
		getTaskView().setAdapter(
				new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1, beans
								.toArray(new String[] {})));
		getTaskView().setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(final AdapterView<?> parent,
					final View view, final int position, final long id) {
				TextView task = (TextView) view;
				getProvider().deleteTask(task.getText().toString());
				renderToDos();
			}
		});
	}

	private void createPlaceholders() {
		getProvider().deleteAll();
		if (getProvider().findAll().isEmpty()) {
			List<String> beans = new ArrayList<String>();
			for (int i = 0; i < 10; i++) {
				String title = getString(R.string.placeholder, i);
				getProvider().addTask(title);
				beans.add(title);
			}
		}
	}

}
