package lesson4.hw1;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FileDAO {

    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";//1
    private static final String DB_URL = "jdbc:oracle:thin:@sl22-database1.cshzc28zzyct.us-east-2.rds.amazonaws.com:1521:ORCL";

    //
    private static final String USER = "main";
    private static final String PASS = "db123slash22";

    private static final String updateName = "UPDATE FILE_F set name = ? WHERE ID = ?";
    private static final String updateDescription = "UPDATE FILE_F set description = ? WHERE ID = ?";
    private static final String updatePrice =  "UPDATE FILE_F set price = ? WHERE ID = ?";
    private static final String deleteProducts = "DELETE FROM FILE_F WHERE ID = ?";

    public  File save(File file) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO FILE_F  VALUES (?, ?, ?, ?)")) {

            preparedStatement.setLong(1, file.getId());
            preparedStatement.setString(2, file.getName());
            preparedStatement.setString(3, file.getFormat());
            preparedStatement.setLong(4, file.getSize());

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
        return file;
    }

    public ArrayList<File> findById(long id) throws SQLException {
        // 1 Методы между собой не делят никак Connection
        // На каждый запрос свой Connection

        //2. На каждый метод свой Statement
        ArrayList<File> list = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM FILE_F where id = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while ((resultSet.next())) {//пробегаемся по всем записам в таблице
                list.add(mappingData(resultSet));
            }
            return list;
        }

         catch (SQLException e) {
            System.err.println("Something went wrong");

            e.printStackTrace();
        }
        return list;
    }

    private static File mappingData(ResultSet resultSet) throws SQLException {
        long idn = resultSet.getLong(1);//маппинг первой колонки таблицы FILE_N
        String name = resultSet.getString(2);//маппинг второй колонки
        String format = resultSet.getString(3);//маппинг третьей колонки
        long size = resultSet.getLong(4);//маппинг четвертой колонки таблицы

        File file = new File(idn, name, format, size);
        return  file;
    }



    public File update(File file){
        updateFile(file);
        return file;
    }

    private void updateFile(File file) {

        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE FILE_F set name_file = ?, format_file = ?, size_file = ?   WHERE ID = ?")) {

            preparedStatement.setString(1, file.getName());
            preparedStatement.setString(2, file.getFormat());
            preparedStatement.setLong(3, file.getSize());

            preparedStatement.setLong(4, file.getId());



            int res = preparedStatement.executeUpdate();

            System.out.println("Save was finished with result " + res);
        }
        catch (SQLException e) {
            System.err.println("Something went wrong");

            e.printStackTrace();
        }
    }

    public void delete(long id) {

        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM FILE_F WHERE ID = ?")) {


            preparedStatement.setLong(1, id);


            int res = preparedStatement.executeUpdate();
            System.out.println("Delete was finished with result " + res);

        }
        catch (SQLException e) {
            System.err.println("Something went wrong");

            e.printStackTrace();
        }
    }

    public List<File> getAllFiles() {
        // 1 Методы между собой не делят никак Connection
        // На каждый запрос свой Connection

        //2. На каждый метод свой Statement

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM FILE_F");

            List<File> files = new ArrayList<>();
            while (resultSet.next()) {
                File file = new File(resultSet.getLong(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getLong(4));
                files.add(file);
            }
            return files;


        } catch (SQLException e) {
            System.err.println("Something went wrong");

            e.printStackTrace();
        }
        return null;
    }




    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }


}
