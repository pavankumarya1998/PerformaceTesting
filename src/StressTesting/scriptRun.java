package StressTesting;

import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;


public class scriptRun {
	public static void main(String[] args) {
		
	    String excelFilePath = "C:\\Users\\pavan\\Downloads\\downloadUploadInvalidUsers (6).xlsx";

	    try (FileInputStream fis = new FileInputStream(excelFilePath);
	         Workbook workbook = new XSSFWorkbook(fis)) {

	        Sheet sheet = workbook.getSheetAt(0);
	        int loginCount = 0;

	        for (Row row : sheet) {
	            if (loginCount >= 20) {
	                break;
	            }

	            Cell usernameCell = row.getCell(0);
	            Cell passwordCell = row.getCell(1);

	            if (usernameCell != null && passwordCell != null) {
	                String username = usernameCell.getStringCellValue();
	                String password = passwordCell.getStringCellValue();

	                Thread loginThread = new Thread(new LoginTask(username, password));
	                loginThread.start();
	                loginCount++;
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}


}
