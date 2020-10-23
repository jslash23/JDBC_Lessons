package hibernate.Lesson2HW2;

import hibernate.lesson1.Product;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private static SessionFactory sessionFactory;


    /**
    поиск продукта по id
     */
    public static Product findById(Long id) {
        //create session/tr
        //Session session = null;
        Transaction tr = null;
        Product product = new Product();


        //session = createSessionFactory().openSession();
        try(Session session = createSessionFactory().openSession()){

            Query query = session.createQuery("from Product where id = :Id");
            tr = session.getTransaction();
            tr.begin();

            //action

            query.setParameter("Id",id);
            List list= query.list();


            //close session/tr
            session.getTransaction().commit();

            for (Object l : list) {
                product = (Product) l;
            }

            System.out.println("Select done ");
            return product;
            //тут  сессия закроется автоматичесски
            //session.close();


        } catch (HibernateException e) {
            System.err.println("Select is failed");
            System.err.println(e.getMessage());
            if (tr != null){
                tr.rollback();//
            }
        }
        catch (NullPointerException e){
            System.err.println("Nothing founded!");
        }


       return product;
    }

/**поиск продуктов по имени
 */
    public static List findByName(String name) {
        //Session session = null;
        Transaction tr = null;
        List listE = new ArrayList<Product>();

        try (Session session = createSessionFactory().openSession()) {
            Query query = session.createQuery("from Product  where name = :namePr");
            //session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
            //action

            query.setParameter("namePr",name);
            List res = query.list();

            //close session/tr
            session.getTransaction().commit();

            System.out.println("Select done ");
            return res;
            //session.close();

        } catch (HibernateException e) {
            System.err.println("Select is failed");
            System.err.println(e.getMessage());
            if (tr != null){
                tr.rollback();//
            }
        }
        catch (NullPointerException e){
            System.err.println("Nothing founded!");
        }
       /* finally {
            if (session != null)
                session.close();
        }*/
        return listE ;
    }


/**поиск продуктов, которые в своем имени содержать слово name

 */
  public List findByContainedName(String name){
      //Session session = null;
      Transaction tr = null;
      List listE = new ArrayList<Product>();

      try (Session session = createSessionFactory().openSession()) {
          Query query = session.createQuery("from Product c  where c.name in :nameProd");
          //session = createSessionFactory().openSession();
          tr = session.getTransaction();
          tr.begin();
          //action

          query.setParameter("nameProd",name);
          List res = query.list();

          //close session/tr
          session.getTransaction().commit();

          System.out.println("Select done ");
          return res;
          //session.close();

      } catch (HibernateException e) {
          System.err.println("Select is failed");
          System.err.println(e.getMessage());
          if (tr != null){
              tr.rollback();//
          }
      }
      catch (NullPointerException e){
          System.err.println("Nothing founded!");
      }
      return listE ;
  }

/**
поиск продуктов по вилке цен price+-delta включительно
 будет искать продукты с заданной ценной в диапазоне +=delta включительно.
 Например, если нужно найти продукты с ценой 100 и дельтой 10, то ищем все от 90 до 110
 */
   public List findByPrice(int price, int delta){
       //Session session = null;
       Transaction tr = null;
       List listE = new ArrayList<Product>();

       try (Session session = createSessionFactory().openSession()) {
           Query query = session.createQuery("from Product c  where c.price between :priceLow and :priceHigh");

           tr = session.getTransaction();
           tr.begin();
           //action

           query.setParameter("priceLow",price - delta);
           query.setParameter("priceHigh",price + delta);
           List res = query.list();

           //close session/tr
           session.getTransaction().commit();

           System.out.println("Select done ");
           return res;
           //session.close();

       } catch (HibernateException e) {
           System.err.println("Select is failed");
           System.err.println(e.getMessage());
           if (tr != null){
               tr.rollback();//
           }
       }
       catch (NullPointerException e){
           System.err.println("Nothing founded!");
       }
       return listE ;

    }

    /**
     поиск продуктов по имени, результат отсортирован по алфавитному порядку колонки name
     */

   public List  findByNameSortedAsc(String name){
       //Session session = null;
       Transaction tr = null;
       List listE = new ArrayList<Product>();

       try (Session session = createSessionFactory().openSession()) {

           Query query = session.createQuery(("from Product c where c.name in :nameProd order by c.name asc"));
           //session = createSessionFactory().openSession();
           tr = session.getTransaction();
           tr.begin();
           //action

           query.setParameter("nameProd",name);
           List res = query.list();

           //close session/tr
           session.getTransaction().commit();

           System.out.println("Select done ");
           return res;
           //session.close();

       } catch (HibernateException e) {
           System.err.println("Select is failed");
           System.err.println(e.getMessage());
           if (tr != null){
               tr.rollback();//
           }
       }
       catch (NullPointerException e){
           System.err.println("Nothing founded!");
       }
       return listE ;
   }
/**
 поиск продуктов по имени, результат отсортирован начиная с конца алфавита колонки name
 */
    public List  findByNameSortedDesc(String name){
        //Session session = null;
        Transaction tr = null;
        List listE = new ArrayList<Product>();

        try (Session session = createSessionFactory().openSession()) {

            Query query = session.createQuery(("from Product c where c.name in :nameProd order by c.name desc"));
            //session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
            //action

            query.setParameter("nameProd",name);
            List res = query.list();

            //close session/tr
            session.getTransaction().commit();

            System.out.println("Select done ");
            return res;
            //session.close();

        } catch (HibernateException e) {
            System.err.println("Select is failed");
            System.err.println(e.getMessage());
            if (tr != null){
                tr.rollback();//
            }
        }
        catch (NullPointerException e){
            System.err.println("Nothing founded!");
        }
        return listE ;
    }

    /**
     поиск продуктов по вилке цен price +- delta включительно, результат отсортирован по убыванию цен
     */
    public List findByPriceSortedDesc(int price, int delta){
        //Session session = null;
        Transaction tr = null;
        List listE = new ArrayList<Product>();

        try (Session session = createSessionFactory().openSession()) {
            Query query = session.createQuery("from Product c  where c.price between :priceLow and :priceHigh" +
                    " order by c.price desc ");

            tr = session.getTransaction();
            tr.begin();
            //action

            query.setParameter("priceLow",price - delta);
            query.setParameter("priceHigh",price + delta);
            List res = query.list();

            //close session/tr
            session.getTransaction().commit();

            System.out.println("Select done ");
            return res;
            //session.close();

        } catch (HibernateException e) {
            System.err.println("Select is failed");
            System.err.println(e.getMessage());
            if (tr != null){
                tr.rollback();//
            }
        }
        catch (NullPointerException e){
            System.err.println("Nothing founded!");
        }
        return listE ;

    }

    private static SessionFactory createSessionFactory() {
        //singleton pattern нужен чтоб сесии не создавали множество раз
        //если сесион фактори нет то мы её инициализируем и возвращаем
        // если сесион фактори есть то создание не будет происходить а сразу вызовется готовый объект
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
