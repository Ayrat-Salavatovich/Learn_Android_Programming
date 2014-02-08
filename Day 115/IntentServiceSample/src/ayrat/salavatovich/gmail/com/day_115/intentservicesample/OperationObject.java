package ayrat.salavatovich.gmail.com.day_115.intentservicesample;

import android.os.Parcel;
import android.os.Parcelable;

public class OperationObject implements Parcelable {

	private Operation operation;

	public OperationObject(String operator) {
		operation = getOperation(operator);
	}

	public OperationObject(Operation operation) {
		this.operation = operation;
	}

	private OperationObject(Parcel parcel) {
		this.operation = (Operation) parcel.readValue(Operation.class
				.getClassLoader());
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public double apply(double number1, double number2) {
		return operation.eval(number1, number2);
	}

	private Operation getOperation(String operator) {
		if (operator.equals("+"))
			return Operation.ADD;
		else if (operator.equals("-"))
			return Operation.SUBTRACT;
		else if (operator.equals("*"))
			return Operation.MULTIPLY;
		else if (operator.equals("/"))
			return Operation.DIVIDE;

		return null;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeValue(operation);
	}

	public static final Parcelable.Creator<OperationObject> CREATOR = new Parcelable.Creator<OperationObject>() {

		@Override
		public OperationObject createFromParcel(Parcel in) {
			return new OperationObject(in);
		}

		@Override
		public OperationObject[] newArray(int size) {
			return new OperationObject[size];
		}

	};

}
