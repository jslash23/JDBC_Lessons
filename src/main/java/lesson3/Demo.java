package lesson3;

public class Demo {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();
        SolutionOLd solutionOLd = new SolutionOLd();
        Solution solution = new Solution();
        Product product = new Product(11, "testJKHH", "test description JHJHJHJH", 100);
        Product product2 = new Product(10, "test_new", "some data description", 250);

        //productDAO.save(product);
        //System.out.println(productDAO.getProduct());


        //productDAO.update(product2);//обновляем элемент который есть в базе
        //сам элемент находим по id, если элемента нет то его не обновляем


        // тут нам нужно получить из бд поле с ай ди таким же как ай ди в объекте produkt
        //Удаляем элемент который есть в базе, элемент так же находим по id
        //productDAO.delete(10); //если по такому id в базе нет элементов то ничего не делаем

        //System.out.println(solutionOLd.findProductsByPrice());
        //System.out.println(solutionOLd.findProductsWithEmptyDescription());

      //solution.createTable();
   solution.testSavePerformance();
        //solution.testSavePerformanceOld();
       //solution.createSequense();//java.sql.SQLSyntaxErrorException: ORA-00955: имя уже задействовано для существующего объекта
       // solution.createTriggers();//java.sql.SQLException: В индексе отсутствует параметр IN или OUT:: 1

     //solution.testDeleteByIdPerformance();
        //solution.testDeletePerformance();

       //solution.dropTable();
        //solution.dropSequence();
      //solution.dropTrigger();

        //solution.testSelectPerformance();

       // solution.testSelectByIdPerformance();

        // Я несколько дней воюю с триггером в SQL запрос работает а в Идее не хочет, кидает ошибку.
    }
}



