package ayrat.salavatovich.gmail.com.day_61.expandablelistevents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.widget.SimpleExpandableListAdapter;

public class AdapterHelper {
	private final String ATTR_GROUP_NAME = "groupName";
	private final String ATTR_CHILD_NAME = "phoneName";

	private SimpleExpandableListAdapter adapter;

	private Map<String, List<String>> collection;

	private Context context;

	AdapterHelper(Context context) {
		this.context = context;
		collection = newCollection();
		adapter = newAdapter();
	}

	private Map<String, List<String>> newCollection() {
		Map<String, List<String>> collection = new HashMap<String, List<String>>();
		collection.put("Sony", Arrays.asList("Sony Xperia Z Ultra",
				"Xperia E Dual", "Xperia Z", "Xperia ZL", "Xperia L",
				"Xperia SP"));
		collection.put("HTC", Arrays.asList("One", "One max"));
		collection.put("Samsung", Arrays.asList("Galaxy S3 16 GB",
				"Galaxy S3 Mini i8190", "Galaxy S DUOS S7562",
				"Galaxy S2 Plus i9105P", "Galaxy Xcover 2 S7710",
				"Galaxy Young S6310N", "Galaxy S4 16GB LTE i9505",
				"Galaxy Tab 3 10.1 16GB LTE P5220", "Galaxy Young DUOS S6312",
				"Galaxy Note 8.0 3G N5100", "Galaxy Tab 3 7.0 8GB 3G T2110",
				"Galaxy Mega i9205", "Galaxy S4 mini i9195",
				"Galaxy Tab 3 10.1 16GB 3G P5200", "Galaxy S4 Active i9295",
				"Galaxy Note 10.1 2014 Edition LTE P", "Galaxy Gear V700"));
		collection.put("LG", Arrays.asList("E975 Optimus G",
				"E460 Optimus L5 II", "E430 Optimus L3 II",
				"P710 Optimus L7 II", "P875 Optimus F5", "Nexus 5 16GB",
				"G2 32GB"));
		collection.put("Caterpillar", Arrays.asList("B25"));
		collection.put("Nokia", Arrays.asList("Lumia 520", "Lumia 920",
				"Lumia 925", "Lumia 720", "Lumia 1520"));
		collection.put("Apple", Arrays.asList("iPhone 5 64GB"));
		collection
				.put("Motorola", Arrays.asList("RAZR i", "RAZR HD",
						"Moto G 8GB", "Moto G 16GB"));
		collection.put("Huawei", Arrays.asList("Ascend G510", "Ascend Y300",
				"Ascend P2", "Ascend Mate"));
		collection.put("Blackberry", Arrays.asList("Z10", "Q10", "Z30"));

		return collection;
	}

	private SimpleExpandableListAdapter newAdapter() {
		ArrayList<Map<String, String>> groupData;

		ArrayList<Map<String, String>> childDataItem;

		ArrayList<ArrayList<Map<String, String>>> childData;

		Map<String, String> item;

		groupData = new ArrayList<Map<String, String>>();
		for (String group : collection.keySet()) {
			item = new HashMap<String, String>();
			item.put(ATTR_GROUP_NAME, group);
			groupData.add(item);
		}

		String groupFrom[] = new String[] { ATTR_GROUP_NAME };
		int groupTo[] = new int[] { R.id.textViewGroup };

		childData = new ArrayList<ArrayList<Map<String, String>>>();
		for (List<String> phones : collection.values()) {
			childDataItem = new ArrayList<Map<String, String>>();
			for (String phone : phones) {
				item = new HashMap<String, String>();
				item.put(ATTR_CHILD_NAME, phone);
				childDataItem.add(item);
			}
			childData.add(childDataItem);
		}

		String childFrom[] = new String[] { ATTR_CHILD_NAME };
		int childTo[] = new int[] { R.id.textViewChild };

		return new SimpleExpandableListAdapter(context, groupData,
				R.layout.expandable_list_item, groupFrom, groupTo, childData,
				R.layout.list_item, childFrom, childTo);
	}

	SimpleExpandableListAdapter getAdapter() {
		return adapter;
	}

	String getGroupText(int groupPosition) {
		return ((Map<String, String>) (adapter.getGroup(groupPosition)))
				.get(ATTR_GROUP_NAME);
	}

	String getChildText(int groupPosition, int childPosition) {
		return ((Map<String, String>) (adapter.getChild(groupPosition,
				childPosition))).get(ATTR_CHILD_NAME);
	}

	String getGroupChildText(int groupPosition, int childPosition) {
		return getGroupText(groupPosition) + " "
				+ getChildText(groupPosition, childPosition);
	}
}
