package ayrat.salavatovich.gmail.com.day_60.expandablelist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

public class MainActivity extends Activity {

	Map<String, List<String>> groups = new HashMap<String, List<String>>();

	ArrayList<Map<String, String>> groupData;

	ArrayList<Map<String, String>> childDataItem;

	ArrayList<ArrayList<Map<String, String>>> childData;

	Map<String, String> m;

	ExpandableListView expandableListViewMain;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		fillGroups();

		expandableListViewMain = (ExpandableListView) findViewById(R.id.expandableListViewMain);

		groupData = new ArrayList<Map<String, String>>();
		for (String group : groups.keySet()) {
			m = new HashMap<String, String>();
			m.put("groupName", group);
			groupData.add(m);
		}

		String groupFrom[] = new String[] { "groupName" };
		int groupTo[] = new int[] { android.R.id.text1 };

		childData = new ArrayList<ArrayList<Map<String, String>>>();
		for (List<String> phones : groups.values()) {
			childDataItem = new ArrayList<Map<String, String>>();
			for (String phone : phones) {
				m = new HashMap<String, String>();
				m.put("phoneName", phone);
				childDataItem.add(m);
			}
			childData.add(childDataItem);
		}

		String childFrom[] = new String[] { "phoneName" };
		int childTo[] = new int[] { android.R.id.text1 };

		SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
				this, groupData,
				android.R.layout.simple_expandable_list_item_1, groupFrom,
				groupTo, childData, android.R.layout.simple_list_item_1,
				childFrom, childTo);

		expandableListViewMain.setAdapter(adapter);
	}

	private void fillGroups() {
		groups.put("Sony", Arrays.asList("Sony Xperia Z Ultra",
				"Xperia E Dual", "Xperia Z", "Xperia ZL", "Xperia L",
				"Xperia SP"));
		groups.put("HTC", Arrays.asList("One", "One max"));
		groups.put("Samsung", Arrays.asList("Galaxy S3 16 GB",
				"Galaxy S3 Mini i8190", "Galaxy S DUOS S7562",
				"Galaxy S2 Plus i9105P", "Galaxy Xcover 2 S7710",
				"Galaxy Young S6310N", "Galaxy S4 16GB LTE i9505",
				"Galaxy Tab 3 10.1 16GB LTE P5220", "Galaxy Young DUOS S6312",
				"Galaxy Note 8.0 3G N5100", "Galaxy Tab 3 7.0 8GB 3G T2110",
				"Galaxy Mega i9205", "Galaxy S4 mini i9195",
				"Galaxy Tab 3 10.1 16GB 3G P5200", "Galaxy S4 Active i9295",
				"Galaxy Note 10.1 2014 Edition LTE P", "Galaxy Gear V700"));
		groups.put("LG", Arrays.asList("E975 Optimus G", "E460 Optimus L5 II",
				"E430 Optimus L3 II", "P710 Optimus L7 II", "P875 Optimus F5",
				"Nexus 5 16GB", "G2 32GB"));
		groups.put("Caterpillar", Arrays.asList("B25"));
		groups.put("Nokia", Arrays.asList("Lumia 520", "Lumia 920",
				"Lumia 925", "Lumia 720", "Lumia 1520"));
		groups.put("Apple", Arrays.asList("iPhone 5 64GB"));
		groups.put("Motorola",
				Arrays.asList("RAZR i", "RAZR HD", "Moto G 8GB", "Moto G 16GB"));
		groups.put("Huawei", Arrays.asList("Ascend G510", "Ascend Y300",
				"Ascend P2", "Ascend Mate"));
		groups.put("Blackberry", Arrays.asList("Z10", "Q10", "Z30"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
