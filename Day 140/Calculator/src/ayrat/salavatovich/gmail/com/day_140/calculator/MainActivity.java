package ayrat.salavatovich.gmail.com.day_140.calculator;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private View background = null;
	private EditText editTextCalculate = null;
	private TextView textViewMemory = null;
	private Button buttonZero = null;
	private Button buttonOne = null;
	private Button buttonTwo = null;
	private Button buttonThree = null;
	private Button buttonFour = null;
	private Button buttonFive = null;
	private Button buttonSix = null;
	private Button buttonSeven = null;
	private Button buttonEight = null;
	private Button buttonNine = null;
	private Button buttonPlus = null;
	private Button buttonMinus = null;
	private Button buttonMultiply = null;
	private Button buttonDivide = null;
	private Button buttonEquals = null;
	private Button buttonC = null;
	private Button buttonDecimal = null;
	private Button buttonMC = null;
	private Button buttonMR = null;
	private Button buttonMM = null;
	private Button buttonMP = null;
	private Button buttonBS = null;
	private Button buttonPerc = null;
	private Button buttonSqr = null;
	private Button buttonPlusMinus = null;

	private double num = 0;
	private double memoryNum = 0;

	enum Operator {
		NOTHING, PLUS, MINUS, MULTIPLY, DIVIDE
	}

	private Operator operator = Operator.PLUS;

	private boolean readyToClear = false;
	private boolean hasChanged = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initControls();
		initScreenLayout();
		reset();
	}

	private void initControls() {
		background = findViewById(R.id.layoutLinear);

		editTextCalculate = (EditText) findViewById(R.id.editTextCalculate);
		textViewMemory = (TextView) findViewById(R.id.textViewMemory);

		buttonZero = (Button) findViewById(R.id.buttonZero);
		buttonOne = (Button) findViewById(R.id.buttonOne);
		buttonTwo = (Button) findViewById(R.id.buttonTwo);
		buttonThree = (Button) findViewById(R.id.buttonThree);
		buttonFour = (Button) findViewById(R.id.buttonFour);
		buttonFive = (Button) findViewById(R.id.buttonFive);
		buttonSix = (Button) findViewById(R.id.buttonSix);
		buttonSeven = (Button) findViewById(R.id.buttonSeven);
		buttonEight = (Button) findViewById(R.id.buttonEight);
		buttonNine = (Button) findViewById(R.id.buttonNine);
		buttonPlus = (Button) findViewById(R.id.buttonPlus);
		buttonMinus = (Button) findViewById(R.id.buttonMinus);
		buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
		buttonDivide = (Button) findViewById(R.id.buttonDivide);
		buttonEquals = (Button) findViewById(R.id.buttonEquals);
		buttonC = (Button) findViewById(R.id.buttonC);
		buttonDecimal = (Button) findViewById(R.id.buttonDecimal);
		buttonMC = (Button) findViewById(R.id.buttonMC);
		buttonMR = (Button) findViewById(R.id.buttonMR);
		buttonMM = (Button) findViewById(R.id.buttonMM);
		buttonMP = (Button) findViewById(R.id.buttonMP);
		buttonBS = (Button) findViewById(R.id.buttonBS);
		buttonPerc = (Button) findViewById(R.id.buttonPerc);
		buttonSqr = (Button) findViewById(R.id.buttonSqr);
		buttonPlusMinus = (Button) findViewById(R.id.buttonPlusMinus);

		buttonZero.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				handleNumber(0);
			}
		});
		buttonOne.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				handleNumber(1);
			}
		});
		buttonTwo.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				handleNumber(2);
			}
		});
		buttonThree.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				handleNumber(3);
			}
		});
		buttonFour.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				handleNumber(4);
			}
		});
		buttonFive.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				handleNumber(5);
			}
		});
		buttonSix.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				handleNumber(6);
			}
		});
		buttonSeven.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				handleNumber(7);
			}
		});
		buttonEight.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				handleNumber(8);
			}
		});
		buttonNine.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				handleNumber(9);
			}
		});
		buttonPlus.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				handleEquals(Operator.PLUS);
			}
		});
		buttonMinus.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				handleEquals(Operator.MINUS);
			}
		});
		buttonMultiply.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				handleEquals(Operator.MULTIPLY);
			}
		});
		buttonDivide.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				handleEquals(Operator.DIVIDE);
			}
		});
		buttonEquals.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				calc();
			}
		});
		buttonC.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				reset();
			}
		});
		buttonDecimal.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				handleDecimal();
			}
		});
		buttonPlusMinus.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				handlePlusMinus();
			}
		});
		buttonMC.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				memoryNum = 0;
				textViewMemory.setText(R.string.empty_string);
			}
		});
		buttonMR.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				if (memoryNum != .0) {
					setValue(Double.toString(memoryNum));

					setTextViewMemory(memoryNum);
					updateStateVariables(true, hasChanged);
				}
			}
		});
		buttonMM.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				memoryNum = memoryNum - Double.parseDouble(getDisplayText());
				setTextViewMemory(memoryNum);
				operator = Operator.NOTHING;
			}
		});
		buttonMP.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				memoryNum = memoryNum + Double.parseDouble(getDisplayText());
				setTextViewMemory(memoryNum);
				operator = Operator.NOTHING;
			}
		});
		buttonBS.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				handleBackspace();
			}
		});
		buttonSqr.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				setValue(Double.toString(Math.sqrt(Double
						.parseDouble(getDisplayText()))));
			}
		});
		buttonPerc.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				setValue(Double.toString(num
						* (.01 * Double.parseDouble(getDisplayText()))));
			}
		});

	}

	void setTextViewMemory(Double num) {
		textViewMemory.setText("M: " + Double.toString(num));
	}

	protected void handleBackspace() {
		if (!readyToClear) {
			String txt = getDisplayText();
			if (txt.length() > 1)
				txt = txt.substring(0, txt.length() - 1);
			else
				txt = "0";

			updateDisplay(txt);
		}
	}

	protected void saveNum(Double num) {
		this.num = num;
	}

	protected void setValue(String value) {
		if (operatorIsNothing(operator))
			reset();

		if (readyToClear) {
			readyToClear = false;
		}

		updateDisplay(value);

		updateStateVariables(true, true);
	}

	protected void handlePlusMinus() {
		if (!readyToClear) {
			String txt = getDisplayText();
			if (!txt.equals("0")) {
				if (txt.charAt(0) == '-')
					txt = txt.substring(1, txt.length());
				else
					txt = "-" + txt;

				updateDisplay(txt);
			}
		}
	}

	protected void handleDecimal() {
		if (operatorIsNothing(operator))
			reset();

		if (readyToClear) {
			updateDisplay("0.");

			updateStateVariables(false, true);
		} else {
			String txt = getDisplayText();
			if (!txt.contains(".")) {
				updateDisplay(".");

				updateStateVariables(readyToClear, true);
			}
		}
	}

	protected void reset() {
		num = 0;
		updateDisplay("0");
		resetOperator();
	}

	protected void resetOperator() {
		operator = Operator.PLUS;
	}

	protected void handleEquals(Operator operator) {
		if (hasChanged) {
			num = num + Double.parseDouble(getDisplayText());

			String txt = Double.toString(num);

			updateDisplay(txt);

			updateStateVariables(true, false);
		}

		this.operator = operator;
	}

	protected void calc() {
		if (hasChanged) {
			if (operator == Operator.PLUS)
				num = num + Double.parseDouble(getDisplayText());
			else if (operator == Operator.MINUS)
				num = num - Double.parseDouble(getDisplayText());
			else if (operator == Operator.MULTIPLY)
				num = num * Double.parseDouble(getDisplayText());
			else if (operator == Operator.DIVIDE)
				num = num / Double.parseDouble(getDisplayText());

			String txt = Double.toString(num);

			updateDisplay(txt);

			updateStateVariables(true, false);
		}
	}

	void updateDisplay(String text) {
		editTextCalculate.setText(text);
		editTextCalculate.setSelection(text.length());
	}

	void updateStateVariables(boolean readyToClear, boolean hasChanged) {
		this.readyToClear = readyToClear;
		this.hasChanged = hasChanged;
	}

	String getDisplayText() {
		return editTextCalculate.getText().toString();
	}

	protected void handleNumber(int i) {
		if (operatorIsNothing(operator))
			reset();

		String txt = getDisplayText();
		if (readyToClear) {
			txt = "";
			readyToClear = false;
		} else if (txt.equals("0"))
			txt = "";

		txt += Integer.toString(i);

		updateDisplay(txt);

		updateStateVariables(readyToClear, true);
	}

	private boolean operatorIsNothing(Operator operator) {
		return (operator == Operator.NOTHING ? true : false);
	}

	private void initScreenLayout() {
		background.setBackgroundColor(Color.GRAY);

		buttonZero.setTextColor(Color.MAGENTA);
		buttonOne.setTextColor(Color.MAGENTA);
		buttonTwo.setTextColor(Color.MAGENTA);
		buttonThree.setTextColor(Color.MAGENTA);
		buttonFour.setTextColor(Color.MAGENTA);
		buttonFive.setTextColor(Color.MAGENTA);
		buttonSix.setTextColor(Color.MAGENTA);
		buttonSeven.setTextColor(Color.MAGENTA);
		buttonEight.setTextColor(Color.MAGENTA);
		buttonNine.setTextColor(Color.MAGENTA);
		buttonPlusMinus.setTextColor(Color.MAGENTA);
		buttonDecimal.setTextColor(Color.MAGENTA);

		buttonMP.setTextColor(Color.BLUE);
		buttonMM.setTextColor(Color.BLUE);
		buttonMR.setTextColor(Color.BLUE);
		buttonMC.setTextColor(Color.BLUE);
		buttonBS.setTextColor(Color.BLUE);
		buttonC.setTextColor(Color.RED);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
