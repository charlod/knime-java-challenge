package datatypes;

import operations.Negatable;
import operations.Reversible;

public class IntType extends DataType implements Reversible, Negatable {
	private int val;
	private String stringVal;
	private StringBuilder reversed;
	private boolean negative = false;

	public IntType(String param) {
		this.val = Integer.parseInt(param);
		this.stringVal = param;
	}

	public int getVal() {
		return val;
	}

	private void setVal(int newVal) {
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
	public void reverse() {

		reversed = new StringBuilder();
		reversed.append(this.stringVal);

		if (this.stringVal.startsWith("-")) {
			reversed.deleteCharAt(0);
			negative = true;
		}

		reversed = reversed.reverse();

		setStringVal(StringType.removeLeadingZeros(reversed.toString()));
		setVal(Integer.parseInt(StringType.removeLeadingZeros(reversed.toString())));
	}

	@Override
	public void negate() {

		if (this.val == Integer.MAX_VALUE || this.val == Integer.MIN_VALUE) {
			throw new ArithmeticException("integer overflow");
		}

		setVal(this.val * -1);
		setStringVal(String.valueOf(this.val));
	}
}
