package lesson3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {

    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";//1
    private static final String DB_URL = "jdbc:oracle:thin:@sl22-database1.cshzc28zzyct.us-east-2.rds.amazonaws.com:1521:ORCL";


    private static final String USER = "main";
    private static final String PASS = "db123slash22";


    private static final String queryCreateTable = "CREATE TABLE TEST_SPEED" +
            "(ID NUMBER NOT NULL," +
            "SOME_STRING NVARCHAR2(1000) NOT NULL," +
            "SOME_NUMBER NUMBER(7,0) NOT NULL," +
            " PRIMARY KEY(ID))";

    private static final String querySelectById = "SELECT * FROM TEST_SPEED WHERE ID = ?";

    private static final String queryDeleteById = " DELETE  FROM TEST_SPEED WHERE ID = ? ";

    private static final String queryTestSavePerf = "INSERT INTO TEST_SPEED  VALUES (?, ?, ?)";

    private static final String queryDropTable = "DROP TABLE TEST_SPEED";

    private static final String queryDeletePerf = "TRUNCATE TABLE TEST_SPEED";
    private static final String querySelectAllPerf = "SELECT * FROM  TEST_SPEED";

    /*
    testSavePerformance()    Total execution time: 150115ms
    testSelectById           Total execution time: 146508ms
    testSelectPerformance()  Total execution time: 2759ms
    testDeleteByIdPerformance()  Total execution time: 146539ms
    testDeletePerformance()  Total execution time: 2529ms
     */


    public void createTable() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(queryCreateTable)) {

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

            System.out.println("Table was created with result ");

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }


    public void dropTable() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(queryDropTable)) {

            int res = preparedStatement.executeUpdate();

            System.out.println("Drop was finished with result " + res);

        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }


    public void testSavePerformance() {

        //Добавить 1000 записей в таблицу TEST_SPEED  с произвольными значением

        //1.для генерации 1000 запросов нам нужно сделать цикл с итерацие на 1 до 10001 в котором мы вызываем наш запрос

        //2. сделаем preparedStatement с сохранением параметров в поля таблицы TEST_SPEED в запросе используем знаки вопроса
        //каждый знак вопроса это отдельная колонка.
        //3. В каждую колонку добавляем отдельный метод генерации произвольного числа или текста
        //для ай ди используем автоинкрементирование на 1
        //для генерации текста в колонке SOME_STRING используем метод произвольной генерации текста
        //для генерации цены в колонке SOME_NUMBER используем метод произвольной генерации чисел от 1 до 1000


        long startTime = System.currentTimeMillis();

        try (Connection connection = getConnection()) {
            ArrayList<TestSpeed> ts = new ArrayList<>();

            for (int i = 1; i <= 1000; i++) {
                ts.add(new TestSpeed(i, getNewString(20), getNewNumber()));
            }

            PreparedStatement preparedStatement = connection.prepareStatement(queryTestSavePerf);
            for (TestSpeed testSpeed : ts) {
                preparedStatement.setLong(1, testSpeed.getId());
                preparedStatement.setString(2, testSpeed.getSomeString());
                preparedStatement.setInt(3, testSpeed.getSomeNumber());


                preparedStatement.executeUpdate();
            }
            long endTime = System.currentTimeMillis();

            System.out.println("Total execution time: " + (endTime - startTime) + "ms");

        } catch (SQLException e) {
            System.err.println("Something went wrong");

            e.printStackTrace();
        }

    }


    public void testDeleteByIdPerformance() {

        long startTime = System.currentTimeMillis();

        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(queryDeleteById)) {

            for (int i = 1; i < 1001; i++) {

                preparedStatement.setLong(1, i);
                preparedStatement.executeUpdate();
                System.out.println(i);
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong");

            e.printStackTrace();
        }


        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) + "ms");

    }

    private Integer getNewNumber() {
        return (int) (Math.random() * 1001);
    }

    private String getNewString(int n) {

        // Java-программа генерирует случайную буквенно-цифровую строку
// используя метод Math.random ()

        // функция для генерации случайной строки длиной n

        {
            // выбор случайного символа  из этой строки

            String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                    + "0123456789"
                    + "abcdefghijklmnopqrstuvxyz";

            // создаем StringBuffer размером AlphaNumericString

            StringBuilder sb = new StringBuilder(n);

            for (int i = 0; i < n; i++) {

                // генерируем случайное число между
                // 0 переменной длины AlphaNumericString

                int index
                        = (int) (AlphaNumericString.length()
                        * Math.random());

                // добавляем символ один за другим в конец sb

                sb.append(AlphaNumericString
                        .charAt(index));

            }

            return sb.toString();
        }
    }

    public void testDeletePerformance() {
        long startTime = System.currentTimeMillis();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(queryDeletePerf)) {

            int res = preparedStatement.executeUpdate();

            System.out.println("Delete was finished with result " + res);
            long endTime = System.currentTimeMillis();
            System.out.println("Total execution time: " + (endTime - startTime) + "ms");
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }

    public void testSelectPerformance() {
        long startTime = System.currentTimeMillis();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(querySelectAllPerf)) {

            preparedStatement.executeUpdate();

            long endTime = System.currentTimeMillis();
            System.out.println("Total execution time: " + (endTime - startTime) + "ms");
        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }
    }


    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);

    }

    public void testSelectByIdPerformance() {
        long startTime = System.currentTimeMillis();
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(querySelectById)) {

            for (int i = 1; i < 1001; i++) {

                preparedStatement.setLong(1, i);
                preparedStatement.executeUpdate();
                System.out.println(i);
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong");

            e.printStackTrace();
        }


        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) + "ms");

    }
}


