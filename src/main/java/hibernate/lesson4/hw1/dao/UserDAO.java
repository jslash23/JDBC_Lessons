package hibernate.lesson4.hw1.dao;

import hibernate.lesson4.hw1.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAO {

    private  SessionFactory sessionFactory;

    public  User findById(Long id) {

        User user = new User();

        try (Session session = createSessionFactory().openSession()) {
            //
            Query query = session.createQuery("from User where id = :Id");
            Transaction transaction = session.getTransaction();
            transaction.begin();

            //action

            query.setParameter("Id", id);
            List list = query.list();


            //close session/tr
            transaction.commit();

            for (Object l : list) {
                user = (User) l;
            }

            return user;
            //тут  сессия закроется автоматичесски
            //session.close();

        } catch (HibernateException e) {
            System.err.println("Select from User failed" + e.getMessage());
        }
        return user;
    }


    public  void update(User user) {

        try (Session session = createSessionFactory().openSession()) {

            Transaction transaction = session.getTransaction();
            transaction.begin();

            Long nr = user.getId();
            User findUser = findById(nr);
            findUser.setName("Petya");

            //action
            session.update(findUser);

            //close session/tr
            transaction.commit();

            //тут  сессия закроется автоматичесски
        } catch (HibernateException e) {
            System.out.println("Nothing update!" + e.getMessage());
        }
    }


    public  void save(User user) {

        try (Session session = createSessionFactory().openSession()) {

            Transaction transaction = session.getTransaction();
            transaction.begin();

            //action
            session.save(user);

            transaction.commit();

            System.out.println("Save done ");

            //тут  сессия закроется автоматичесски
            //session.close();

        } catch (HibernateException e) {
            System.err.println("Save User failed " + e.getMessage());
        }
    }

    public  void delete(Long id) {

        try (Session session = createSessionFactory().openSession()) {
            Query query = session.createQuery("Delete User where id = :Id");
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


    private  SessionFactory createSessionFactory() {
        //singleton pattern нужен чтоб сесии не создавали множество раз
        //если сесион фактори нет то мы её инициализируем и возвращаем
        // если сесион фактори есть то создание не будет происходить а сразу вызовется готовый объект
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
