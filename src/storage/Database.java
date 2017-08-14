package storage;

import java.sql.Connection;

import relationModel.ActiveRecord;

public abstract class Database {
	public Connection connection = null;
	
	public static Database getInstance() {
		return null;
	}

	public abstract void createStructure(ActiveRecord record);
	
	public abstract void save(ActiveRecord record);
}
