package main;

import storage.Database;
import storage.SQLite;

public class Config {

	public static Database getDatabase() {
		return SQLite.getInstance();
	}
}
