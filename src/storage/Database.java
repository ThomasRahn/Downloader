package storage;

import java.sql.Connection;

public abstract class Database {
	public Connection connection = null;
	
	public static Database getInstance() {
		return null;
	}

}
