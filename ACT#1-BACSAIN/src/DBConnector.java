import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    public final Connection conn;

    public DBConnector() {
        String url = getRequiredEnv("DB_URL");
        String user = getRequiredEnv("DB_USER");
        String pass = getRequiredEnv("DB_PASSWORD");

        try {
            // MySQL driver is discovered at runtime when present on the classpath.
            this.conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to database. Check DB_URL/DB_USER/DB_PASSWORD.", e);
        }
    }

    private static String getRequiredEnv(String key) {
        String v = System.getenv(key);
        if (v == null || v.isBlank()) {
            throw new IllegalStateException("Missing required environment variable: " + key);
        }
        return v;
    }
}
