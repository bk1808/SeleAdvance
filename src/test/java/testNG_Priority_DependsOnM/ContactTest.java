package testNG_Priority_DependsOnM;

import org.testng.annotations.Test;

public class ContactTest {

//	execution of first three test methods happens in descending order i.e. -3,-2,-1
	@Test (priority = -1)
	public void createContactTest() {
	System.out.println("execute createContactTest");	
	}
	
	@Test (priority = 0)
	public void modifyContactTest() {
	System.out.println("execute modifyContactTest");	
	}
	
	@Test (priority = 3)
	public void deleteContactTest() {
	System.out.println("execute deleteContactTest");	
	}
	
//	execution of the below test methods happens in acsending order i.e. 1,2,3
	@Test (priority = -1)
	public void createContactTest1() {
	System.out.println("execute createContactTest");	
	}
	
	@Test (priority = 0)
	public void modifyContactTest1() {
	System.out.println("execute modifyContactTest");	
	}
	
	@Test (priority = 3)
	public void deleteContactTest1() {
	System.out.println("execute deleteContactTest");	
	}
	
//	execution without priority, if first test case fails remaing test case methods will still execute
	
	@Test 
	public void createContactTest2() {
	System.out.println("execute createContactTest-->HDFC");	
	}
	
	@Test 
	public void modifyContactTest2() {
	System.out.println("execute update, to insert contact into Data Base==ICICI");
	System.out.println("execute modifyContactTest--->ICICI=>ICICI_1");	
	}
	
	@Test 
	public void deleteContactTest2() {
	System.out.println("execute update, to insert contact into Data Base==UPI");
	System.out.println("execute deleteContactTest");	
	}
	
//	how to use Depends On Method
	
	@Test ()
	public void createContactTest3() {
		System.out.println("execute createContact with --> HDFC");
	}
	
	@Test (dependsOnMethods = "createContactTest3")
	public void modifyContact() {
	System.out.println("execute modifyContact--->HDFC==ICICI");	
	}
	
	@Test (dependsOnMethods = "modifyContact")
	public void deleteContact() {
		System.out.println("execute deleteContact==ICICI");
	}
	
}
