package com.crm.generic.baseutility;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass {

	@BeforeSuite
	public void beforeSuiteTest()	{
		System.out.println("====connect to Database, Report config====");
	}
	
	@AfterSuite
	public void afterSuiteTest() {
		System.out.println("====close Databse, Report backup====");
	}
	
	@BeforeClass
	public void beforeClassTest()	{
		System.out.println("==launch the browser==");
	}
	
	@AfterClass
	public void afterClassTest() {
		System.out.println("==close the browser==");
	}
	
	@BeforeMethod
	public void beforeMethodTest()	{
		System.out.println("==login to application==");
	}
	@AfterMethod
	public void afterMethodTest() {
		System.out.println("==logout of application==");
	}
	
	
}
