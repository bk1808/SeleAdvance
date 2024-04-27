package practice.contactTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateContactWithOrg_IT {

	public static void main(String[] args) throws IOException, InterruptedException {
//		NOTE: Pre-condition - at-least one organization should be created
		
		/* Converting manual test case into test script for regression testing to check whether the contact is created along with organization or not
		Expted result: Contacts should be created along with organization and displayed in contact details page and verify the contact 
		*/

//		step-1: read the data from property file
		FileInputStream fis=new FileInputStream("./data/commondata.property");
		Properties p=new Properties();
		p.load(fis);
		String browser = p.getProperty("browser");
		String url = p.getProperty("url");
		String un = p.getProperty("username");
		String pwd = p.getProperty("password");
		
//		create the random number using Random Class of Java.util package
		
		Random r=new Random();
		int randomNum = r.nextInt(1000);//set the upper limit
//		read test script data from the excel file, as we shouldn't hardcode
		
		FileInputStream fin=new FileInputStream("./data/testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fin);
		String orgName = wb.getSheet("contact").getRow(7).getCell(2).toString()+randomNum;
		String contactLastName = wb.getSheet("contact").getRow(7).getCell(3).toString()+randomNum;
		
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
//		step-1: login to application
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(un);
		driver.findElement(By.name("user_password")).sendKeys(pwd);
		driver.findElement(By.id("submitButton")).submit();
		
//		step-2: navigate to organization module 
		driver.findElement(By.linkText("Organizations")).click();
		
//		step-3: click on create organization button
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
//		step-4: enter all the details and create new organization
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
//		step-5: navigate to contact module
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@href='index.php?module=Contacts&action=index']")).click();
			
//		step-6: click on create contact button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
//`		step-7: enter all the details and create new contact
		driver.findElement(By.name("lastname")).sendKeys(contactLastName);

//		step-8: click on look-up window, select organization by searching 
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();

//		switch to child window
		Set<String> allWh = driver.getWindowHandles();
		Iterator<String> i=allWh.iterator();
		while(i.hasNext()) {
			String parent = i.next();
			String child = i.next();
			driver.switchTo().window(child);
			driver.findElement(By.id("search_txt")).sendKeys(orgName);
			driver.findElement(By.name("search")).click();
			Thread.sleep(4000);
			driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();//this xpath is created dynamically during run-time so we need to take the same reference to verify
			driver.switchTo().window(parent); 
		}
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
//		verify the header message expected result 
		String headerMessg=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if(headerMessg.contains(contactLastName)) {
			System.out.println(contactLastName+" is created===PASS");
		}
		else {
			System.out.println(contactLastName+" is not created==FAIL");
		}

//		verify the header orgName expected result 
		String actualOrgNameinfo=driver.findElement(By.id("mouseArea_Organization Name")).getText();
		
		if(actualOrgNameinfo.trim().equals(orgName)) {
			System.out.println(orgName+" is created===PASS");
		}else {
			System.out.println(orgName+" is not created===FAIL");
		}
		
//		step-6: cick on logout
		Actions a=new Actions(driver);
		WebElement ele = driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
		a.moveToElement(ele).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	}

}
