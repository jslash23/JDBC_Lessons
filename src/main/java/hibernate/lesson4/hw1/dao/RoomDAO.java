package hibernate.lesson4.hw1.dao;

import hibernate.lesson4.hw1.Roomn;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import  org.hibernate.query.Query;

import java.util.List;

public class RoomDAO {

    private static SessionFactory sessionFactory;

    public static Roomn findById(Long id) {

        Roomn roomn = new Roomn();

        try(Session session = createSessionFactory().openSession()){
            //
            Query query = session.createQuery("from Roomn where id = :Id");
            Transaction transaction = session.getTransaction();
            transaction.begin();

            //action

            query.setParameter("Id",id);
            List list= query.list();


            //close session/tr
            transaction.commit();

            for (Object l : list) {
                roomn = (Roomn) l;
            }

            return roomn;
            //тут  сессия закроется автоматичесски
            //session.close();

        }
        catch (HibernateException e){
            System.err.println("Select from Roomn failed" + e.getMessage());
        }
        return roomn;
    }


    public static void update(Roomn roomn) {

        try(Session session = createSessionFactory().openSession()){

            Transaction transaction = session.getTransaction();
            transaction.begin();
            //Roomn myEntity = per.findObjectById(myEntity .getId())
            Long nr = roomn.getId();
            Roomn findRoomn = findById(nr);
            findRoomn.setNumberOfGuests(2);

            //action
            session.update(findRoomn);

            //close session/tr
            transaction.commit();

            //тут  сессия закроется автоматичесски
        }
        catch (HibernateException e){
            System.out.println("Nothing update!" + e.getMessage());
        }
    }


    public static void save (Roomn roomn) {

        try(Session session = createSessionFactory().openSession()){

            Transaction transaction = session.getTransaction();
            transaction.begin();

            //action
            session.save(roomn);

            transaction.commit();

            System.out.println("Save done ");

            //тут  сессия закроется автоматичесски
            //session.close();

        }
        catch (HibernateException e){
            System.err.println("Save Roomn failed" + e.getMessage());
        }
    }

    public static void delete(Long id) {

        try (Session session = createSessionFactory().openSession()) {
            Query query = session.createQuery("Delete Roomn where id = :Id");
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
