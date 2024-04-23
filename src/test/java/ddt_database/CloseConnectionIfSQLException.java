package ddt_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class CloseConnectionIfSQLException {

	public static void main(String[] args) throws SQLException {
		/*Whenever we try to access the database, the connection will be established, 
 after the complete execution of the program we need to close that connection, 
 if there's any syntax error in writing the SQL query and the exceptions will 
 inturrupt the execution and the connection will never close which will cause
 the object creation each time the program is executed. This can lead to database 
 overload to avoid that we need to hadle the exception and close the connection
 by using try-catch-finally blocks as shown below
		 */
		Connection conn=null;	
		try {
			//	step-1: establish a driver connection
			Driver driverRef=new Driver();
			DriverManager.registerDriver(driverRef);

			// step-2: connect to database
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_projects", "root", "root");
			System.out.println("=====Done=====");
			// step-3: create sql statement

			Statement statement = conn.createStatement();

			// step-4 execute sql statement to get the resultSet()

			ResultSet resultSet = statement.executeQuery("select * from projects");

			while(resultSet.next()) {
				System.out.println(resultSet.getString(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getString(3)+"\t"+resultSet.getString(4)+"\t"+resultSet.getString(5)+"\t"+resultSet.getString(6));
			}
		}catch (Exception e){
			
			System.out.println("handle the exception");
		}
		// step-5: close the connection
		finally {
			conn.close();
			System.out.println("=========close the connection========");
		}
	}

}
