package com.comcast.crm.contacttest;

import org.testng.annotations.Test;

public class TestMethod {

	
	@Test (priority = -1)
	public void createContact() {
	System.out.println("execute createContact");	
	}
	
	@Test (priority = 1)
	public void modifyContact() {
		System.out.println("execute modifyContact");	
	
	}
	@Test (priority = 0)
	public void editContact() {
		System.out.println("execute editContact");	
	
	}
	@Test (dependsOnMethods = "newContact")
	public void deleteContact() {
		System.out.println("execute deleteContact");	

	}
	@Test
	public void updateContact() {
		System.out.println("execute updateContact");	

	}
	@Test
	public void newContact() {
		System.out.println("execute newContact");	

	}
}
