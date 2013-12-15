package ayrat.salavatovich.gmail.com.day_50.simplesqliteupdate;

public class Contact {
	public final Long id;
	public final String name;
	public final String email;

	public Contact(Long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

}
