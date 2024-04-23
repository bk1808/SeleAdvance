package ddt_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Assert;
import org.junit.Test;

import com.mysql.cj.jdbc.Driver;

public class UnitTestingToCheckExptectedDataInDB {

	@Test
	public void checkExpectedData() throws SQLException {
		String expectedID="TP_008";
		boolean flag=false;
		Driver driverRef=new Driver();
		DriverManager.registerDriver(driverRef);
// 		step-2: connect to database
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_projects", "root", "root");
		System.out.println("=====Connection Established======");
		//		step-3: create sql statement
		Statement stat = conn.createStatement();
//		step-4: execute select query and get the result
		ResultSet resultSet = stat.executeQuery("select * from projects");
		while(resultSet.next()) {
			String projID=resultSet.getString(1);
			if(expectedID.equals(projID)) {
				flag=true;
				System.out.println(expectedID+" is present==PASS");
			}
		}
		if(flag==false) {
			System.out.println(expectedID+" is not present==FAIL");
			Assert.fail();
//		step-5: close the connection
			conn.close();
		}
	}
	
}
