package ddt_excel;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Write_a_program_to_read_entire_column_data {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		FileInputStream fis = new FileInputStream("./data/testdata.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis);
		
		int rowCount = wb.getSheet("cred").getLastRowNum();
		
		for(int i = 0 ; i <= rowCount ; i++) {
			
			String data = wb.getSheet("cred").getRow(i).getCell(0).getStringCellValue();
			
			System.out.println(data);
		}
		
		wb.close();
	}
}
