package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext1 {

    private final String SERVER = "127.0.0.1";
    private final String PORT = "3306";
    private final String DATABASE = "school";

    private final String USER = "root";
    private final String PASSWORD = "Vietnam@123";

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url =
                    "jdbc:mysql://" + SERVER + ":" + PORT + "/" + DATABASE
                    + "?useSSL=false"
                    + "&allowPublicKeyRetrieval=true"
                    + "&serverTimezone=Asia/Ho_Chi_Minh"
                    + "&characterEncoding=UTF-8";

            return DriverManager.getConnection(
                    url,
                    USER,
                    PASSWORD
            );

        } catch (ClassNotFoundException e) {
            throw new SQLException(
                    "Không tìm thấy MySQL JDBC Driver.",
                    e
            );
        }
    }
}