package ddt_excel;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadMultipleDataFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
			
		// step-1: get the excel path location of the physical file
				FileInputStream fis=new FileInputStream("./data/testScriptData.xlsx");
				
		// step-2: open workbook in read mode
				Workbook wb=WorkbookFactory.create(fis);
				
		// step-3: get the control of the sheet in workbook
				Sheet sh=wb.getSheet("multiData");
				
		// step-4: get the the overall row count which has data present
				int rowCount = sh.getLastRowNum();
				
		// step-5: iterate the value using forLoop
				for(int i=1 ; i<=rowCount ; i++) {
					String data1 = sh.getRow(i).getCell(0).toString();
					String data2 = sh.getRow(i).getCell(1).toString();
					System.out.println(data1+"\t"+data2);
				}
				
				
				
//		step-6: close workbook
				wb.close();
				
	}

}
