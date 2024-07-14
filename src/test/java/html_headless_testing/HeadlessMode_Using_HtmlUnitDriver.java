package html_headless_testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.Test;

public class HeadlessMode_Using_HtmlUnitDriver {

	/* all the file upload scenario get failed since the browser itself is not there
	 * if we are using Robot class test script get failed
	 * browser rendering time is less, test script execution is faster since it's faster*/
	@Test
	public void headlessTestScriptTest() {
		
		WebDriver driver = new HtmlUnitDriver();
		driver.get("https://www.google.com/");
		System.out.println("=========Run"+driver.getTitle());
		
		
	}
	
}
