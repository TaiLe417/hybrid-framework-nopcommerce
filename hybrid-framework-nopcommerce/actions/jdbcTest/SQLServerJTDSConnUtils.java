package jdbcTest;

import java.sql.*;

public class SQLServerJTDSConnUtils {
    public static Connection getSQLServerConnection() {
        String hostName = "localhost";
        String sqlInstanceName = "SQLEXPRESS";
        String database = "automationtest";
        String userName = "sa";
        String password = "taile47711";

        return getSQLServerConnection(hostName, sqlInstanceName, database, userName, password);
    }

    public static Connection getSQLServerConnection(String hostName, String sqlInstanceName, String database, String userName, String password) {
        Connection conn = null;
        try {
            // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Cấu trúc URL Connection dành cho SQL Server
            String connectionURL = "jdbc:sqlserver://" + hostName + ":1433" + ";instance=" + sqlInstanceName + ";databaseName=" + database;
            conn = DriverManager.getConnection(connectionURL, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
