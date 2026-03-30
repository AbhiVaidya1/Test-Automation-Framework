package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.ui.pojo.User;

public class CSVReaderUtility {
	
	public static Iterator<User> readCSVFile(String fileName) {
		File csvFile = new File(System.getProperty("user.dir")+"//testData//"+fileName);
		FileReader fileReader = null;
		CSVReader csvReader;
		String [] line;
		List<User> userList = null;
		User userData;
		// Never declare variables inside try catch block OR loop
		try {
			fileReader = new FileReader(csvFile);
			csvReader = new CSVReader(fileReader);
			csvReader.readNext(); //reads the column header of csv, i.e 1st line
//			csvReader.readNext(); //Row 2
//			data = csvReader.readNext(); //ROW 3
			//if there are no rows, we ll get null, as output format will be of string array
			
			userList= new ArrayList<User>();
			
			while((line = csvReader.readNext())!=null) {
				userData = new User(line[0],line[1]);
				userList.add(userData);
			}
			
//			for(User user:userList)
//			{
//				System.out.println(user); //Just to see the csv data
//			}
			
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		catch (CsvValidationException | IOException e) {
			
			e.printStackTrace();
		}
		return userList.iterator();
	}
	
	

}
