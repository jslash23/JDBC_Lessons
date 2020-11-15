package hibernate.lesson4.hw1.dao;

import hibernate.lesson4.hw1.model.Order;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class OrderDAO {

    private static SessionFactory sessionFactory;

    public static Order findById(Long id) {

        Order order = new Order();

        try (Session session = createSessionFactory().openSession()) {
            //
            Query query = session.createQuery("from Order where id = :Id");
            Transaction transaction = session.getTransaction();
            transaction.begin();

            //action

            query.setParameter("Id", id);
            List list = query.list();


            //close session/tr
            transaction.commit();

            for (Object l : list) {
                order = (Order) l;
            }

            return order;
            //тут  сессия закроется автоматичесски
            //session.close();

        } catch (HibernateException e) {
            System.err.println("Select from Order failed" + e.getMessage());
        }
        return order;
    }


    public static void update(Order order) {

        try (Session session = createSessionFactory().openSession()) {

            Transaction transaction = session.getTransaction();
            transaction.begin();

            Long nr = order.getId();
            Order findOrder = findById(nr);
            findOrder.setMoneyPaid(100);

            //action
            session.update(findOrder);

            //close session/tr
            transaction.commit();

            //тут  сессия закроется автоматичесски
        } catch (HibernateException e) {
            System.out.println("Nothing update!" + e.getMessage());
        }
    }


    public static void save(Order order) {

        try (Session session = createSessionFactory().openSession()) {

            Transaction transaction = session.getTransaction();
            transaction.begin();

            //action
            session.save(order);

            transaction.commit();

            System.out.println("Save done ");

            //тут  сессия закроется автоматичесски
            //session.close();

        } catch (HibernateException e) {
            System.err.println("Save Order failed " + e.getMessage());
        }
    }

    public static void delete(Long id) {

        try (Session session = createSessionFactory().openSession()) {
            Query query = session.createQuery("Delete Order where id = :Id");
            Transaction transaction = session.getTransaction();


            transaction.begin();

            //action

            query.setParameter("Id", id);
            query.executeUpdate();

            //close session/tr
            transaction.commit();

            //тут  сессия закроется автоматичесски
            //session.close();

        }
    }
//methods from project Core

    public static void bookRoom (Order order) {

        try(Session session = createSessionFactory().openSession()){

            Transaction transaction = session.getTransaction();
            transaction.begin();

            //action
            session.save(order);

            transaction.commit();

            System.out.println("Save done ");

            //тут  сессия закроется автоматичесски
            //session.close();

        }
        catch (HibernateException e){
            System.err.println("Book order failed" + e.getMessage());
        }
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
