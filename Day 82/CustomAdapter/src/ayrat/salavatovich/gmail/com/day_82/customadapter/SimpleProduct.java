package ayrat.salavatovich.gmail.com.day_82.customadapter;

import java.util.LinkedHashMap;
import java.util.Map;

public class SimpleProduct extends Product {

	private float price;

	public SimpleProduct(String name, String description, int picture,
			boolean cart, float price) {
		super(name, description, picture, cart);
		this.price = price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getPrice() {
		return price;
	}

	public Map<String, String> toMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("name", getName());
		map.put("description", getDescription());
		map.put("picture", String.valueOf(getPicture()));
		map.put("cart", String.valueOf(isCart()));
		map.put("price", String.valueOf(price));

		return map;
	}

}
