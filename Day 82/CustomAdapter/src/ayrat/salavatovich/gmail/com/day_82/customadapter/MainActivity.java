package ayrat.salavatovich.gmail.com.day_82.customadapter;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ArrayList<SimpleProduct> products = new ArrayList<SimpleProduct>();
	private MarketAdapter adapter;
	private final int MAX_COUNT_PRODUCTS = 33;
	private final float MIN_PRICE_PRODUCT = 100;
	private final float MAX_PRICE_PRODUCT = 10000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		fillMarket();
		adapter = new MarketAdapter(this, products);

		ListView listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(adapter);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button:
			StringBuilder result = new StringBuilder();

			for (SimpleProduct p : adapter.getCart())
				result.append(p.getName() + System.lineSeparator());

			Toast.makeText(this, result.toString(), Toast.LENGTH_LONG).show();
			break;

		default:
			break;
		}
	}

	private void fillMarket() {
		for (int i = 0; i <= MAX_COUNT_PRODUCTS; i++) {
			products.add(new SimpleProduct("Product " + i, "Product " + i,
					R.drawable.ic_launcher, false, randomPrice(
							MIN_PRICE_PRODUCT, MAX_PRICE_PRODUCT)));
		}
	}

	private float randomPrice(float start, float end) {
		float random = new Random().nextFloat();
		return start + (random * (end - start));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
