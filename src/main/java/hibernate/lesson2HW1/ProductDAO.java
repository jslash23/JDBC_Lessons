package hibernate.lesson2HW1;

import hibernate.lesson1.Product;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDAO {

    private static SessionFactory sessionFactory;

    public static Product save(Product product) {
        //create session/tr
        Session session = null;
        Transaction tr = null;

        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();

            //action
            session.save(product);

            //close session/tr
            session.getTransaction().commit();
            System.out.println("Done save");
            //тут  сессия закроется автоматичесски
            //session.close();
            return product;

        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());
            if (tr != null){
                tr.rollback();//
            }
        }

        finally {
            if (session != null)
                session.close();
        }
        return product;
    }

    public static void saveAll(List<Product> products)
    {
        //create session/tr
        Session session = null;
        Transaction tr = null;

        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();

            //action
            for (Product product : products){
                session.save(product);
            }


            //close session/tr
            session.getTransaction().commit();
            System.out.println("Done save");
            //тут  сессия закроется автоматичесски
            //session.close();

        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());
            if (tr != null){
                tr.rollback();//
            }
        }

        finally {
            if (session != null)
                session.close();
        }
    }


   public static SessionFactory createSessionFactory() {
        //singleton pattern нужен чтоб сесии не создавали множество раз
        //если сесион фактори нет то мы её инициализируем и возвращаем
       // если сесион фактори есть то создание не будет происходить а сразу вызовется готовый объект
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
   }


    public static void delete(long id)
    {
        //create session/tr
        Session session = null;
        Transaction tr = null;

        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            // Delete a persistent object
            Product product = new Product();
            product.setId(id);
            session.remove(product);


            tr.begin();

            //action

            //close session/tr
            session.getTransaction().commit();
            System.out.println("Delete done");
            //тут  сессия закроется автоматичесски

        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());
            if (tr != null){
                tr.rollback();//
            }
        }

        finally {
            if (session != null)
                session.close();
        }
    }

    public static void deleteAll(List<Product> products)
    {
        //create session/tr
        Session session = null;
        Transaction tr = null;

        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();

            //action

            tr.begin();
            String sql = "DELETE Product WHERE NAME = :name";
            for (Product product: products){
                Query query = session.createQuery(sql);
                query.setParameter("name",product.getName());
                query.executeUpdate();

            }


            //close session/tr
            session.getTransaction().commit();
            System.out.println("All delete done");
            //тут  сессия закроется автоматичесски
            //session.close();

        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());
            if (tr != null){
                tr.rollback();//
            }
        }

        finally {
            if (session != null)
                session.close();
        }
    }
    public static void updateAll(List<Product> products) {
        //create session/tr
        Session session = null;
        Transaction tr = null;

        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
            for (Product product : products ) {
                Query query = session.createQuery("UPDATE Product SET name = :nameProd"
                        + ", description = :descrProd"
                        + ", price = :priceProd"
                        + " WHERE id = :getId");

                query.setParameter("getId",product.getId());
                query.setParameter("nameProd",product.getName());
                query.setParameter("descrProd",product.getDescription());
                query.setParameter("priceProd",product.getPrice());
                int result = query.executeUpdate();
            }


            //close session/tr
            session.getTransaction().commit();

            System.out.println("Done save");

            //тут  сессия закроется автоматичесски
            //session.close();

        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());
            if (tr != null){
                tr.rollback();//
            }
        }

        finally {
            if (session != null)
                session.close();
        }
    }


    public static Product update(Product product) {
        //create session/tr
        Session session = null;
        Transaction tr = null;

        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();

            Query query = session.createQuery("UPDATE Product SET name = :nameProd"
                    + ", description = :descrProd"
                    + ", price = :priceProd"
                    + " WHERE id = :getId");

            query.setParameter("getId",product.getId());
            query.setParameter("nameProd",product.getName());
            query.setParameter("descrProd",product.getDescription());
            query.setParameter("priceProd",product.getPrice());
            int result = query.executeUpdate();

            //close session/tr
            session.getTransaction().commit();

            System.out.println("Done save");

            //тут  сессия закроется автоматичесски
            //session.close();
            return product;
        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());
            if (tr != null){
                tr.rollback();//
            }
        }

        finally {
            if (session != null)
                session.close();
        }
        return product;
    }
}
