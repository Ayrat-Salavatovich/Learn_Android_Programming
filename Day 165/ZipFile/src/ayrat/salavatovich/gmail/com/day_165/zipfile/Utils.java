package ayrat.salavatovich.gmail.com.day_165.zipfile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Utils {

	public static final int BUFFER = 2048;

	public static void compress(String file, String zipFile) {
		try {
			BufferedInputStream inBufferStream = new BufferedInputStream(
					new FileInputStream(file), BUFFER);

			FileOutputStream targetOutputStream = new FileOutputStream(zipFile);
			ZipOutputStream outZipStream = new ZipOutputStream(
					new BufferedOutputStream(targetOutputStream));

			byte data[] = new byte[BUFFER];

			ZipEntry entry = new ZipEntry(
					file.substring(file.lastIndexOf("/") + 1));
			outZipStream.putNextEntry(entry);
			int count;
			while ((count = inBufferStream.read(data, 0, BUFFER)) != -1) {
				outZipStream.write(data, 0, count);
			}

			inBufferStream.close();
			outZipStream.close();

		} catch (Exception ignore) {
		}
	}

	public static void decompress(String zipFile, String pathToUnzip) {

		try {
			ZipInputStream zipIn = new ZipInputStream(new FileInputStream(
					zipFile));
			ZipEntry entry = null;
			while ((entry = zipIn.getNextEntry()) != null) {
				if (!entry.isDirectory()) {
					FileOutputStream fout = new FileOutputStream(pathToUnzip
							+ "/" + entry.getName());
					for (int c = zipIn.read(); c != -1; c = zipIn.read()) {
						fout.write(c);
					}
					zipIn.closeEntry();
					fout.close();
				}
			}

			zipIn.close();
		} catch (Exception ignore) {
		}
	}

}
