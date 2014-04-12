package ayrat.salavatovich.gmail.com.day_151.simplehttpserver;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHttpd {

	private ServerThread thread;

	public static class SimpleHttpdSingleton {
		public static final SimpleHttpd INSTANCE = new SimpleHttpd();
	}

	public static SimpleHttpd getInstance() {
		return SimpleHttpdSingleton.INSTANCE;
	}

	public void start(final int port) {
		thread = new ServerThread(port);
		thread.setRunning(true);
		thread.start();
	}

	public void stop() {
		thread.setRunning(false);

		new Thread(new Runnable() {
			@Override
			public void run() {
				boolean done = false;
				while (!done) {
					try {
						thread.join();
						done = true;
					} catch (InterruptedException e) {
					}
				}
			}
		}).start();
	}

	public boolean hasStarted() {
		return thread != null;
	}

	public boolean isAlive() {
		return hasStarted() && thread.isAlive();
	}

	private static class ServerThread extends Thread {

		private final int PORT;
		private boolean running;

		public ServerThread(final int port) {
			this.PORT = port;
		}

		@Override
		public void run() {
			ServerSocket serverSocket;
			try {
				serverSocket = new ServerSocket(PORT);

				while (running) {
					Socket clientSocket = serverSocket.accept();

					PrintWriter out = new PrintWriter(new BufferedWriter(
							new OutputStreamWriter(
									clientSocket.getOutputStream())), true);

					out.write("HTTP/1.0 200 OK\r\n");
					out.write("Content-Type: text/html\r\n");
					out.write("\r\n");
					out.write("<html>");
					out.write("<head>");
					out.write("<title>Example</title>");
					out.write("</head>");
					out.write("<body>");
					out.write("<p>Welcome to my server!</p>");
					out.write("</body>");
					out.write("</html>");

					out.close();
					clientSocket.close();
				}
			} catch (Exception e) {
			}
		}

		public void setRunning(boolean running) {
			this.running = running;
		}
	}
}
