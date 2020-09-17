package lesson3;

public class Demo {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();
        Solution solution = new Solution();
        PreparedQuery preparedQuery = new PreparedQuery();
        Product product = new Product(11, "testJKHH", "test description JHJHJHJH", 100);
        Product product2 = new Product(10, "test_new", "some data description", 250);

        //productDAO.save(product);
        //System.out.println(productDAO.getProduct());


        //productDAO.update(product2);//обновляем элемент который есть в базе
        //сам элемент находим по id, если элемента нет то его не обновляем


        // тут нам нужно получить из бд поле с ай ди таким же как ай ди в объекте produkt
        //Удаляем элемент который есть в базе, элемент так же находим по id
        //productDAO.delete(10); //если по такому id в базе нет элементов то ничего не делаем

        //System.out.println(solution.findProductsByPrice());
        //System.out.println(solution.findProductsWithEmptyDescription());

      //preparedQuery.createTable();
     // preparedQuery.testSavePerformance();
       // preparedQuery.createSequense();//java.sql.SQLSyntaxErrorException: ORA-00955: имя уже задействовано для существующего объекта
        //preparedQuery.createTriggers();//java.sql.SQLException: В индексе отсутствует параметр IN или OUT:: 1

      preparedQuery.testDeleteByIdPerformance();
        //preparedQuery.testDeletePerformance();

       //preparedQuery.dropTable();
        //preparedQuery.dropSequence();
       // preparedQuery.dropTrigger();

        //  System.out.println(preparedQuery.testSelectPerformance());

        //preparedQuery.testSelectByIdPerformance();

        // Я несколько дней воюю с триггером в SQL запрос работает а в Идее не хочет, кидает ошибку.
    }
}



