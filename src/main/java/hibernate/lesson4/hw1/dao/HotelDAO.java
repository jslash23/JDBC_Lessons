package hibernate.lesson4.hw1.dao;

import hibernate.lesson4.hw1.model.Hotel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import  org.hibernate.query.Query;

import java.util.List;

public class HotelDAO {

     SessionFactory sessionFactory;

    public  Hotel findById(Long id) {

        Hotel hotel = new Hotel();

        try(Session session = createSessionFactory().openSession()){
            //
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


    public  void update(Hotel hotel) {

        try(Session session = createSessionFactory().openSession()){

            Transaction transaction = session.getTransaction();
            transaction.begin();
            //Room myEntity = per.findObjectById(myEntity .getId())
            Long nr = hotel.getId();
            Hotel findHotel = findById(nr);
            findHotel.setName("Lux");

            //action
            session.update(findHotel);

            //close session/tr
            transaction.commit();

            //тут  сессия закроется автоматичесски
        }
        catch (HibernateException e){
            System.out.println("Nothing update!" + e.getMessage());
        }
    }


    public  void save (Hotel hotel) {

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
            System.err.println("Save Hotel failed " + e.getMessage());
        }
        }

    public  void delete(Long id) {

        try (Session session = createSessionFactory().openSession()) {
            Query query = session.createQuery("Delete Hotel where id = :Id");
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

//Methods from project Core

    public  Hotel findHotelByName(String name) {

        Hotel hotel = new Hotel();

        try(Session session = createSessionFactory().openSession()){
            //
            Query query = session.createQuery("from Hotel where name = :Name");
            Transaction transaction = session.getTransaction();
            transaction.begin();

            //action

            query.setParameter("Name",name);
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


    public  Hotel findHotelByCity(String city) {

        Hotel hotel = new Hotel();

        try(Session session = createSessionFactory().openSession()){
            //
            Query query = session.createQuery("from Hotel where city = :City");
            Transaction transaction = session.getTransaction();
            transaction.begin();

            //action

            query.setParameter("City", city);
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
