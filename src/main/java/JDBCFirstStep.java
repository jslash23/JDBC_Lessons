import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;


public class JDBCFirstStep {

    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@sl22-database1.cshzc28zzyct.us-east-2.rds.amazonaws.com:1521:ORCL";

    //
    private static final String USER = "main";
    private static final String PASS = "db123slash22";

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {

            //1.DB Driver совместимость джавы с базой данніх которую мы используем +
            //2.Create connection  создаем соединение, используем его и закрываем его иначе если все будут +
            // использовать постоянные конекшены потушат базу данных и сервер
            //3. create query создать запрос
            //4. execute query выполнить запрос
            //5.Work with result
            //6.close all connection
            try {
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException e) {
                System.out.println("Class" + JDBC_DRIVER + "not found");
                return;
            }

            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM Test")) {
                while (resultSet.next()) {
                    System.out.println("Object found");
                }
            }
        } catch (SQLException e) {
            System.err.println("Something wrong");
            e.printStackTrace();
        }
    }
}
