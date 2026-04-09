package hospital.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class conn {
    Connection connection;
    Statement statement;

    public conn() {
        try {
            // Load MySQL driver (optional in new versions, but safe to include)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database connection
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hospital_management_system",
                    "root",
                    "lingkon+biswas@"
            );

            statement = connection.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
