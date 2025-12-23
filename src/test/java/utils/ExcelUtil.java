package utils;


import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.*;

public class ExcelUtil {
	
	
	public static Object[][] getExcelData(String filePath, String sheetName) {
		Object[][] data = null;
		
		try {	
//			InputStream fis = ExcelUtil.class
//                    .getClassLoader()
//                    .getResourceAsStream(filePath);
//
//            if (fis == null) {
//                throw new RuntimeException("Excel file not found: " + filePath);
//            }
			FileInputStream fis = new FileInputStream(filePath);
			Workbook workbook = WorkbookFactory.create(fis);

			Sheet sheet = workbook.getSheet(sheetName);
			if(sheet == null) {
			    throw new RuntimeException("Sheet " + sheetName + " not found in Excel file.");
			}
	            int rows = sheet.getPhysicalNumberOfRows();
	            int cols = sheet.getRow(0).getPhysicalNumberOfCells();

	            data = new Object[rows - 1][cols];

	            for (int i = 1; i < rows; i++) {
	                Row row = sheet.getRow(i);
	                for (int j = 0; j < cols; j++) {
	                    Cell cell = row.getCell(j);
	                    data[i - 1][j] = cell.toString();
	                }
	            }
	            workbook.close();
	            fis.close();
	            System.out.println(data);

	        } catch (Exception e ) {
	        	 throw new RuntimeException("Failed to read Excel data", e);
	        }

	        return data;
	        
	    }
	}