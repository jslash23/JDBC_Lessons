package lesson3;

import lesson1and2.OrderOld;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
//CRUD
    //create, read, update, delete

    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";//1
    private static final String DB_URL = "jdbc:oracle:thin:@sl22-database1.cshzc28zzyct.us-east-2.rds.amazonaws.com:1521:ORCL";

    //
    private static final String USER = "main";
    private static final String PASS = "db123slash22";

    private static final String updateName = "UPDATE PRODUCTJD set name = ? WHERE ID = ?";
    private static final String updateDescription = "UPDATE PRODUCTJD set description = ? WHERE ID = ?";
    private static final String updatePrice =  "UPDATE PRODUCTJD set price = ? WHERE ID = ?";
    private static final String deleteProducts = "DELETE FROM PRODUCTJD WHERE ID = ?";

    public Product save(Product product) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PRODUCTJD  VALUES (?, ?, ?, ?)")) {

            preparedStatement.setLong(1, product.getId());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setString(2, product.getName());

            preparedStatement.setInt(4, product.getPrice());

            //1.DB Driver совместимость джавы с базой данніх которую мы используем +
            //2.Create connection  создаем соединение, используем его и закрываем его иначе если все будут +
            // использовать постоянные конекшены потушат базу данных и сервер
            //3. create query создать запрос
            //4. execute query выполнить запрос
            //5.Work with result
            //6.close all connection

//Метод executeQuery не подходит потому что он не возвращает данные
            //метод execute возвращает true если мы получили какие то данные в ответ, false если не получили данные в ответ
            int res = preparedStatement.executeUpdate();

            System.out.println("Save was finished with result " + res);

        } catch (SQLException e) {
            System.err.println("Something went wrong");

            e.printStackTrace();
        }
        return product;
    }



    public Product update(Product product){
        updateName(product);
        updateDescription(product);
        updatePrice(product);
        return product;
    }

    private void updateName(Product product) {

        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE PRODUCTJD set name = ? WHERE ID = ?")) {

            preparedStatement.setString(1, product.getName());
            preparedStatement.setLong(2, product.getId());

            preparedStatement.setString(1, product.getDescription());
            preparedStatement.setLong(2, product.getId());


            int res = preparedStatement.executeUpdate();

            System.out.println("Save was finished with result " + res);
        }
        catch (SQLException e) {
            System.err.println("Something went wrong");

            e.printStackTrace();
        }
    }


    private void updateDescription(Product product) {

        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE PRODUCTJD set description = ? WHERE ID = ?")) {
            preparedStatement.setString(1, product.getDescription());
            preparedStatement.setLong(2, product.getId());


            int res = preparedStatement.executeUpdate();
            System.out.println("Save was finished with result " + res);

        }
        catch (SQLException e) {
            System.err.println("Something went wrong");

            e.printStackTrace();
        }
    }

    private void updatePrice(Product product) {

        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE PRODUCTJD set price = ? WHERE ID = ?")) {
            preparedStatement.setInt(1, product.getPrice());
            preparedStatement.setLong(2, product.getId());


            int res = preparedStatement.executeUpdate();
            System.out.println("Save was finished with result " + res);

        }
        catch (SQLException e) {
            System.err.println("Something went wrong");

            e.printStackTrace();
        }
    }

    public List<Product> getProduct() {
        // 1 Методы между собой не делят никак Connection
        // На каждый запрос свой Connection

        //2. На каждый метод свой Statement

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ;
            ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCTJD");

            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4));
                products.add(product);
            }
            return products;


        } catch (SQLException e) {
            System.err.println("Something went wrong");

            e.printStackTrace();
        }
        return null;
    }



    public void delete(long id) {

        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM PRODUCTJD WHERE ID = ?")) {


            preparedStatement.setLong(1, id);


            int res = preparedStatement.executeUpdate();
            System.out.println("Save was finished with result " + res);

        }
        catch (SQLException e) {
            System.err.println("Something went wrong");

            e.printStackTrace();
        }
    }




    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
