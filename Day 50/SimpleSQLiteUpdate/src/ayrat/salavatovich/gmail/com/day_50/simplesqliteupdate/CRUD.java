package ayrat.salavatovich.gmail.com.day_50.simplesqliteupdate;

public interface CRUD {
	public long create(Object object);

	public Object select();

	public int update(Object object);

	public int delete(Object object);
}
