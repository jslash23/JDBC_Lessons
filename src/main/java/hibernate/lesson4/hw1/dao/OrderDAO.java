package hibernate.lesson4.hw1.dao;

import hibernate.lesson4.hw1.Ordern;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class OrderDAO {

    private static SessionFactory sessionFactory;

    public static Ordern findById(Long id) {

        Ordern ordern = new Ordern();

        try (Session session = createSessionFactory().openSession()) {
            //
            Query query = session.createQuery("from Ordern where id = :Id");
            Transaction transaction = session.getTransaction();
            transaction.begin();

            //action

            query.setParameter("Id", id);
            List list = query.list();


            //close session/tr
            transaction.commit();

            for (Object l : list) {
                ordern = (Ordern) l;
            }

            return ordern;
            //тут  сессия закроется автоматичесски
            //session.close();

        } catch (HibernateException e) {
            System.err.println("Select from Ordern failed" + e.getMessage());
        }
        return ordern;
    }


    public static void update(Ordern ordern) {

        try (Session session = createSessionFactory().openSession()) {

            Transaction transaction = session.getTransaction();
            transaction.begin();

            Long nr = ordern.getId();
            Ordern findOrdern = findById(nr);
            findOrdern.setMoneyPaid(100);

            //action
            session.update(findOrdern);

            //close session/tr
            transaction.commit();

            //тут  сессия закроется автоматичесски
        } catch (HibernateException e) {
            System.out.println("Nothing update!" + e.getMessage());
        }
    }


    public static void save(Ordern ordern) {

        try (Session session = createSessionFactory().openSession()) {

            Transaction transaction = session.getTransaction();
            transaction.begin();

            //action
            session.save(ordern);

            transaction.commit();

            System.out.println("Save done ");

            //тут  сессия закроется автоматичесски
            //session.close();

        } catch (HibernateException e) {
            System.err.println("Save Ordern failed " + e.getMessage());
        }
    }

    public static void delete(Long id) {

        try (Session session = createSessionFactory().openSession()) {
            Query query = session.createQuery("Delete Ordern where id = :Id");
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
