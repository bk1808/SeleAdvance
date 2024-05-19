package practice.pom.repository;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class SampleTestPOMAutoHealing {

	@FindBy (name = "user_name")
	private WebElement ele1;
	
	@FindBy (name = "user_password")
	private WebElement ele2;
	
//	The @FindAll annotation is called AutoHealing since it works, if there are multiple locators it uses internally [OR] condition and any one of the locators will be used to find the element
	@FindAll ({@FindBy(id = "submitButton"),@FindBy(xpath = "//input[@value='Login']")})
	private WebElement ele3;
	
//	[This annotaion we never user] The @FindBys annotation will use [AND] operator internally if there are multiple locators and it matches with all the locators to find the element.
	@FindBys({@FindBy(id = "submitButton"),@FindBy(xpath = "//input[@value='Login']")})
	private WebElement ele4;
	
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
		
		SampleTestPOMAutoHealing s = PageFactory.initElements(driver, SampleTestPOMAutoHealing.class);
		
		s.ele1.sendKeys("admin");//'s' holds the latest address
		s.ele2.sendKeys("admin");
	
		s.ele3.click();
		
	}
	
	
}
