package ddt_excel;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excel_Practice {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		String expectedData = "tc_02";

		String actualData = "";
		String data1 = "";
		String data2 = "";
		String data3 = "";
		String data4 = "";
		boolean flag = false;

		FileInputStream fis = new FileInputStream("./testData/testScriptData.xlsx");

		Workbook wb = WorkbookFactory.create(fis);

		int rowCount = wb.getSheet("org").getLastRowNum();
		Sheet sh = wb.getSheet("org");

		for (int i = 0; i <= rowCount; i++) {

			try {
				actualData = wb.getSheet("org").getRow(i).getCell(0).getStringCellValue();

				if (actualData.equals(expectedData)) {
					flag = true;
					data1 = sh.getRow(i).getCell(1).getStringCellValue();
					data2 = sh.getRow(i).getCell(2).getStringCellValue();
					data3 = sh.getRow(i).getCell(3).getStringCellValue();
					data4 = sh.getRow(i).getCell(4).getStringCellValue();
				}
			}

			catch (Exception e) {

			}

		}
		if (flag == true) {

			System.out.println(actualData + "--" + data1 + "--" + data2 + "--" + data3 + "--" + data4);
		} else {
			System.out.println(expectedData + " is not present");
		}
	}
}
