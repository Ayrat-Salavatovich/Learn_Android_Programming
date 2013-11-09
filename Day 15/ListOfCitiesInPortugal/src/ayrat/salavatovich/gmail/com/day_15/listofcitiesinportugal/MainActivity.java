package ayrat.salavatovich.gmail.com.day_15.listofcitiesinportugal;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	private ArrayAdapter<String> adapter;
	private AlertDialog.Builder ad;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_list);

		context = getApplicationContext();
		ad = new AlertDialog.Builder(this);

		adapter = new ArrayAdapter<String>(context,
				android.R.layout.simple_list_item_1, getResources()
						.getStringArray(R.array.cities));
		setListAdapter(adapter);

		getListView().setLayoutAnimation(
				AnimationUtils.loadLayoutAnimation(context,
						R.anim.list_layout_controller));

		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				showToast(getString(R.string.toast_your_choise,
						parent.getItemAtPosition(position)));
			}

		});
		getListView().setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View v,
					int position, long id) {
				final String choise_city = parent.getItemAtPosition(position)
						.toString();
				showAlertDialog(choise_city);
				return true;
			}

		});
	}

	private void showToast(final String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}

	private void showAlertDialog(final String choise) {
		refreshAdapter(choise);
		setMessageAlertDialog(choise);
		showDialog(ID_ALERT_DIALOG);
	}

	private void refreshAdapter(final String remove_item) {
		ArrayList<String> list_cities = new ArrayList<String>(
				Arrays.asList(getResources().getStringArray(R.array.cities)));
		list_cities.remove(remove_item);
		adapter = new ArrayAdapter<String>(context,
				android.R.layout.simple_list_item_1, list_cities);
	}

	private void setMessageAlertDialog(final String message) {
		ad.setMessage(getString(R.string.alert_dialog_message, message));
	}

	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case ID_ALERT_DIALOG:
			ad.setPositiveButton(R.string.yes, new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					setListAdapter(adapter);
					showToast(getString(R.string.toast_city_remove));
					dialog.cancel();
				}
			}).setNegativeButton(R.string.no, new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			}).setCancelable(false);
			return ad.create();

		default:
			return super.onCreateDialog(id);
		}
	}

	private final int ID_ALERT_DIALOG = 0;

}
