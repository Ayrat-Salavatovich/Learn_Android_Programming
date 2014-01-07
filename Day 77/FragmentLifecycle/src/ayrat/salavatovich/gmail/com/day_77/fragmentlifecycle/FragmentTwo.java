package ayrat.salavatovich.gmail.com.day_77.fragmentlifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentTwo extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		log(new Exception().getStackTrace()[0].getMethodName());
		return inflater.inflate(R.layout.fragment_two, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		log(new Exception().getStackTrace()[0].getMethodName());
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
		log(new Exception().getStackTrace()[0].getMethodName());
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		log(new Exception().getStackTrace()[0].getMethodName());
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onDestroy() {
		log(new Exception().getStackTrace()[0].getMethodName());
		super.onDestroy();
	}

	@Override
	public void onDestroyView() {
		log(new Exception().getStackTrace()[0].getMethodName());
		super.onDestroyView();
	}

	@Override
	public void onDetach() {
		log(new Exception().getStackTrace()[0].getMethodName());
		super.onDetach();
	}

	@Override
	public void onPause() {
		log(new Exception().getStackTrace()[0].getMethodName());
		super.onPause();
	}

	@Override
	public void onResume() {
		log(new Exception().getStackTrace()[0].getMethodName());
		super.onResume();
	}

	@Override
	public void onStart() {
		log(new Exception().getStackTrace()[0].getMethodName());
		super.onStart();
	}

	@Override
	public void onStop() {
		log(new Exception().getStackTrace()[0].getMethodName());
		super.onStop();
	}

	private void log(String msg) {
		Log.w(new Exception().getStackTrace()[0].getClassName(), msg);
	}

}
