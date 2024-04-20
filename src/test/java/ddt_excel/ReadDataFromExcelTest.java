package ddt_excel;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcelTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {

// step-1: get the excel path location of the physical file
		FileInputStream fis=new FileInputStream("./data/testScriptData.xlsx");
		
// step-2: open workbook in read mode
		Workbook wb=WorkbookFactory.create(fis);
		
// step-3: get the control of the sheet in workbook
		Sheet sh=wb.getSheet("org");
		
// step-4: get the control of the row
		Row row=sh.getRow(1);
		
// step-5: get the control of the cell and read the string data
		String data = row.getCell(2).getStringCellValue();
		int physicalNum = row.getPhysicalNumberOfCells();
		System.out.println(physicalNum);
		double numValue = row.getCell(4).getNumericCellValue();
//		To make the numeric value as String prefix [single quote] '200 to number
		System.out.println(numValue);
		System.out.println(data);
		
// step-6: close the workbook
		wb.close();

// NOTE: In place of getStringCellValue() we can also use toString() method as it's @Overridden
	}

}
