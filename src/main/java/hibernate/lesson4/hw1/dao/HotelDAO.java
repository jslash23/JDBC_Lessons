package hibernate.lesson4.hw1.dao;

import hibernate.lesson4.hw1.model.Hotel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class HotelDAO {

    SessionFactory sessionFactory;
    Hotel hotel = new Hotel();

    public Hotel findById(Long id) {

        try (Session session = createSessionFactory().openSession()) {
            //
            Query query = session.createQuery("from Hotel where id = :Id");
            Transaction transaction = session.getTransaction();
            transaction.begin();
            //action
            query.setParameter("Id", id);
            List list = query.list();
            //close session/tr
            transaction.commit();

            for (Object l : list) {
                hotel = (Hotel) l;
            }
            return hotel;
        } catch (HibernateException e) {
            System.err.println("Select from Hotel failed" + e.getMessage());
        }
        return hotel;
    }


    public void update(Hotel hotel) {

        try (Session session = createSessionFactory().openSession()) {

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
        } catch (HibernateException e) {
            System.out.println("Nothing update!" + e.getMessage());
        }
    }


    public void save(Hotel hotel) {

        try (Session session = createSessionFactory().openSession()) {

            Transaction transaction = session.getTransaction();
            transaction.begin();
            //action
            session.save(hotel);
            transaction.commit();
            System.out.println("Save done ");

        } catch (HibernateException e) {
            System.err.println("cath worked " + "Save Hotel failed!!!" + e.getMessage());
        }
    }

    public void delete(Long id) {

        try (Session session = createSessionFactory().openSession()) {
            Query query = session.createQuery("Delete Hotel where id = :Id");
            Transaction transaction = session.getTransaction();

            transaction.begin();
            //action
            query.setParameter("Id", id);
            query.executeUpdate();
            //close session/tr
            transaction.commit();
        }
    }

//Methods from project Core

    public Hotel findHotelByName(String name) {

        try (Session session = createSessionFactory().openSession()) {
            Query query = session.createQuery("from Hotel where name = :Name");
            Transaction transaction = session.getTransaction();
            transaction.begin();
            //action
            query.setParameter("Name", name);
            List list = query.list();
            //close session/tr
            transaction.commit();

            for (Object l : list) {
                hotel = (Hotel) l;
            }

            return hotel;

        } catch (HibernateException e) {
            System.err.println("Select from Hotel failed" + e.getMessage());
        }
        return hotel;
    }

    public Hotel findHotelByCity(String city) {
        try (Session session = createSessionFactory().openSession()) {

            Query query = session.createQuery("from Hotel where city = :City");
            Transaction transaction = session.getTransaction();
            transaction.begin();
            //action
            query.setParameter("City", city);
            List list = query.list();
            //close session/tr
            transaction.commit();
            for (Object l : list) {
                hotel = (Hotel) l;
            }
            return hotel;
        } catch (HibernateException e) {
            System.err.println("Select from Hotel failed" + e.getMessage());
        }
        return hotel;
    }


    private SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
