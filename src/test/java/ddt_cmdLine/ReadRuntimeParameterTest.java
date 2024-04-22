package ddt_cmdLine;

import org.testng.annotations.Test;

public class ReadRuntimeParameterTest {

	@Test
	public void seleniumTest() {
//		with the help of Java class i.e. System we call a static method i.e. getProperty()
		String url=System.getProperty("url");
		String browser = System.getProperty("browser");
		String un = System.getProperty("username");
		String pw = System.getProperty("password");
		System.out.println("Env data==URL==>"+url);
		System.out.println("Env data==browser==>"+browser);
		System.out.println("Env data==username==>"+un);
		System.out.println("Env data==password==>"+pw);
	}
	
}
