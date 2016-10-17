package dao.mysql.db;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPool {

	private static DataSource dataSource;

	static {
		try {
			Context context = new InitialContext();
			Context root = (Context) context.lookup("java:comp/env");
			dataSource = (DataSource) root.lookup("jdbc/autoservice_db");
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}

	public static java.sql.Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public static void closeConnection(Connection cn) {
		if (cn != null) {
			try {
				cn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
