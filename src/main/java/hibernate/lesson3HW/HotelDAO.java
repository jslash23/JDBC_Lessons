package hibernate.lesson3HW;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import  org.hibernate.query.Query;

import java.util.List;

public class HotelDAO {
    private static SessionFactory sessionFactory;

    public static Hotel findById(Long id) {

        Hotel hotel = new Hotel();

        try(Session session = createSessionFactory().openSession()){

            Query query = session.createQuery("from Hotel where id = :Id");
            Transaction transaction = session.getTransaction();
            transaction.begin();

            //action

            query.setParameter("Id",id);
            List list= query.list();


            //close session/tr
            transaction.commit();

            for (Object l : list) {
                hotel = (Hotel) l;
            }

            return hotel;
            //тут  сессия закроется автоматичесски
            //session.close();


        }
        catch (HibernateException e){
            System.err.println("Select from Hotel failed" + e.getMessage());
        }

        return hotel;
    }



    public static void saveAll (Hotel hotel) {

        try(Session session = createSessionFactory().openSession()){

            Transaction transaction = session.getTransaction();
            transaction.begin();

            //action
            session.save(hotel);

            transaction.commit();

            System.out.println("Save done ");

            //тут  сессия закроется автоматичесски
            //session.close();

        }
        catch (HibernateException e){
            System.err.println("Save Hotel failed" + e.getMessage());
        }
    }

    public static void   deleteAllHotels() {

        try(Session session = createSessionFactory().openSession()){

            Query query = session.createQuery("Delete  Hotel");
            Transaction transaction = session.getTransaction();
            transaction.begin();

            //action

            query.executeUpdate();
            //close session/tr
            transaction.commit();

            //тут  сессия закроется автоматичесски
            //session.close();
            System.out.println("All hotels were deleted!");

        }
        catch (HibernateException e){
            System.err.println("DeleteAll from Hotel failed" + e.getMessage());
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
