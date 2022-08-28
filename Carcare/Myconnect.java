package Carcare;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnect {
	public static Connection getConnection() {
		try {
			Class.forName(com.mysql.jdbc.Driver.class.getName());
			return DriverManager.getConnection("jdbc:mysql://localhost/carcare", "root", "");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
