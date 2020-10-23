package hibernate.Lesson2HW2;

import hibernate.lesson1.Product;

import java.util.Arrays;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();
        Product product = new Product();
        product.setName("table new!");
        product.setDescription("black&white");
        product.setPrice(150);


        Product product1 = new Product();
        product1.setName("car");
        product1.setDescription("dodje");
        product1.setPrice(1500);

        Product product2 = new Product();
        product2.setName("car");
        product2.setDescription("bmw");
        product2.setPrice(1200);

        Product product3 = new Product();
        product3.setName("car");
        product3.setDescription("lada");
        product3.setPrice(1000);


        Product product4 = new Product();
        product4.setId(35);
        product4.setName("car");
        product4.setDescription("lexus");
        product4.setPrice(15000);

        Product product5 = new Product();
        product5.setId(27);
        product5.setName("car");
        product5.setDescription("zaz");
        product5.setPrice(12000);

        Product product6 = new Product();
        product6.setId(37);
        product6.setName("car");
        product6.setDescription("porshe");
        product6.setPrice(10000);

        Product product7 = new Product();

        product7.setName("car");
        product7.setDescription("jeep");
        product7.setPrice(10000);


        List<Product> products = Arrays.asList(product, product, product3);
        List<Product> productsCars = Arrays.asList(product4, product5, product6);

        //System.out.println(productDAO.findById(5L));
       // System.out.println(productDAO.findByName("car"));
        //System.out.println(productDAO.findByContainedName("car"));
        //System.out.println(productDAO.findByPrice(150,30));
        //System.out.println(productDAO.findByNameSortedAsc("car"));
        //System.out.println(productDAO.findByNameSortedDesc("car"));
        System.out.println(productDAO.findByPriceSortedDesc(12000,10000));
    }
}
