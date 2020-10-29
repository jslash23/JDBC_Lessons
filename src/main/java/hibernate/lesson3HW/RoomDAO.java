package hibernate.lesson3HW;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import  org.hibernate.query.Query;

import java.util.List;

public class RoomDAO {

    private static SessionFactory sessionFactory;

    public static Room findById(Long id) {

        Transaction tr = null;
        Room room = new Room();


        //session = createSessionFactory().openSession();
        try(Session session = createSessionFactory().openSession()){

            Query query = session.createQuery("from Room where id = :Id");
            tr = session.getTransaction();
            tr.begin();

            //action

            query.setParameter("Id",id);
            List list= query.list();


            //close session/tr
            session.getTransaction().commit();

            for (Object l : list) {
                room = (Room) l;
            }

            System.out.println("Select done ");
            return room;
            //тут  сессия закроется автоматичесски
            //session.close();


        } catch (HibernateException e) {
            System.err.println("Select is failed");
            System.err.println(e.getMessage());
            if (tr != null){
                tr.rollback();//
            }
        }
        catch (NullPointerException e){
            System.err.println("Nothing founded!");
        }

        return room;
    }



    public static void saveAll (Room room) {

        Transaction tr = null;

        //session = createSessionFactory().openSession();
        try(Session session = createSessionFactory().openSession()){

            tr = session.getTransaction();
            tr.begin();

            //action
            session.save(room);

            session.getTransaction().commit();

            System.out.println("Save done ");

            //тут  сессия закроется автоматичесски
            //session.close();


        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());
            if (tr != null){
                tr.rollback();//
            }
        }
        catch (NullPointerException e){
            System.err.println("Nothing saved!");
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
