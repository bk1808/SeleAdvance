package practice.contactTest;

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

public class CreateContactWithMandatoryField {

	public static void main(String[] args) throws IOException {
		/* TODO Converting manual test case into test script for regression testing to check whether the contact is created with mandatory fields or not
		Expted result: Contacts should be create and displayed in contact details page along with mandatory fileds 
		*/
				
//				step-1: read the data from property file
				FileInputStream fis=new FileInputStream("./data/commondata.property");
				Properties p=new Properties();
				p.load(fis);
				String browser = p.getProperty("browser");
				String url = p.getProperty("url");
				String un = p.getProperty("username");
				String pwd = p.getProperty("password");
				
//				create the random number using Random Class of Java.util package
				
				Random r=new Random();
				int randomNum = r.nextInt(1000);//set the upper limit
//				read test script data from the excel file, as we shouldn't hardcode
				
				FileInputStream fin=new FileInputStream("./data/testScriptData.xlsx");
				Workbook wb = WorkbookFactory.create(fin);
				String lastName=wb.getSheet("contact").getRow(1).getCell(3).toString()+randomNum;
				
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
//				step-1: login to application
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				driver.get(url);
				driver.findElement(By.name("user_name")).sendKeys(un);
				driver.findElement(By.name("user_password")).sendKeys(pwd);
				driver.findElement(By.id("submitButton")).submit();
				
//				step-2: navigate to contacts module 
				driver.findElement(By.linkText("Contacts")).click();
				
//				step-3: click on create contacts button
				driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
				
//				step-4: enter all the details and create new organization
				driver.findElement(By.name("lastname")).sendKeys(lastName);

//				step-5: save the contact
							
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
//				verify the header and the contact info with last name mandatory field
				
				String actualContact = driver.findElement(By.className("dvHeaderText")).getText();
				if(actualContact.contains(lastName)) {
					System.out.println(lastName+" is created in header==PASS");
				}
				else {
					System.out.println(lastName+" is not created in header==FAIL");
				}
								
				String actualLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
				
				if(actualLastName.equals(lastName)) {
					System.out.println(lastName+" is created==PASS");
				}
				else {
					System.out.println(lastName+" is not created==FAIL");
				}
//				step-6: cick on logout
				Actions a=new Actions(driver);
				WebElement ele = driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
				a.moveToElement(ele).perform();
				driver.findElement(By.linkText("Sign Out")).click();
				driver.quit();
			}

		}
