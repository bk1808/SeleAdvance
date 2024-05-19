package ddt_jsonFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class ReadDataFromJSONtoCreateOrg {

	@Test
	public void createOrgUsingJSON() throws FileNotFoundException, IOException, ParseException, InterruptedException {
//	parse .json physical file into java object using JSONParse class, add 'json simple' dependency to pom.xml
	JSONParser parser= new JSONParser();
	Object obj = parser.parse(new FileReader("./data/appCommonData.json"));		
	
//	convert java object into json object using downcasting, it acts similar to hashmap	
	JSONObject map=(JSONObject)obj;//downcast from java object to json object

//	get the value from json using 'key', there's a method .get() and pass the key, since the return type of JSON .get() method is object we need to either do casting or convert to toString()
	
	String browser =(String) map.get("browser");
	String url = (String) map.get("url");
	String un = map.get("username").toString();
	String pwd = map.get("password").toString();
	
	WebDriver driver=null;
	if(browser.equals("chrome")) {
		driver=new ChromeDriver();
		}
		else if(browser.equals("firefox")) {
		driver=new FirefoxDriver();
		}
		else if(browser.equals("edge")) {
		driver=new EdgeDriver();
		}
		else {
			driver=new ChromeDriver();
	}
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//	step-1: enter URL
	driver.get(url);
	
//	step-2: login to vtiger app
	driver.findElement(By.name("user_name")).sendKeys(un);
	driver.findElement(By.name("user_password")).sendKeys(pwd);
	driver.findElement(By.id("submitButton")).submit();
	Thread.sleep(1000);
	
//	step-3: navigate to organization module 
	driver.findElement(By.linkText("Organizations")).click();
//	step-4: click on create organization button
	driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
	
//	Additional: Add random number to organization name
	Random r=new Random();
	int randomNum = r.nextInt();
	
//	step-5: enter organization name and click on save  		
	FileInputStream fin=new FileInputStream("./data/testScriptData.xlsx");
	Workbook wb = WorkbookFactory.create(fin);
	String orgName = wb.getSheet("createOrg").getRow(1).getCell(2).toString()+randomNum;
	driver.findElement(By.name("accountname")).sendKeys(orgName);
	driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
	
	Thread.sleep(3000);
//	ste-6: click on logout, close workbook and close the browser	
	Actions a=new Actions(driver);
	WebElement ele = driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
	a.moveToElement(ele).perform();
	
	driver.findElement(By.linkText("Sign Out")).click();
	driver.quit();
	
}
}