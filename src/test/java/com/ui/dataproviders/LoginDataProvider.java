package com.ui.dataproviders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.google.gson.Gson;
import com.ui.pojo.TestData;
import com.ui.pojo.User;
import com.utility.CSVReaderUtility;
import com.utility.ExcelReaderUtility;

public class LoginDataProvider {

	@DataProvider(name = "LoginTestDataProvider")
	public Iterator<Object[]> loginDataProvider() throws FileNotFoundException {
		
		Gson gson = new Gson();
		File testDataFile = new File(System.getProperty("user.dir")+"\\testData\\loginData.json");
		FileReader fileReader = new FileReader(testDataFile);
		TestData data = gson.fromJson(fileReader, TestData.class); //Deserializatoin - converting gson object to Java Object
		
		List<Object[]> dataToReturn = new ArrayList<Object[]>();
		for(User user:data.getData()) { // gets to the json file and attaches to the array list
			dataToReturn.add(new Object[] {user});
		}
		return dataToReturn.iterator();
	}
	
	//This created DataProvider reaches out to the CSV ReaderUtility and then reads the file logindta.csv
	@DataProvider(name = "LoginTestCSVDataProvider")
	public Iterator<User> loginCSVDataProvider() {
		return CSVReaderUtility.readCSVFile("loginData.csv");
	}
	@DataProvider(name = "LoginTestExcelDataProvider")
	public Iterator<User> loginExcelDataProvider() {
		return ExcelReaderUtility.readExcelFile("loginData.xlsx");
	}
}
