package lesson1and2;

import java.sql.*;
import java.util.ArrayList;

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


            // saveProduct(statement);
             //deleteProducts(statement);
             //deleteProductByPrice(statement);



            //System.out.println(getAllProducts(statement));
            //System.out.println(getProductsByPrice(statement));

            //System.out.println(getProductsByDescription(statement));

            //increasePrice(statement);
            changeDescription(statement);


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

    //Написать список всех продуктов с таблицы PRODUCT
    private static ArrayList<Order> getAllProducts(Statement statement) throws Exception {
        ArrayList<Order> list =  new ArrayList<>();
        try (ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCTJD")) {
            while (resultSet.next()) {//пробегаемся по всем записам в таблице
              list.add(mappingData(resultSet));
            }
        }
        return list;
    }

    private static ArrayList<Order>  getProductsByPrice(Statement statement) throws Exception {
        ArrayList<Order> list =  new ArrayList<>();
        try (ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCTJD where PRICE <= 100")) {
            while (resultSet.next()) {//пробегаемся по всем записам в таблице
                list.add(mappingData(resultSet));
            }
        }
        return list;
    }

    private static ArrayList<Order> getProductsByDescription(Statement statement) throws Exception {
        ArrayList<Order> list =  new ArrayList<>();
        try (ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCTJD where LENGTH (to_char (DESCRIPTION)) > 50")) {
            while (resultSet.next()) {//пробегаемся по всем записам в таблице
                list.add(mappingData(resultSet));
            }
        }
        return list;
    }

    private static Order mappingData(ResultSet resultSet) throws Exception {
        long id = resultSet.getLong(1);//маппинг первой колонки таблицы Orders_old
        String productName = resultSet.getString(2);//маппинг второй колонки
        String description = resultSet.getString(3);
        int price = resultSet.getInt(4);//маппинг третьей колонки

        //Загрузим все содержимое таблицы в джавовый объект order
        Order order = new Order(id, productName, description, price);
       return order;
    }



    //метод увеличивает значение цены на 100 во всех продуктах, где цена < 970
    private static void increasePrice(Statement statement) throws Exception{
        int response = statement.executeUpdate
                ("UPDATE PRODUCTJD SET PRICE = PRICE + 100 where PRICE < 970");
        System.out.println(response);
    }

    //Удаляет последнее предложение с описанием всех продуктов с длиной описания > 100
    private static void changeDescription(Statement statement) throws Exception {
        int response = statement.executeUpdate
                ("SELECT length (DESCRIPTION)  FROM PRODUCTJD  where LENGTH (to_char (DESCRIPTION)) > 100");
        System.out.println(response);
    }


}
