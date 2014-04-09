package ayrat.salavatovich.gmail.com.day_145.jsonarray;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

public class IoUtil {

	private IoUtil() {
	}

	public static void closeCloseable(Reader closeable) {
		try {
			if (notNull(closeable)) {
				closeable.close();
			}
		} catch (IOException e) {
		}
	}

	public static void closeCloseable(InputStream closeable) {
		try {
			if (notNull(closeable)) {
				closeable.close();
			}
		} catch (IOException e) {
		}
	}

	public static void closeCloseable(OutputStream closeable) {
		try {
			if (notNull(closeable)) {
				closeable.flush();
				closeable.close();
			}
		} catch (IOException e) {
		}
	}

	public static void closeCloseable(Writer closeable) {
		try {
			if (notNull(closeable)) {
				closeable.flush();
				closeable.close();
			}
		} catch (IOException e) {
		}
	}

	public static void copyStreamAndClose(InputStream in, OutputStream out)
			throws IOException {
		final int blockSize = 512;
		byte[] buffer = new byte[blockSize];
		int received;
		try {
			while ((received = in.read(buffer)) != -1) {
				out.write(buffer, 0, received);
			}
		} finally {
			closeCloseable(in);
		}
	}

	public static byte[] readAllBytesAndClose(InputStream in)
			throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			copyStreamAndClose(in, out);
		} finally {
			closeCloseable(out);
		}
		return out.toByteArray();
	}

	public static boolean notNull(Object obj) {
		if (obj == null) {
			return false;
		}

		return true;
	}

}
