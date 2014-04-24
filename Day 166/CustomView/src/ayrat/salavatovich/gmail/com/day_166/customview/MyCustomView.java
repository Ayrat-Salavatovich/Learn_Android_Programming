package ayrat.salavatovich.gmail.com.day_166.customview;

import java.util.Random;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyCustomView extends View {
	
	protected int colorView = Color.WHITE;
	protected Random rand = new Random();
	protected Paint paint;

	public MyCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		prepare();
	}

	public MyCustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		prepare();
	}

	public MyCustomView(Context context) {
		super(context);
		prepare();
	}
	
	protected void prepare() {
		paint = new Paint();
	}
	
	public void setColorView(int color) {
		colorView = color;
	}

	public void setCrazyColorView() {
		int red = rand.nextInt(255);
		int green = rand.nextInt(255);
		int blue = rand.nextInt(255);

		colorView = Color.rgb(red, green, blue);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(getSuggestedMinimumWidth(), getSuggestedMinimumHeight());
	}

}
