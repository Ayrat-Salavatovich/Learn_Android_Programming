package ayrat.salavatovich.gmail.com.day_151.simplehttpserver;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private SimpleHttpd server = SimpleHttpd.getInstance();
	private final int SERVER_PORT = 4444;
	private TextView textViewServerStatus;
	private Button buttonStartServer;
	private Button buttonStopServer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textViewServerStatus = (TextView) findViewById(R.id.textViewServerStatus);

		buttonStartServer = (Button) findViewById(R.id.buttonStartServer);
		buttonStopServer = (Button) findViewById(R.id.buttonStopServer);

		buttonStartServer.setOnClickListener(onClickStartServerButtonListener);
		buttonStopServer.setOnClickListener(onClickStopServerButtonListener);

		displayServerStatus();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void startServer() {
		if (!server.isAlive())
			server.start(SERVER_PORT);
	}

	public void stopServer() {
		if (server.isAlive())
			server.stop();
	}

	private OnClickListener onClickStartServerButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			startServer();
		}
	};

	private OnClickListener onClickStopServerButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			stopServer();
		}
	};

	private void displayServerStatus() {
		Timer timer = new Timer();
		final Handler uiHandler = new Handler();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				final boolean serverIsAlive = server.isAlive();
				uiHandler.post(new Runnable() {
					@Override
					public void run() {
						if (serverIsAlive)
							textViewServerStatus.setText(R.string.runner);
						else
							textViewServerStatus.setText(R.string.stopped);
					}
				});
			}
		}, 0L, 1000);
	}
}
