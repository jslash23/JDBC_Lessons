package hibernate.lesson3HW;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import  org.hibernate.query.Query;


import javax.transaction.Transactional;
import java.util.List;

public class RoomDAO {

    private static SessionFactory sessionFactory;

    public static Room findById(Long id) {
        Room room = new Room();

        try(Session session = createSessionFactory().openSession()){
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
            System.out.println("Nothing founded!" + e.getMessage());
        }
        return room;
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

    public static void deleteAllRooms() {

        try(Session session = createSessionFactory().openSession()){
            Query query = session.createQuery("Delete Room ");
            Transaction transaction = session.getTransaction();

            transaction.begin();

            //action

            query.executeUpdate();

            //close session/tr
            transaction.commit();

            //тут  сессия закроется автоматичесски
            //session.close();

        }
        catch (HibernateException e){
            System.out.println("DeleteAllRoooms failed! " + e.getMessage());
        }
    }


    public static void update(Room room) {

        try(Session session = createSessionFactory().openSession()){

            Transaction transaction = session.getTransaction();
            transaction.begin();
            //Room myEntity = per.findObjectById(myEntity .getId())

            //action
            session.update(room);

            //close session/tr
            transaction.commit();

            //тут  сессия закроется автоматичесски

        }
        catch (HibernateException e){
            System.out.println("Nothing update!" + e.getMessage());
        }
    }

    public static void saveAll (Room room) {

        try(Session session = createSessionFactory().openSession()){

           Transaction transaction = session.getTransaction();

            transaction.begin();

            //action
            session.save(room);
           // query1.executeUpdate();
            transaction.commit();

            System.out.println("Save done ");

            //тут  сессия закроется автоматичесски
            //session.close();

        }
        catch (HibernateException e){
            System.err.println("Save Room failed");
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


 /* Query query = session.createQuery("UPDATE Room SET numberOfGuests = :NOFG"
                    + ", price = :priceRoom"
                    + ", breakfastIncluded =:breakfastIncluded"
                    + ", petsAllowed =:petsAllowed"
                    + ", dateAvailableFrom =:dateAvail"
                    + ", hotel =: hotelObj "
                    + " WHERE id = :getId");*/

  /*  query.setParameter("getId",rooom.getId());
            query.setParameter("NOFG",rooom.getNumberOfGuests());
            query.setParameter("priceRoom",rooom.getPrice());
            query.setParameter("breakfastIncluded",rooom.getBreakfastIncluded());
            query.setParameter("petsAllowed",rooom.getPetsAllowed());
            query.setParameter("dateAvail",rooom.getDateAvailableFrom());
             query.setParameter("hotelObj",rooom.getHotel());*/
//int result = query.executeUpdate();



 /* Query query1 = session.createQuery("insert into Hotel (NUMBEROFGUESTS, PRICE," +
                    " BREAKFASTINCLUDED, PETSALLOWED, DATEAVAILABLEFROM) values ?????");

            Query query2 = session.createQuery("insert into Room (NAME, COUNTRY," +
                    "CITY, STREET) values ????");

            query1.setParameter(1, room.getNumberOfGuests());
            query1.setParameter(2, room.getPrice());
            query1.setParameter(3, room.getBreakfastIncluded());
            query1.setParameter(4, room.getPetsAllowed());
            query1.setParameter(5, room.getDateAvailableFrom());*/
