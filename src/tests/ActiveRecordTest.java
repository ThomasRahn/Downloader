package tests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import relationModel.ActiveRecord;

public class ActiveRecordTest {
	private Connection connection;
	
	@Before
	public void setUp() {
		// load the sqlite-JDBC driver using the current class loader
	    try {
			Class.forName("org.sqlite.JDBC");
			
			// create a database connection
		     connection = DriverManager.getConnection("jdbc:sqlite:test.db");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	@After
	public void tearDown() {
		try {
			connection.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCanCreateActiveRecord() {
		ActiveRecord record = new ActiveRecord("table");
		
		assertTrue(record instanceof ActiveRecord);
	}
	
	@Test
	public void testCanCreateActiveRecordTable() throws SQLException {
		
		//Actual test
		ActiveRecord record = new ActiveRecord("test");
		record.registerField("id", 1, "PRIMARY KEY");
		record.create_structure();
		
		
		//assertion
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM test");
		
		System.out.println(rs);
		
		
	}
	
}
