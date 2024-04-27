package practice.pom.repository;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SampleTestWithPOM {

	@FindBy (name = "user_name")
	WebElement ele1;
	
	@FindBy (name = "user_password")
	WebElement ele2;
	
	@FindBy (id = "submitButton")
	WebElement ele3;
	
	
//	without initElements() method we will get null pointer exception, as @FindBy will not be executed without initializing the elements
	/*SampleTestWithPOM(WebDriver driver){
		PageFactory.initElements(driver, this);
	}

	public void loginPage(String username, String password) {
		ele1.sendKeys(username);
		ele2.sendKeys(password);
		ele3.click();
	}*/
	
	@Test
	public void sampleTest() {
		
		WebDriver driver=new ChromeDriver();
		driver.get("http://localhost:8888/");
		
		SampleTestWithPOM s = PageFactory.initElements(driver, SampleTestWithPOM.class);
		
		s.ele1.sendKeys("admin");//'s' holds the latest address
		s.ele2.sendKeys("admin");
		driver.navigate().refresh();/*we will not get StaleElementReferenceException 
		even though we are refreshing the page*/ 
	
		s.ele1.sendKeys("admin");
		s.ele2.sendKeys("admin");
		s.ele3.click();
		
	}
	
	
}
