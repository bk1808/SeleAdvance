package practice.orgTest;

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
import org.openqa.selenium.support.ui.Select;

public class CreateOrgWithIndustryTypeTest {

	public static void main(String[] args) throws IOException {
		/* TODO Converting manual test case into test script for regression testing to check whether the organization is created with 'Industry' and 'Type' drop down selection
		Expted result: Organization should be create and displayed in organization details page and display the message "ORG***" Organization Information
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
				int randomNum = r.nextInt(100);//set the upper limit
//				read test script data from the excel file, as we shouldn't hardcode
				
				FileInputStream fin=new FileInputStream("./data/testScriptData.xlsx");
				Workbook wb = WorkbookFactory.create(fin);
				String orgName = wb.getSheet("org").getRow(1).getCell(2).toString()+randomNum;
				String industry = wb.getSheet("org").getRow(4).getCell(3).toString();
				String type = wb.getSheet("org").getRow(4).getCell(4).toString();
				
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
				
//				step-2: navigate to organization module 
				driver.findElement(By.linkText("Organizations")).click();
				
//				step-3: click on create organization button
				driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
				
//				step-4: enter all the details and create new organization
				driver.findElement(By.name("accountname")).sendKeys(orgName);

//				step-5: select the industry dropdown value
				WebElement indEle = driver.findElement(By.name("industry"));
				Select s=new Select(indEle);
				s.selectByVisibleText(industry);
				
//				step-6: select the type dropdown value	
				WebElement typeEle = driver.findElement(By.name("accounttype"));
				Select st=new Select(typeEle);
				st.selectByVisibleText(type);
				
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
//				verify the drop-down industry and type info
				
				String actualIndustry = driver.findElement(By.id("dtlview_Industry")).getText();
				if(actualIndustry.equals(industry)) {
					System.out.println(industry+" is verified==PASS");
				}
				else {
					System.out.println(industry+" is not verified==FAIL");
				}
				String actualType = driver.findElement(By.id("dtlview_Type")).getText();
				if(actualType.equals(type)) {
					System.out.println(type+" is verified==PASS");
				}
				else {
					System.out.println(type+" is not verified==FAIL");
				}
				
//				step-7: cick on logout
				Actions a=new Actions(driver);
				WebElement ele = driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
				a.moveToElement(ele).perform();
				driver.findElement(By.linkText("Sign Out")).click();
				driver.quit();
			}

		}
