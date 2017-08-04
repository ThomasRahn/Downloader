package file;

import java.sql.Connection;

/*
 * Downloadable interface for objects that can be downloaded and stored locally
 */
public interface Downloadable {
	public void download();
	public void store(Connection connection);
	public void create_structure(Connection connection);
	public void set_downloaded();
}
