package ddt_excel;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Write_a_program_to_read_entire_row_data {

	public static void main(String[] args) throws  IOException {

		FileInputStream fis = new FileInputStream("./data/testScript.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis);
		
		int cellCount = wb.getSheet("createCustomer").getRow(1).getLastCellNum();
		
		for(int i = 0 ; i<cellCount ; i++) {
			
			String data = wb.getSheet("createCustomer").getRow(1).getCell(i).getStringCellValue();
			System.out.println(data);
			
		}
	}

}
