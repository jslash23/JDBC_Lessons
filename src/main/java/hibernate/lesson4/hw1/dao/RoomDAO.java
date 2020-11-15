package hibernate.lesson4.hw1.dao;

import hibernate.lesson4.hw1.model.Filter;
import hibernate.lesson4.hw1.model.Room;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import  org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    private static SessionFactory sessionFactory;

    public static Room findById(Long id) {

        Room room = new Room();

        try(Session session = createSessionFactory().openSession()){
            //
            Query query = session.createQuery("from Room where id = :Id");
            Transaction transaction = session.getTransaction();
            transaction.begin();

            //action

            query.setParameter("Id",id);
            List list= query.list();


            //close session/tr
            transaction.commit();

            for (Object l : list) {
                room = (Room) l;
            }

            return room;
            //тут  сессия закроется автоматичесски
            //session.close();

        }
        catch (HibernateException e){
            System.err.println("Select from Room failed" + e.getMessage());
        }
        return room;
    }


    public static void update(Room room) {

        try(Session session = createSessionFactory().openSession()){

            Transaction transaction = session.getTransaction();
            transaction.begin();
            //Room myEntity = per.findObjectById(myEntity .getId())
            Long nr = room.getId();
            Room findRoom = findById(nr);
            findRoom.setNumberOfGuests(2);

            //action
            session.update(findRoom);

            //close session/tr
            transaction.commit();

            //тут  сессия закроется автоматичесски
        }
        catch (HibernateException e){
            System.out.println("Nothing update!" + e.getMessage());
        }
    }


    public static void save (Room room) {

        try(Session session = createSessionFactory().openSession()){

            Transaction transaction = session.getTransaction();
            transaction.begin();

            //action
            session.save(room);

            transaction.commit();

            System.out.println("Save done ");

            //тут  сессия закроется автоматичесски
            //session.close();

        }
        catch (HibernateException e){
            System.err.println("Save Room failed" + e.getMessage());
        }
    }

    public static void delete(Long id) {

        try (Session session = createSessionFactory().openSession()) {
            Query query = session.createQuery("Delete Room where id = :Id");
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

    public static List<Room> findRooms(Filter filter){
       List <Room> room = new ArrayList<>();

        try(Session session = createSessionFactory().openSession()){
            //
            Query query = session.createQuery("from Room where numberOfGuests = :ng and price = :pr " +
                    "and breakfastIncluded = :bi and petsAllowed = :pa and dateAvailableFrom = : daf " +
                    "   ");//////////////
            Transaction transaction = session.getTransaction();
            transaction.begin();

            //action

            query.setParameter("ng",filter.getNumbersOfGuests());
            query.setParameter("pr",filter.getPrice());
            query.setParameter("bi",filter.isBreakfastIncluded());
            query.setParameter("pa",filter.isPetsAllowed());
            query.setParameter("daf",filter.getDateAvableFrom());

            room = query.list();

            //close session/tr
            transaction.commit();



            return room;
            //тут  сессия закроется автоматичесски
            //session.close();

        }
        catch (HibernateException e){
            System.err.println("Select from Room failed" + e.getMessage());
        }
        return room;
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
