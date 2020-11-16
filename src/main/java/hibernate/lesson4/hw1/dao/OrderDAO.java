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

    private SessionFactory sessionFactory;

    public Order findById(Long id) {
        Order order = new Order();

        try (Session session = createSessionFactory().openSession()) {

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
        } catch (HibernateException e) {
            System.err.println("Select from Order failed" + e.getMessage());
        }
        return order;
    }


    public void update(Order order) {

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

        } catch (HibernateException e) {
            System.out.println("Nothing update!" + e.getMessage());
        }
    }


    public void save(Order order) {

        try (Session session = createSessionFactory().openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            //action
            session.save(order);
            transaction.commit();
            System.out.println("Save done ");

        } catch (HibernateException e) {
            System.err.println("Save Order failed " + e.getMessage());
        }
    }

    public void delete(Long id) {

        try (Session session = createSessionFactory().openSession()) {

            Query query = session.createQuery("Delete Order where id = :Id");
            Transaction transaction = session.getTransaction();
            transaction.begin();
            //action
            query.setParameter("Id", id);
            query.executeUpdate();
            //close session/tr
            transaction.commit();
        }
    }
//methods from project Core

    public void bookRoom(Order order) {

        try (Session session = createSessionFactory().openSession()) {

            Transaction transaction = session.getTransaction();
            transaction.begin();
            //action
            session.save(order);
            transaction.commit();
            System.out.println("Save done ");

        } catch (HibernateException e) {
            System.err.println("Book order failed" + e.getMessage());
        }
    }

    private SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
