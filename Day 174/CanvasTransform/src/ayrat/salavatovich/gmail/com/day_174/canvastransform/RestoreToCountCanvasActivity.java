package ayrat.salavatovich.gmail.com.day_174.canvastransform;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

public class RestoreToCountCanvasActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new DrawView(this));
	}

	class DrawView extends View {

		private Paint p;
		private RectF rectf1;
		private RectF rectf2;

		public DrawView(Context context) {
			super(context);

			p = new Paint();
			p.setStrokeWidth(3);
			p.setStyle(Paint.Style.STROKE);
			rectf1 = new RectF(50, 50, 100, 100);
			rectf2 = new RectF(50, 150, 100, 200);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawARGB(80, 102, 204, 255);

			// сохраняем настройки матрицы канвы
			// в initSave получаем значение для восстановления этого состояния
			int initSave = canvas.save();

			// зеленый квадрат
			p.setColor(Color.GREEN);
			canvas.drawRect(rectf1, p);

			// преобразования канвы
			// и рисование зеленых квадратов
			canvas.translate(100, 0);
			canvas.drawRect(rectf1, p);
			canvas.translate(100, 0);
			canvas.drawRect(rectf1, p);

			// сохраняем настройки матрицы канвы
			canvas.save();

			// преобразования канвы
			// и рисование желтых квадратов
			p.setColor(Color.YELLOW);
			canvas.translate(100, 0);
			canvas.drawRect(rectf1, p);
			canvas.translate(100, 0);
			canvas.drawRect(rectf1, p);
			canvas.translate(100, 0);

			// сохраняем настройки матрицы канвы
			// в needSave получаем значение для восстановления этого состояния
			int needSave = canvas.save();

			// преобразования канвы
			// и рисование красных квадратов
			p.setColor(Color.RED);
			canvas.translate(100, 0);
			canvas.drawRect(rectf1, p);
			canvas.translate(100, 0);
			canvas.drawRect(rectf1, p);

			// сохраняем настройки матрицы канвы
			canvas.save();

			// преобразования канвы
			// и рисование синих квадратов
			p.setColor(Color.BLUE);
			canvas.translate(100, 0);
			canvas.drawRect(rectf1, p);
			canvas.translate(100, 0);
			canvas.drawRect(rectf1, p);
			canvas.translate(100, 0);
			canvas.drawRect(rectf1, p);

			// белый квадрат
			p.setColor(Color.WHITE);
			canvas.drawRect(rectf2, p);

			// возврат канвы к указанному сохранению
			canvas.restoreToCount(needSave);

			// черный квадрат
			p.setColor(Color.BLACK);
			canvas.drawRect(rectf2, p);

			// возврат канвы к указанному сохранению
			canvas.restoreToCount(initSave);

			// пурпурный квадрат
			p.setColor(Color.MAGENTA);
			canvas.drawRect(rectf2, p);
		}

	}
}
