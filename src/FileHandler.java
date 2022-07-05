import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import datatypes.DataType;
import datatypes.DoubleType;
import datatypes.IntType;
import datatypes.StringType;
import operations.OperationTypes;

/**
 * FileHandler class performs file reading and writing as well as applies the
 * operations on the given input.
 * 
 * @author seray
 * 
 */
public class FileHandler {
	String inputFile;
	String inputType;
	String outputFile;
	String[] operations;
	ArrayList<String> fileDataInput;
	ArrayList<String> fileDataOutput;
	int threads;

	/**
	 * FileHandler instance creator
	 * 
	 * @param inputFile  name of the output file
	 * @param inputType  supported types: string, int, or double
	 * @param operations supported operations: capitalize, reverse, or negate
	 * @param threads    currently == 1, multithreading is not supported
	 * @param outputFile name of the output file
	 */
	public FileHandler(String inputFile, String inputType, String[] operations, int threads, String outputFile) {
		this.inputFile = inputFile;
		this.inputType = inputType;
		this.operations = operations;
		this.threads = threads;
		this.outputFile = outputFile;
	}

	/**
	 * Applies the operations in the order they have been stated in String[]
	 * operations.
	 * 
	 * @param value     StringType, IntType, DoubleType
	 * @param operation capitalize, reverse, or negate
	 * @param type      supported types: string, int, or double
	 * @return Anonymous {@link DataType} object of type either StringType, IntType,
	 *         or DoubleType
	 */
	public DataType applyOperations(DataType value, String operation, String type) {

		// opType is the enum constant of the specified enum type with
		// the specified operation name
		OperationTypes opType = OperationTypes.valueOf(operation.toUpperCase());

		switch (opType) {
		case CAPITALIZE:
			((StringType) value).capitalize();
			return value;
		case REVERSE:
			if (type.equals("string")) {
				((StringType) value).reverse();
			} else {
				((IntType) value).reverse();
			}
			return value;
		case NEGATE:
			if (type.equals("double")) {
				((DoubleType) value).negate();
			} else {
				((IntType) value).negate();
			}
			return value;
		}
		return value;
	}

	/**
	 * Reads file and stores the data in fileDataInput to later apply operations and
	 * write to a file.
	 * 
	 * @param fileName Name of the file to read
	 * @throws FileNotFoundException If the file does not exist
	 * @throws IOException           If an I/O error occurs
	 */
	public void readFile(String fileName) throws FileNotFoundException, IOException {

		ArrayList<String> lines = new ArrayList<String>();

		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = br.readLine();

		while (line != null) {
			lines.add(line);
			line = br.readLine();
		}

		fileDataInput = lines;
		br.close();
	}

	/**
	 * Writes the transformed data into the file named as fileName.
	 * 
	 * @param fileName Name of the file to write
	 * @param lines    Data to be written
	 * @throws IOException If an I/O error occurs
	 */
	public void writeFile(String fileName, ArrayList<String> lines) throws IOException {

		BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));

		for (String line : lines) {
			bw.write(line);
			bw.newLine();
		}

		bw.close();
	}
}
