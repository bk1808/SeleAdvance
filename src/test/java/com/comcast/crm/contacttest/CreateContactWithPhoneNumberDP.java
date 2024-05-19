package com.comcast.crm.contacttest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateContactWithPhoneNumberDP {

	@Test (dataProvider = "getData")
	public void createContact(String firstName, String lastName, long phoneNumber) {
	
	System.out.println("First Name: "+firstName+", "+"Last Name: "+lastName+", "+"Phone Number: "+phoneNumber);
		
	}
	
	@DataProvider 
	public Object [] [] getData() {
		
		Object [] [] objArray=new Object [3] [3];//3 time/rows, 3 arguments/columns
		objArray [0] [0]="Gagan";
		objArray [0] [1]="D";
		objArray [0] [2] = 9886644822L;//long data, if not declared it will consider as int data type
	
		objArray [1] [0]="Tushar";
		objArray [1] [1]="Garave";
		objArray [1] [2] = 9886644823L;
		
		objArray [2] [0]="Durga";
		objArray [2] [1]="Prasad";
		objArray [2] [2] = 9886644824L;
		
		return objArray;
	}
	
}
