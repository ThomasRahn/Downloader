package tests;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import relationModel.ActiveRecord;

public class ActiveRecordTest {
	
	
	private Connection connection;
	private Object lite;
	
	@Before
	public void setUp() {
		
		// load the sqlite-JDBC driver using the current class loader
	    try {
			Class.forName("org.sqlite.JDBC");
			
			// create a database connection
		     connection = DriverManager.getConnection("jdbc:sqlite:test.db");
		     
		     Class<?> sqlite = Class.forName("storage.SQLite");
		     
		     Field conn = lite.getClass().getField("connection");
		     conn.set(lite, connection);
		     
		     
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
	public void testCanCreateActiveRecordTable() throws Exception {
		
		//Actual test
		Class<?> ar = Class.forName("relationModel.ActiveRecord");
		Object cc = ar.getDeclaredConstructor(String.class).newInstance("test");

		Field db = cc.getClass().getDeclaredField("db");
//		db.set(cc, lite);
//		
//		((ActiveRecord) cc).registerField("id", 1, "PRIMARY KEY");
//		((ActiveRecord) cc).create_structure();
		
		
		
	}
	
}
