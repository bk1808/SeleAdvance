package cmdLine;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateOrgTest {

	public static void main(String[] args) throws IOException, InterruptedException {

		FileInputStream fis=new FileInputStream("./data/commondata.property");
		Properties p=new Properties();
		p.load(fis);
		String browser=p.getProperty("browser");
		String url=p.getProperty("url");
		String un=p.getProperty("username");
		String pwd=p.getProperty("password");
		
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
//		step-1: enter URL
		driver.get(url);
		
//		step-2: login to vtiger app
		driver.findElement(By.name("user_name")).sendKeys(un);
		driver.findElement(By.name("user_password")).sendKeys(pwd);
		driver.findElement(By.id("submitButton")).submit();
		Thread.sleep(1000);
		
//		step-3: navigate to organization module 
		driver.findElement(By.linkText("Organizations")).click();
//		step-4: click on create organization button
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
//		Additional: Add random number to organization name
		Random r=new Random();
		int randomNum = r.nextInt();
		
//   	step-5: enter organization name and click on save  		
		FileInputStream fin=new FileInputStream("./data/testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fin);
		String orgName = wb.getSheet("createOrg").getRow(1).getCell(2).toString()+randomNum;
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		
		Thread.sleep(2000);
//		ste-6: click on logout, close workbook and close the browser	
		Actions a=new Actions(driver);
		WebElement ele = driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
		a.moveToElement(ele).perform();
		
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		
		driver.quit();
		
	}

}
