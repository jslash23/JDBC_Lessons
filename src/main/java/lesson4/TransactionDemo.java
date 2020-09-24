package lesson4;

import lesson3.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDemo {


    //Atomicity (Атомарность) если один шаг из нашей транзакции не проходит то мы отменяем все
    //чтоб не было логичессских непоняток
    //Атомарность это всё или ничего

    //Consistency  (Целостность данных) Все данные в Б.Д. следуют определенным правилам которые общие для всех
    //Например длина полей ограничена для всех продуктов заданной длиной полей в классе этих объектов

    //1. save order -  pay money - receive money
    //2.save order - pay money - receive money
    //Изоляция означает что транзакции не могут получать промежуточных результатов с другой транзакции
    //Пока полностью транзакция не завершится все другие транзакции не имеют доступа к шагам транзакции
    //(save order, pay money, reseive money
    //)

    //Durability - если транзакция завершилась успешно то даннные должны быть сохранены в Б.Д.
    //Если не успешно, код упол где то в одном из шагов транзакции то данные не сохраняются в Б.Д.


    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";//1
    private static final String DB_URL = "jdbc:oracle:thin:@sl22-database1.cshzc28zzyct.us-east-2.rds.amazonaws.com:1521:ORCL";

    //
    private static final String USER = "main";
    private static final String PASS = "db123slash22";

    private static final String updateName = "UPDATE PRODUCTJD set name = ? WHERE ID = ?";
    private static final String updateDescription = "UPDATE PRODUCTJD set description = ? WHERE ID = ?";
    private static final String updatePrice = "UPDATE PRODUCTJD set price = ? WHERE ID = ?";
    private static final String deleteProducts = "DELETE FROM PRODUCTJD WHERE ID = ?";

    public static void main(String[] args) {
        Product product1 = new Product(55, "11111", "test description 1", 555);
        Product product2 = new Product(75, "11111", " description 2", 478);
        Product product3 = new Product(85, "11111", " description 3", 478 );
        ArrayList<Product> products =  new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        save(products);
    }

    //Сделаем сохранение списка продуктов в рамках одной транзакции
    //Если сохранение фейлится то мы отменяем всё и выбрасиваем Exeption
    public static void save(List<Product> products) {
        try (Connection connection = getConnection()) {
            saveList(products, connection);

        } catch (SQLException e) {
            System.err.println("Something went wrong");

            e.printStackTrace();
        }
    }

    private static void saveList(List<Product> products, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PRODUCTJD  VALUES (?, ?, ?, ?)")) {

            connection.setAutoCommit(false);//управление окончанием транзакции вручную

            for (Product product : products) {
                preparedStatement.setLong(1, product.getId());
                preparedStatement.setString(3, product.getDescription());
                preparedStatement.setString(2, product.getName());
                preparedStatement.setInt(4, product.getPrice());

                int res = preparedStatement.executeUpdate();

                System.out.println("Save was finished with result " + res);
            }
            connection.commit();//запись данный в Б.Д.
        } catch (SQLException e) {
            connection.rollback();
            //если rollback свалится то мы делаем несколько ретраев или ничего не делаем
            throw e; //перебрасываем лог об ошибке во внешний метод saveList

        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
