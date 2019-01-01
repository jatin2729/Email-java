import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class sqliteConnection {
	Connection conn = null;

	public static Connection dbConnector() {
		try {
			/**
			 * 
			 * forname is method in the class namned Class
			 */
			Class.forName("org.sqlite.JDBC");// loading driver from library (mysql connector driver)
			/**
			 * get connection is a method in
			 */
			// D:\CS313\Email_final_370\database
			Connection conn = DriverManager
					.getConnection("jdbc:sqlite:D:\\CS313\\Email_final_370\\database\\emails.db");
			// JOptionPane.showMessageDialog(null, "connection successful");
			return conn;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
