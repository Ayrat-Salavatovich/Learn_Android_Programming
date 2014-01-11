package ayrat.salavatovich.gmail.com.day_82.customadapter;

public class Product {

	private boolean cart;
	private String name;
	private String description;
	private int picture;

	public Product(String name, String description, int picture, boolean cart) {
		this.name = name;
		this.description = description;
		this.cart = cart;
		this.picture = picture;
	}

	public void setCart(boolean cart) {
		this.cart = cart;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPicture(int picture) {
		this.picture = picture;
	}

	public boolean isCart() {
		return cart;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getPicture() {
		return picture;
	}

}
