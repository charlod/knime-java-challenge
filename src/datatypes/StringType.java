package datatypes;

import operations.Capitalizable;
import operations.Reversible;

public class StringType extends DataType implements Capitalizable, Reversible {
	private String val;
	private StringBuilder reversed;

	public StringType(String param) {
		this.val = param;
	}

	private void setVal(String newVal) {
		this.val = newVal;
	}

	public String getStringVal() {
		return val;
	}

	@Override
	public void capitalize() {
		setVal(this.val.toUpperCase());
	}

	@Override
	public void reverse() {
		reversed = new StringBuilder();
		reversed.append(this.val);
		reversed = reversed.reverse();
		setVal(removeLeadingZeros(reversed.toString()));
	}

	public static String removeLeadingZeros(String s) {
		return s.replaceFirst("^0+(?!$)", "");
	}

}
