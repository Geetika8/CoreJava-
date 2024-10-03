import java.sql.*;
public class InsertQueryTest {
    // Database URL, username, and password (change these based on your DB configuration)
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/demo"; // Change 'db_name'
    private static final String USER = "student"; // Change 'root' to your DB username
    private static final String PASSWORD = "admin"; // Change 'password' to your DB password
    public static void main(String[] args){
        // Test the JDBC connection
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            // Step 1: Load the JDBC driver (optional in modern Java)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish a connection
            connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            System.out.println("Connection established successfully!");

            // Step 3: Create a statement object
            statement = connection.createStatement();

            // Step 4: Execute a SQL query
            int rowsAffected = statement.executeUpdate(
                    "insert into employees " +
                            "(last_name, first_name, email, department, salary) " +
                            "values " +
                            "('Wright', 'Eric', 'eric.wright@foo.com', 'HR', 33000.00)");

            // 4. Verify this by getting a list of employees
            resultSet = statement.executeQuery("select * from employees order by last_name");


            // Step 5: Process the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String first_name = resultSet.getString("first_name");
                String email = resultSet.getString("email");

                System.out.println("ID: " + id + ", First Name: " + first_name + ", Email: " + email);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Step 6: Close the resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
                System.out.println("Connection closed.");
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}
