package lesson5;

import org.hibernate.Session;

public class Demo {
    public static void main(String[] args) {


//transaction for session

      Product product = new Product();
      product.setId(505);
      product.setName("dogNew");
      product.setDescription("kolly");
      product.setPrice(200);


      Product product2 = new Product();
      product2.setId(505);
      product2.setName("cat");
      product2.setDescription("pers");
      product2.setPrice(150);

      long id = product.getId();


      System.out.println(ProductRepositiry.save(product));//+
      ProductRepositiry.delete(id);
      System.out.println(ProductRepositiry.update(product2));

    }
}
