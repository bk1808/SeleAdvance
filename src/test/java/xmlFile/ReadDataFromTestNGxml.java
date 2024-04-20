package xmlFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

public class ReadDataFromTestNGxml {
//NOTE: run this class using testNG.xml file run as testNG
	@Test
	public void createOrgUsingXml(XmlTest test) throws EncryptedDocumentException, IOException, InterruptedException {
// reading the data from testNG.xml file which has <parameter> tag with name & value pair for data
		String browser = test.getParameter("browser");
		String url = test.getParameter("url");
		String un = test.getParameter("username");
		String pwd = test.getParameter("password");
		
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
		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(un);
		driver.findElement(By.name("user_password")).sendKeys(pwd);
		driver.findElement(By.id("submitButton")).submit();
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		Random r=new Random();
		int randomNum = r.nextInt();
//affed
//		Read testScriptData from excel file
		
		FileInputStream fis=new FileInputStream("./data/testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		
		String orgName = wb.getSheet("createOrg").getRow(1).getCell(2).toString()+randomNum;
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		
		Thread.sleep(2000);

		Actions a=new Actions(driver);
		WebElement ele = driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
		a.moveToElement(ele).perform();
		
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		
		driver.quit();
		
	}

}