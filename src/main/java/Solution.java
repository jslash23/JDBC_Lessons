import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Solution {
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@sl22-database1.cshzc28zzyct.us-east-2.rds.amazonaws.com:1521:ORCL";

    private static final String USER = "main";
    private static final String PASS = "db123slash22";

    public static void main(String[] args) throws Exception {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {

            //1.DB Driver совместимость джавы с базой данніх которую мы используем +
            //2.Create connection  создаем соединение, используем его и закрываем его иначе если все будут +
            // использовать постоянные конекшены потушат базу данных и сервер
            //3. create query создать запрос
            //4. execute query выполнить запрос
            //5.Work with result
            //6.close all connection


            saveProduct(statement);
            deleteProducts(statement);
            deleteProductByPrice(statement);

        } catch (SQLException e) {
            System.err.println("Something wrong");
            e.printStackTrace();
        }
    }


    //сохранение продукт в таблицу PRODUCT со значениями полей
    //по порядку 999,toy, for children, 60
    private static void saveProduct(Statement statement) throws Exception {
        int response = statement.executeUpdate /*("INSERT INTO PRODUCTJD  VALUES (999, 'toy', 'for children', 60)",*/
                ("INSERT INTO PRODUCTJD  VALUES (1000, 'cat', 'for children', 50)");
        System.out.println(response);
    }


    //удаление продуктов с таблицы  PRODUCT с именем не toy
    private static void deleteProducts(Statement statement) throws Exception {
        int response = statement.executeUpdate("DELETE FROM PRODUCTJD WHERE NAME <> 'toy'");
        System.out.println(response);
    }

    //удаление продуктов с таблицы PRODUCT с ценой меньше 100
    private static void deleteProductByPrice(Statement statement) throws Exception {
        int response = statement.executeUpdate("DELETE FROM PRODUCTJD WHERE PRICE < 100");
        System.out.println(response);
    }
}
