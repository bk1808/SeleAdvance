package ddt_cmdLine;

import org.testng.annotations.Test;

public class Write_program_to_pass_parameter_in_Maven_CMD {

	@Test
	public void readDataFromMvnCMD() {
		
		String browser = System.getProperty("browser");
		String url = System.getProperty("url");
		String username = System.getProperty("username");
		String password = System.getProperty("password");
		
		System.out.println(browser);
		System.out.println(url);
		System.out.println(username);
		System.out.println(password);
	}
	
}
