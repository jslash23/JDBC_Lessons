package lesson3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SolutionOLd {

    //CRUD
    //create, read, update, delete

    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";//1
    private static final String DB_URL = "jdbc:oracle:thin:@sl22-database1.cshzc28zzyct.us-east-2.rds.amazonaws.com:1521:ORCL";

    //
    private static final String USER = "main";
    private static final String PASS = "db123slash22";

    private static final String queryFindProductsByPryce = "SELECT * FROM PRODUCTJD  WHERE PRICE > 50 AND PRICE <= 150";

    private static final String queryFindProductsByName = "SELECT * FROM PRODUCTJD WHERE  NAME LIKE ?";

    private static final String queryFindProductsWithEmptyDescr = "SELECT * FROM PRODUCTJD WHERE  DESCRIPTION IS NULL";

    public List<Product> findProductsByPrice() {
        // 1 Методы между собой не делят никак Connection
        // На каждый запрос свой Connection

        //2. На каждый метод свой Statement

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(queryFindProductsByPryce)) {

            ResultSet resultSet = preparedStatement.executeQuery();

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

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);

    }

    public List<Product> findProductsByName(String word) {
        // 1 Методы между собой не делят никак Connection
        // На каждый запрос свой Connection

        //2. На каждый метод свой Statement

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(queryFindProductsByName)) {
            preparedStatement.setString(1,word);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4));

                int value = product.getName().trim().length();
                String[] data = new String[value];
                data = product.getName().split(" ");
                if (data.length > 1){
                    break;
                }

                for (String val : data) {
                  if (val.matches("^[A-Z0-9]+$") && val.length() < 3){
                      break;
                    }
                }

                products.add(product);
            }
            return products;


        } catch (SQLException e) {
            System.err.println("Something went wrong");

            e.printStackTrace();
        }
        return null;
    }

    public List<Product> findProductsWithEmptyDescription(){
        // 1 Методы между собой не делят никак Connection
        // На каждый запрос свой Connection

        //2. На каждый метод свой Statement

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(queryFindProductsWithEmptyDescr)) {

            ResultSet resultSet = preparedStatement.executeQuery();

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


}


