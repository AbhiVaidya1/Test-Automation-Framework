package com.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ui.pojo.User;

public class ExcelReaderUtility {
	public static Iterator<User> readExcelFile(String fileName) {
		
		File xlsxFile = new File(System.getProperty("user.dir")+"//testData//"+fileName);
		//XLSX File
		XSSFWorkbook xssfWorkbook = null;
		List<User> userList = null;
		Row row;
		Cell emailAddressCell;
		Cell passwordCell;
		User user;
		XSSFSheet xssfSheet;
		Iterator<Row> rowIterator;
		try {
			xssfWorkbook = new XSSFWorkbook(xlsxFile);
			userList = new ArrayList<User>();
			
			xssfSheet= xssfWorkbook.getSheet("loginTestData");
			rowIterator = xssfSheet.iterator();
			rowIterator.next(); //skips the col Name i.e 1st row of excel
			while(rowIterator.hasNext()) {
				row = rowIterator.next();
				emailAddressCell = row.getCell(0);
				passwordCell = row.getCell(1);
				user = new User(emailAddressCell.toString(),passwordCell.toString());
				userList.add(user);
//				System.out.println(firstCell.toString());
//				System.out.println(secondCell.toString());
				xssfWorkbook.close();
			}
		} catch (InvalidFormatException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return userList.iterator();
		
	}
}
