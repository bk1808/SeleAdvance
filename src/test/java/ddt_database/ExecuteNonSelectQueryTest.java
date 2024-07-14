package ddt_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class ExecuteNonSelectQueryTest {

	public static void main(String[] args) throws SQLException {

		// Program to 'Execute No Select query' write or insert the data into database execution - Follow the steps
		//		step-1: load/ register the databse driver
		Driver driverRef=new Driver(); 
		DriverManager.registerDriver(driverRef);
		// 		step-2: connect to database
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_projects", "root", "root");
		//		step-3: create sql statement
		Statement stat = conn.createStatement();
		//		step-4: execute select query and get the result

		int result = stat.executeUpdate("insert into projects values('TP_004','preethi','14/04/2024','Tek Pyramid','on going','90')");
		System.out.println(result);

		//		step-5: close the connection
		conn.close(); 
	}

}