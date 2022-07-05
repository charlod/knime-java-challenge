import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import datatypes.DataType;

public class Main {

	private static FileHandler fileHandler;
	private static ArrayList<String> transformedData;

	public static void main(String[] args) {

		Options options = getOptions();

		try {
			transformedData = new ArrayList<String>();

			fileHandler = parseOptions(options, args);
			fileHandler.readFile(fileHandler.inputFile);

			for (String data : fileHandler.fileDataInput) {
				DataType value = DataType.convertFromString(fileHandler.inputType, data);
				for (String operation : fileHandler.operations) {
					value = fileHandler.applyOperations(value, operation, fileHandler.inputType);
				}
				transformedData.add(value.getStringVal());
			}

			fileHandler.writeFile(fileHandler.outputFile, transformedData);
		} catch (ParseException e) {
			System.err.println("Parsing failed. Reason: " + e.getMessage());
			System.exit(1);
			return;
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
			System.exit(1);
			return;
		} catch (IOException e) {
			System.err.println("IO operation unsuccesful. Reason: " + e.getMessage());
			System.exit(1);
			return;
		}
	}

	private static Options getOptions() {

		Options options = new Options();

		Option input = new Option("input", true, "input file to read data from");
		Option type = new Option("inputtype", true, "input type");
		Option ops = Option.builder("o").longOpt("operations").required(true).valueSeparator(',')
				.desc("operations to perform").hasArgs().build();
		Option threadNum = new Option("threads", true, "num of threads");
		Option output = new Option("output", true, "output file to write the result");

		options.addOption(input);
		options.addOption(type);
		options.addOption(ops);
		options.addOption(threadNum);
		options.addOption(output);

		return options;
	}

	private static FileHandler parseOptions(Options options, String[] args) throws ParseException {

		CommandLineParser parser = new DefaultParser();
		CommandLine line = parser.parse(options, args);

		String inputFile = line.getOptionValue("input");
		String inputType = line.getOptionValue("inputtype");
		String[] operations = line.getOptionValues("operations");
		int threads = Integer.parseInt(line.getOptionValue("threads"));
		String outputFile = line.getOptionValue("output");

		return new FileHandler(inputFile, inputType, operations, threads, outputFile);
	}

}
