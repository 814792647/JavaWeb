package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class xlsx2sql {

	public XSSFWorkbook xlsx;
	
	public xlsx2sql(File file) {
		try {
			InputStream is = new FileInputStream(file);
			xlsx = new XSSFWorkbook(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String toSql() {
		try {
			XSSFSheet sheet = xlsx.getSheetAt(0);	
			XSSFRow row;
			row = sheet.getRow(0);
			return row.getCell(0).toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
