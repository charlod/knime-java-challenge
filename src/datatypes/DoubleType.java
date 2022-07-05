package datatypes;

import operations.Negatable;

public class DoubleType extends DataType implements Negatable {

	private double val;
	private String stringVal;
	private boolean negative = false;

	public DoubleType(String param) {
		this.val = Double.parseDouble(param);
		this.stringVal = param;
	}

	public double getVal() {
		return val;
	}

	private void setVal(Double newVal) {
		if (negative) {
			newVal *= -1;
		}
		this.val = newVal;
	}

	public String getStringVal() {
		return stringVal;
	}

	private void setStringVal(String newVal) {
		if (negative) {
			newVal = "-" + newVal;
		}
		this.stringVal = newVal;
	}

	@Override
	public void negate() {
		if (this.val == Double.MAX_VALUE || this.val == Double.MIN_VALUE) {
			throw new ArithmeticException("double overflow");
		}
		setVal(this.val * -1.0d);
		setStringVal(String.valueOf(this.val));
	}

}
