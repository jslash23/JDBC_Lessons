package lesson5;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProductRepositiry {

    public static Product save(Product product){
        Session session = new HibernateUtil().createSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(product);
        session.getTransaction().commit();
        System.out.println("Done save");
        session.close();
        return product;
    }

    public static void delete (long id){
        Transaction transaction = null;
        try(Session session =  HibernateUtil.getSessionFactory().openSession()){
            //start a transaction
            transaction = session.beginTransaction();

            // Delete a persistent object
            Product product3 = new Product();
            product3.setId(id);
            session.remove(product3);
            System.out.println("Product with id " + id + " was removed");
            transaction.commit();

        }
        catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public static Product  update(Product product){
        Session session = new HibernateUtil().createSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(product);
        session.getTransaction().commit();
        System.out.println("Done update");
        session.close();
        return product;
    }







}
