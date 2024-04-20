package randomNumber;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

public class ReadRandomNumber {

	public static void main(String[] args) throws EncryptedDocumentException, IOException, ParseException {
/*With the help of Java Random class[implements RandomGenerator I] present in Util package we are calling a nextInt() abstract method of RandomGenerator Interface 
		Read random number from excel file*/
		Random r=new Random();
		int randomNum=r.nextInt(1000);
		
		FileInputStream fis=new FileInputStream("./data/testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data=wb.getSheet("createOrg").getRow(1).getCell(2).getStringCellValue()+randomNum;
		
		System.out.println(data);
		
//		To read random number from property file
		FileInputStream fin=new FileInputStream("./data/commondata.property");
		
		Properties p=new Properties();
		p.load(fin);
		String br = p.getProperty("browser");
		String url = p.getProperty("url");
		String un = p.getProperty("username")+randomNum;
		String un1 = p.getProperty("username");
		
		String pw = p.getProperty("password");
		
		System.out.println(br);
		System.out.println(url);
		System.out.println(un1+randomNum);
		System.out.println(un);
		System.out.println(pw);
		
//		parse .json physical file into java object using JSONParse class, add 'json simple' dependency to pom.xml
		JSONParser parser= new JSONParser();
		Object obj = parser.parse(new FileReader("./data/appCommonData.json"));		
		
//		convert java object into json object using downcasting, it acts similar to hashmap	
		JSONObject map=(JSONObject)obj;//downcast from java object to json object

//		get the value from json using 'key'
		System.out.println(map.get("url"));//there's a method .get() and pass the key
		System.out.println(map.get("browser"));
		System.out.println(map.get("username"));
		System.out.println(map.get("password"));
		String org = map.get("organization").toString()+randomNum;
		System.out.println(org);
		
	}

}
