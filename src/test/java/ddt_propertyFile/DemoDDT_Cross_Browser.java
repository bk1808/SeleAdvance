package ddt_propertyFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DemoDDT_Cross_Browser {
	
	public static void main(String[] args) throws IOException, InterruptedException {
	FileInputStream fis=new FileInputStream("./data/commondata.property");	
		
	Properties p = new Properties();
	p.load(fis);
	String browser=p.getProperty("browser");
	String url=p.getProperty("url");
	String un=p.getProperty("username");
	String pw=p.getProperty("password");
	
	//Login to the application using the values from property file
	
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
	driver.findElement(By.name("user_password")).sendKeys(pw);
	driver.findElement(By.id("submitButton")).submit();
	Thread.sleep(1000);
	driver.findElement(By.linkText("Organizations")).click();
	driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
	driver.findElement(By.name("accountname")).sendKeys("ORG_02");
	driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[1]")).click();
	}
	
}
