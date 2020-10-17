package lesson5;

import org.hibernate.Session;

public class Demo {
    public static void main(String[] args) {


//transaction for session

      Product product = new Product();
      product.setId(503);
      product.setName("dog");
      product.setDescription("pes");
      product.setPrice(150);


      Product product2 = new Product();
      product2.setId(500);
      product2.setName("car");
      product2.setDescription("bmv");
      product2.setPrice(15000);

      long id = product.getId();


      //System.out.println(ProductRepositiry.save(product));//+
      ProductRepositiry.delete(id);
      //System.out.println(ProductRepositiry.update(product2));

    }
}
