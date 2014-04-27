package ayrat.salavatovich.gmail.com.day_169.image;

import android.graphics.Bitmap;
import android.graphics.Color;

public class ConvolutionMatrix {

	public static final int SIZE = 3;

	public double[][] matrix;
	public double Factor = 1;
	public double Offset = 1;

	public static final int MAX_VALUE = 255;

	public ConvolutionMatrix(int size) {
		matrix = new double[size][size];
	}

	public void setAll(double value) {
		for (int x = 0; x < SIZE; ++x)
			for (int y = 0; y < SIZE; ++y)
				matrix[x][y] = value;
	}

	public void applyConfig(double[][] config) {
		for (int x = 0; x < SIZE; ++x)
			for (int y = 0; y < SIZE; ++y)
				matrix[x][y] = config[x][y];
	}

	public static Bitmap computeConvolution3x3(Bitmap src,
			ConvolutionMatrix convolutionMatrix) {
		int width = src.getWidth();
		int height = src.getHeight();
		Bitmap result = Bitmap.createBitmap(width, height, src.getConfig());

		int A, R, G, B;
		int sumR, sumG, sumB;
		int[][] pixels = new int[SIZE][SIZE];

		for (int y = 0; y < height - 2; ++y)
			for (int x = 0; x < width - 2; ++x) {

				// get pixel matrix
				for (int i = 0; i < SIZE; ++i)
					for (int j = 0; j < SIZE; ++j)
						pixels[i][j] = src.getPixel(x + i, y + j);

				// get alpha of center pixel
				A = Color.alpha(pixels[1][1]);

				// init color sum
				sumR = sumG = sumB = 0;

				// get sum of RGB on matrix
				for (int i = 0; i < SIZE; ++i) {
					for (int j = 0; j < SIZE; ++j) {
						sumR += (Color.red(pixels[i][j]) * convolutionMatrix.matrix[i][j]);
						sumG += (Color.green(pixels[i][j]) * convolutionMatrix.matrix[i][j]);
						sumB += (Color.blue(pixels[i][j]) * convolutionMatrix.matrix[i][j]);
					}
				}

				// get final Red
				R = getFinalColor(sumR, convolutionMatrix.Factor,
						convolutionMatrix.Offset);

				// get final Green
				G = getFinalColor(sumG, convolutionMatrix.Factor,
						convolutionMatrix.Offset);

				// get final Blue
				B = getFinalColor(sumB, convolutionMatrix.Factor,
						convolutionMatrix.Offset);

				// apply new pixel
				result.setPixel(x + 1, y + 1, Color.argb(A, R, G, B));
			}

		// final image
		return result;
	}

	private static int getFinalColor(int sumOfColor, double factor,
			double offset) {
		int result = (int) (sumOfColor / factor + offset);
		if (result < 0)
			result = 0;
		else if (result > ConvolutionMatrix.MAX_VALUE)
			result = ConvolutionMatrix.MAX_VALUE;

		return result;
	}

}
