package hibernate.lesson4.hw1.dao;

import hibernate.lesson4.hw1.Hoteln;
import hibernate.lesson4.hw1.Usern;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAO {

    private static SessionFactory sessionFactory;

    public static Usern findById(Long id) {

        Usern usern = new Usern();

        try (Session session = createSessionFactory().openSession()) {
            //
            Query query = session.createQuery("from Usern where id = :Id");
            Transaction transaction = session.getTransaction();
            transaction.begin();

            //action

            query.setParameter("Id", id);
            List list = query.list();


            //close session/tr
            transaction.commit();

            for (Object l : list) {
                usern = (Usern) l;
            }

            return usern;
            //тут  сессия закроется автоматичесски
            //session.close();

        } catch (HibernateException e) {
            System.err.println("Select from Usern failed" + e.getMessage());
        }
        return usern;
    }


    public static void update(Usern usern) {

        try (Session session = createSessionFactory().openSession()) {

            Transaction transaction = session.getTransaction();
            transaction.begin();

            Long nr = usern.getId();
            Usern findUsern = findById(nr);
            findUsern.setName("Petya");

            //action
            session.update(findUsern);

            //close session/tr
            transaction.commit();

            //тут  сессия закроется автоматичесски
        } catch (HibernateException e) {
            System.out.println("Nothing update!" + e.getMessage());
        }
    }


    public static void save(Usern usern) {

        try (Session session = createSessionFactory().openSession()) {

            Transaction transaction = session.getTransaction();
            transaction.begin();

            //action
            session.save(usern);

            transaction.commit();

            System.out.println("Save done ");

            //тут  сессия закроется автоматичесски
            //session.close();

        } catch (HibernateException e) {
            System.err.println("Save Usern failed " + e.getMessage());
        }
    }

    public static void delete(Long id) {

        try (Session session = createSessionFactory().openSession()) {
            Query query = session.createQuery("Delete Usern where id = :Id");
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
