package ayrat.salavatovich.gmail.com.day_30.logandmess;

public enum Priority {

	DEBUG(android.util.Log.DEBUG), ERROR(android.util.Log.ERROR), INFO(
			android.util.Log.INFO), VERBOSE(android.util.Log.VERBOSE), WARN(
			android.util.Log.WARN);

	Priority(int priorityId) {
		this.id = priorityId;
	}

	public int id;

}
