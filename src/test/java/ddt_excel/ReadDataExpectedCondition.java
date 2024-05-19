package ddt_excel;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataExpectedCondition {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		String expectedID="tc_02";
		String data="";
		String data1="";
		String data2="";
		String data3="";
		boolean flag=false;
		FileInputStream fis = new FileInputStream("./testData/testScriptData.xlsx");

		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("org");
		int rowcount = sh.getLastRowNum();
		for(int i=0 ; i<=rowcount ; i++) {

			try {
				data=sh.getRow(i).getCell(0).toString();
				if(data.equals(expectedID)) {
					flag=true;
					data1=sh.getRow(i).getCell(1).toString();
					data2=sh.getRow(i).getCell(2).toString();
					data3=sh.getRow(i).getCell(3).toString();
				}
			}
			catch(Exception e) {

			}

		}
		if(flag==true) {
			System.out.println(data1+"--"+data2+"--"+data3+" ");
		}
		else {
			System.out.println(expectedID + " data is not present");
		}
		wb.close();

	}


}
