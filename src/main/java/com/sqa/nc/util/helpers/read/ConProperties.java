/**
 *   File Name: AppBasics.java<br>
 *
 *   Cui, Nicolas<br>
 *   Java Boot Camp Exercise<br>
 *   Instructor: Jean-francois Nepton<br>
 *   Created: Apr 2, 2016
 *
 */
package com.sqa.nc.util.helpers.read;

import java.io.*;
import java.util.*;

/**
 * AppBasics //ADDD (description of class)
 * <p>
 * //ADDD (description of core fields)
 * <p>
 * //ADDD (description of core methods)
 *
 * @author Cui, Nicolas
 * @version 1.0.0
 * @since 1.0
 */
public class ConProperties {

	public static HashMap<String, String> conProperties (String propertiesFileLocation) {
		// convert a text file into an ArrayList with each string element representing a separate line
		ArrayList<String> dataLines = evalTextDataLines(propertiesFileLocation);
		// display the contents which are stored in an ArrayList<String> type of variable
		//ConProperties.displayDataLines(dataLines);
		// further convert the ArrayList<String> into a HashMap collection of key/value
		HashMap<String, String> dataHashMap = ConProperties.convertLineContents(dataLines);
		// display the contents of a HashMap<String,String> to console
		ConProperties.displayDataContents(dataHashMap);
		return dataHashMap;
	}

	public static HashMap<String, String> convertLineContents (ArrayList<String> textFileLineContents) {
		HashMap<String, String> arrayListContents = new HashMap<>();
		String line, key, value;
		String[] tempLine;
		for (int i = 0; i < textFileLineContents.size(); i++) {
			// convert the line from ArrayList into an array with 2 elements representing key/value based on separator "="
			tempLine = textFileLineContents.get(i).trim().replace(" ", "").split("=");
			key = tempLine[0];
			value = tempLine[1];
			arrayListContents.put(key, value);
		}
		return arrayListContents;
	}

	public static void displayDataContents (HashMap<String, String> properties) {
		System.out.println("-----------------display data contents hashmap ver-------------------------------------------");
		for (String i : properties.keySet()) {
			System.out.println("Property = Value: " + i + " = " + properties.get(i));
		}
		return;
	}

	public static void displayDataLines (ArrayList<String> textFileLineContents) {
		System.out.println("display data lines ArrayList: " + textFileLineContents);
		return;
	}

	public static void displayProperties (Properties props) {
		System.out.println(props);

	}

	public static ArrayList<String> evalTextDataLines (String textFileLoadLocation) {
		//TODO create ArrayList and initialize it
		ArrayList<String> fileText = new ArrayList<String>();
		// this will reference one line at a time
		String line;
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(textFileLoadLocation);
			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				// TODO add the line to the ArrayList collection
				fileText.add(line);
			}
			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + textFileLoadLocation + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + textFileLoadLocation + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
		// TODO return the ArrayList collection
		return fileText;
	}

	// method to load properties
	public static Properties loadProperties (String fileLocation) {
		Properties props = new Properties();
		try {
			InputStream is = new FileInputStream(fileLocation);
			props.load(is);
		} catch (Exception e) {
		}
		return props;
	}

	public static void main (String[] args) throws IOException {
		ConProperties read = new ConProperties();
		read.nonStaticEvalTextDataLines("data02.properties");
		System.out.println("-----------------------------------------");
		conProperties("src/main/resources/data01.properties");
		conProperties("src/main/resources/data02.properties");
		conProperties("src/main/resources/data03.properties");
		System.out.println("----------------------------------------------");
		Properties props = ConProperties.loadProperties("src/main/resources/data01.properties");
		ConProperties.displayProperties(props);

	}

	public ArrayList<String> nonStaticEvalTextDataLines (String textFileLoadLocation) throws IOException {
		System.out.println("-----------non static ver -------------------------------");
		Properties prop = new Properties();
		InputStream inputStream;
		inputStream = getClass().getClassLoader().getResourceAsStream(textFileLoadLocation);
		//		this.inputStream = getClass().getClassLoader().getResourceAsStream("data01.properties");
		if (inputStream != null) {
			prop.load(inputStream);
		}
		// get the property value and print it out
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		String memberID = prop.getProperty("memberid");
		String role = prop.getProperty("role");
		String department = prop.getProperty("department");
		System.out.println("Username: " + username + "\nPassword: " + password + "\nMemberID: " + memberID + "\nrole: " + role
				+ "\ndepartment: " + department);
		inputStream.close();
		return null;
	}
}
