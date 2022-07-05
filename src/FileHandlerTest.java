
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import datatypes.DataType;
import datatypes.DoubleType;
import datatypes.IntType;
import datatypes.StringType;

class FileHandlerTest {

	FileHandler fileHandler;

	@BeforeEach
	void setUp() {
		fileHandler = new FileHandler("example.txt", "double", new String[] { "negate" }, 1, "output.txt");
	}

	@Test
	void testApplyOperations() {
		DataType valueCap = fileHandler.applyOperations(new StringType("hello"), "capitalize", "string");
		String expectedCap = "HELLO";
		String resultCap = valueCap.getStringVal();

		DataType valueRev = fileHandler.applyOperations(new StringType("hello"), "reverse", "string");
		String expectedRev = "olleh";
		String resultRev = valueRev.getStringVal();

		DataType valueInt = fileHandler.applyOperations(new IntType("50"), "reverse", "int");
		String expectedInt = "5";
		String resultInt = valueInt.getStringVal();

		DataType valueIntNeg = fileHandler.applyOperations(new IntType("50"), "negate", "int");
		String expectedIntNeg = "-50";
		String resultIntNeg = valueIntNeg.getStringVal();

		DataType valueDouble = fileHandler.applyOperations(new DoubleType("5.0"), "negate", "double");
		String expectedDouble = "-5.0";
		String resultDouble = valueDouble.getStringVal();

		assertEquals(expectedCap, resultCap);
		assertEquals(expectedRev, resultRev);
		assertEquals(expectedInt, resultInt);
		assertEquals(expectedIntNeg, resultIntNeg);
		assertEquals(expectedDouble, resultDouble);
		assertThrows(IllegalArgumentException.class,
				() -> fileHandler.applyOperations(new StringType("hello"), "lowercase", "string"));
	}

	@Test
	void testReadFile() throws FileNotFoundException {
		assertThrows(FileNotFoundException.class, () -> fileHandler.readFile(fileHandler.inputFile));
	}

	@Test
	void testWriteFile() throws NullPointerException {
		assertThrows(NullPointerException.class,
				() -> fileHandler.writeFile(fileHandler.outputFile, fileHandler.fileDataOutput));
	}
}
