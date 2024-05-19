package com.comcast.crm.contacttest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateContactTestUsingDataProvider {

	@Test (dataProvider = "getData")
	public void createContact(String firstName, String lastName) {
		
		System.out.println("First Name: "+firstName+", "+"Last Name: "+lastName);
	}
	 
	
	@DataProvider
	public Object [][] getData(){
		
		Object [] [] objArray = new Object [3] [2];//3 is number of times/rows, 2 is number of arguments/colums
		objArray [0][0]="Deepak";
		objArray [0][1]="HR";
		
		objArray [1][0]="Sam";
		objArray [1][1]="HD";
		
		objArray [2][0]="John";
		objArray [2][1]="Steve";
		
		return objArray;
	}
}
