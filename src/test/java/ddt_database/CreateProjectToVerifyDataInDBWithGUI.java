package ddt_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;

public class CreateProjectToVerifyDataInDBWithGUI {

	public static void main(String[] args) throws InterruptedException, SQLException {
		// Create a project in GUI using Selenium code

		String projectName="instagram_00100";
	
		WebDriver driver=new ChromeDriver();
		driver.get("http://106.51.90.215:8084/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign in']")).submit();
		driver.findElement(By.linkText("Projects")).click(); 
		driver.findElement(By.xpath("//span[contains(text(),'Create Project')]")).click();
		driver.findElement(By.name("projectName")).sendKeys(projectName);
		driver.findElement(By.name("createdBy")).sendKeys("Praveen");
		Thread.sleep(3000);
		WebElement ele = driver.findElement(By.xpath("//label[.='Project Status ']/following-sibling::select"));
		
		Select s=new Select(ele);
		s.selectByVisibleText("Created");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='Add Project']")).click();
	
//		Verify the project in DB backend using JDBC
		boolean flag=false;
		
		Driver driverRef=new Driver();
		DriverManager.registerDriver(driverRef);
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects", "root@%", "root");
		
		Statement stat = conn.createStatement();
		
		ResultSet resultSet = stat.executeQuery("select * from project");
		
		while(resultSet.next()) {
			String actualProj = resultSet.getString(4);
			if(projectName.equals(actualProj)) {
				flag=true;
				System.out.println(projectName+" is present in DB===PASS");
			}
		}
		if(flag==false) {
			System.out.println(projectName+" is not present in DB===FAIL");
		}
		
		conn.close();
	}

}
