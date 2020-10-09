package lesson4.hw1;


import java.sql.*;
import java.util.ArrayList;

public class StorageDAO {

    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";//1
    private static final String DB_URL = "jdbc:oracle:thin:@sl22-database1.cshzc28zzyct.us-east-2.rds.amazonaws.com:1521:ORCL";

    //
    private static final String USER = "main";
    private static final String PASS = "db123slash22";

    private static final String updateName = "UPDATE STORAGE set name = ? WHERE ID = ?";
    private static final String updateDescription = "UPDATE STORAGE set description = ? WHERE ID = ?";
    private static final String updatePrice =  "UPDATE STORAGE set price = ? WHERE ID = ?";
    private static final String deleteProducts = "DELETE FROM STORAGE WHERE ID = ?";

    public  Storage save(Storage storage) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO STORAGE VALUES (?, ?, ?, ?)")) {

            preparedStatement.setLong(1, storage.getId());
            preparedStatement.setString(2, storage.getFormatsSupported());//нужно работать с String[]
            preparedStatement.setString(3, storage.getStorageCountry());
            preparedStatement.setLong(4, storage.getStorageMaxSize());

            int res = preparedStatement.executeUpdate();

            System.out.println("Save was finished with result " + res);

        } catch (SQLException e) {
            System.err.println("Something went wrong");

            e.printStackTrace();
        }
        return storage;
    }

    public ArrayList<Storage> findById(long id) throws SQLException {
        // 1 Методы между собой не делят никак Connection
        // На каждый запрос свой Connection

        //2. На каждый метод свой Statement
        ArrayList<Storage> list = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM STORAGE where id = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while ((resultSet.next())) {//пробегаемся по всем записам в таблице
                list.add(mappingDataStorage(resultSet));
            }
            return list;
        }

        catch (SQLException e) {
            System.err.println("Something went wrong");

            e.printStackTrace();
        }
        return list;
    }

    private static Storage mappingDataStorage(ResultSet resultSet) throws SQLException {
        long idn = resultSet.getLong(1);//маппинг первой колонки таблицы STORAGE
        String formatTypes = resultSet.getString(3);//маппинг второй колонки
        String country = resultSet.getString(4);//маппинг третьей колонки
        long sizeMax = resultSet.getLong(5);//маппинг четвертой колонки таблицы

       Storage storage = new Storage(idn, null, formatTypes, country,sizeMax);
        return  storage;
    }

//обновляем элемент который есть в базе
    //сам элемент находим по id, если элемента нет то его не обновляем

    public Storage update(Storage storage){
        updateStorage(storage);

        return storage;
    }

    private void updateStorage(Storage storage) {

        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE STORAGE SET FormatsSupported = ?, StorageCountry = ?, StorageMaxSize = ? WHERE ID = ?")) {

            preparedStatement.setString(1, storage.getFormatsSupported());
            preparedStatement.setString(2, storage.getStorageCountry());
            preparedStatement.setLong(3, storage.getStorageMaxSize());
            preparedStatement.setLong(4, storage.getId());

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

             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM STORAGE WHERE ID = ?")) {


            preparedStatement.setLong(1, id);


            int res = preparedStatement.executeUpdate();
            System.out.println("Delete was finished with result " + res);

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
