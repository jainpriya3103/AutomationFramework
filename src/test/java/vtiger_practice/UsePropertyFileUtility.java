package vtiger_practice;

import java.io.IOException;

import genericUtilities.PropertyFileUtility;

public class UsePropertyFileUtility {

	public static void main(String[] args) throws IOException {
		PropertyFileUtility pf = new PropertyFileUtility();
		String value = pf.readDataFromPropertyFile("username");
		System.out.println(value);

	}

}
