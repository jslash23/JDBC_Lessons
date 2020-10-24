package hibernate.lesson2HW3;

import hibernate.lesson1.Product;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductDAO {

    private static SessionFactory sessionFactory;

    /**
     * поиск продукта по id
     */
    public List<Product> findById(Long id) {

        Transaction tr = null;

        List listE = new ArrayList<Product>();
        try (Session session = createSessionFactory().openSession()) {

            tr = session.getTransaction();
            tr.begin();

            //action
            String sql = "SELECT ID, NAME, DESCRIPTION, PRICE from PRODUCTJD where ID = ?";

            Query query = session.createSQLQuery(sql).addEntity(Product.class);
            query.setParameter(1, id);
            List<Product> products = query.list();

            session.getTransaction().commit();
            System.out.println("Select done ");

            return products;
            //тут  сессия закроется автоматичесски
            //session.close();


        } catch (HibernateException e) {
            System.err.println("Select is failed");
            System.err.println(e.getMessage());
            if (tr != null) {
                tr.rollback();//
            }
        } catch (NullPointerException e) {
            System.err.println("Nothing founded!");
        }

        return listE;
    }


    /**
     * поиск продуктов по имени
     */
    public List findByName(String name) {

        Transaction tr = null;
        List listE = new ArrayList<Product>();

        try (Session session = createSessionFactory().openSession()) {

            tr = session.getTransaction();
            tr.begin();
            //action
            String sql = "SELECT * from PRODUCTJD where NAME LIKE ?";
            Query query = session.createSQLQuery(sql).addEntity(Product.class);
            query.setParameter(1, name);
            List<Product> products = query.list();


            //close session/tr
            session.getTransaction().commit();

            System.out.println("Select done ");
            return products;
            //session.close();

        } catch (HibernateException e) {
            System.err.println("Select is failed");
            System.err.println(e.getMessage());
            if (tr != null) {
                tr.rollback();//
            }
        } catch (NullPointerException e) {
            System.err.println("Nothing founded!");
        }

        return listE;
    }


    /**
     * поиск продуктов, которые в своем имени содержать слово name
     */
    public List findByContainedName(String name) {

        Transaction tr = null;
        List listE = new ArrayList<Product>();

        try (Session session = createSessionFactory().openSession()) {
            //Фильтрация по одному условию и нескольким значениям с применением IN

            tr = session.getTransaction();
            tr.begin();
            //action
            String sql = "SELECT * from PRODUCTJD where NAME IN ?";
            Query query = session.createSQLQuery(sql).addEntity(Product.class);
            query.setParameter(1, name);
            List<Product> products = query.list();


            //close session/tr
            session.getTransaction().commit();

            System.out.println("Select done ");
            return products;
            //session.close();

        } catch (HibernateException e) {
            System.err.println("Select is failed");
            System.err.println(e.getMessage());
            if (tr != null) {
                tr.rollback();//
            }
        } catch (NullPointerException e) {
            System.err.println("Nothing founded!");
        }
        return listE;
    }

    /**
     * поиск продуктов по вилке цен price+-delta включительно
     * будет искать продукты с заданной ценной в диапазоне +=delta включительно.
     * Например, если нужно найти продукты с ценой 100 и дельтой 10, то ищем все от 90 до 110
     */
    public List findByPrice(int price, int delta) {

        Transaction tr = null;
        List listE = new ArrayList<Product>();

        try (Session session = createSessionFactory().openSession()) {

            tr = session.getTransaction();
            tr.begin();
            //action

            String sql = "SELECT * from PRODUCTJD where PRICE BETWEEN ? AND ?";
            Query query = session.createSQLQuery(sql).addEntity(Product.class);
            query.setParameter(1, price - delta);
            query.setParameter(2, price + delta);
            List<Product> products = query.list();

            //close session/tr
            session.getTransaction().commit();

            System.out.println("Select done ");
            return products;
            //session.close();

        } catch (HibernateException e) {
            System.err.println("Select is failed");
            System.err.println(e.getMessage());
            if (tr != null) {
                tr.rollback();//
            }
        } catch (NullPointerException e) {
            System.err.println("Nothing founded!");
        }
        return listE;
    }

    /**
     * поиск продуктов по имени, результат отсортирован по алфавитному порядку колонки name
     */

    public List findByNameSortedAsc(String name) {

        Transaction tr = null;
        List listE = new ArrayList<Product>();

        try (Session session = createSessionFactory().openSession()) {

            tr = session.getTransaction();
            tr.begin();
            //action

            String sql = "SELECT * from PRODUCTJD where NAME LIKE ? ORDER BY NAME ASC";
            Query query = session.createSQLQuery(sql).addEntity(Product.class);
            query.setParameter(1, name);
            List<Product> products = query.list();

            //close session/tr
            session.getTransaction().commit();

            System.out.println("Select done ");
            return products;
            //session.close();

        } catch (HibernateException e) {
            System.err.println("Select is failed");
            System.err.println(e.getMessage());
            if (tr != null) {
                tr.rollback();//
            }
        } catch (NullPointerException e) {
            System.err.println("Nothing founded!");
        }
        return listE;
    }

    /**
     * поиск продуктов по имени, результат отсортирован начиная с конца алфавита колонки name
     */
    public List findByNameSortedDesc(String name) {

        Transaction tr = null;
        List listE = new ArrayList<Product>();

        try (Session session = createSessionFactory().openSession()) {


            tr = session.getTransaction();
            tr.begin();
            //action

            String sql = "SELECT * from PRODUCTJD where NAME LIKE ? ORDER BY PRICE NAME";
            Query query = session.createSQLQuery(sql).addEntity(Product.class);
            query.setParameter(1, name);
            List<Product> products = query.list();

            //close session/tr
            session.getTransaction().commit();

            System.out.println("Select done ");
            return products;
            //session.close();

        } catch (HibernateException e) {
            System.err.println("Select is failed");
            System.err.println(e.getMessage());
            if (tr != null) {
                tr.rollback();//
            }
        } catch (NullPointerException e) {
            System.err.println("Nothing founded!");
        }
        return listE;
    }


    /**
     * поиск продуктов по вилке цен price +- delta включительно, результат отсортирован по убыванию цен
     */
    public List findByPriceSortedDesc(int price, int delta) {

        Transaction tr = null;
        List listE = new ArrayList<Product>();

        try (Session session = createSessionFactory().openSession()) {

            tr = session.getTransaction();
            tr.begin();
            //action

            String sql = "SELECT * from PRODUCTJD where PRICE BETWEEN ? AND ? ORDER BY PRICE DESC";
            Query query = session.createSQLQuery(sql).addEntity(Product.class);
            query.setParameter(1, price - delta);
            query.setParameter(2, price + delta);
            List<Product> products = query.list();

            //close session/tr
            session.getTransaction().commit();

            System.out.println("Select done ");
            return products;
            //session.close();

        } catch (HibernateException e) {
            System.err.println("Select is failed");
            System.err.println(e.getMessage());
            if (tr != null) {
                tr.rollback();//
            }
        } catch (NullPointerException e) {
            System.err.println("Nothing founded!");
        }
        return listE;

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
