package datatypes;

public abstract class DataType {

	public abstract String getStringVal();

	public static DataType convertFromString(String type, String line) {

		switch (type) {
		case "string":
			return new StringType(line);
		case "int":
			return new IntType(line);
		case "double":
			return new DoubleType(line);
		}

		throw new IllegalArgumentException("Data type is not known: " + type);
	}
}
