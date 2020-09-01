package lesson3;

public class Demo {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();
        Product product = new Product(11,"testJKHH", "test description JHJHJHJH",100);
        Product product2 = new Product(10,"test_new", "some data description",250);
       // productDAO.save(product);
        //System.out.println(productDAO.getProduct());


        productDAO.update(product2);//обновляем элемент который есть в базе
        //сам элемент находим по id, если элемента нет то его не обновляем

       // тут нам нужно получить из бд поле с ай ди таким же как ай ди в объекте produkt

        //Удаляем элемент который есть в базе, элемент так же находим по id
        //productDAO.delete(10) //если по такому id в базе нет элементов то ничего не делаем
    }
}
