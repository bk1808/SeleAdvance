package ddt_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class ExecuteSelectQueryTest {

	public static void main(String[] args) throws SQLException {
		// Program to 'Execute Select query' to read the data from database execution - Follow the steps
//		step-1: load/ register the databse driver
		Driver driverRef=new Driver();
		DriverManager.registerDriver(driverRef);
// 		step-2: connect to database
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_projects", "root", "root");
//		step-3: create sql statement
		Statement stat = conn.createStatement();
//		step-4: execute select query and get the result
		ResultSet resultSet = stat.executeQuery("select * from projects");
		while(resultSet.next()) {
			System.out.println(resultSet.getString(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getString(3)+"\t"+resultSet.getString(4)+"\t"+resultSet.getString(5)+"\t"+resultSet.getString(6));
		}
		
//		step-5: close the connection
		conn.close();
	}

}
