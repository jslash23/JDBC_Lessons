package hibernate.lesson4.hw1.dao;

import hibernate.lesson4.hw1.Hoteln;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import  org.hibernate.query.Query;

import java.util.List;

public class HotelDAO {

    private static SessionFactory sessionFactory;

    public static Hoteln findById(Long id) {

        Hoteln hoteln = new Hoteln();

        try(Session session = createSessionFactory().openSession()){
            //
            Query query = session.createQuery("from Hoteln where id = :Id");
            Transaction transaction = session.getTransaction();
            transaction.begin();

            //action

            query.setParameter("Id",id);
            List list= query.list();


            //close session/tr
            transaction.commit();

            for (Object l : list) {
                hoteln = (Hoteln) l;
            }

            return hoteln;
            //тут  сессия закроется автоматичесски
            //session.close();

        }
        catch (HibernateException e){
            System.err.println("Select from Hoteln failed" + e.getMessage());
        }
        return hoteln;
    }


    public static void update(Hoteln hoteln) {

        try(Session session = createSessionFactory().openSession()){

            Transaction transaction = session.getTransaction();
            transaction.begin();
            //Roomn myEntity = per.findObjectById(myEntity .getId())
            Long nr = hoteln.getId();
            Hoteln findHoteln = findById(nr);
            findHoteln.setName("Lux");

            //action
            session.update(findHoteln);

            //close session/tr
            transaction.commit();

            //тут  сессия закроется автоматичесски
        }
        catch (HibernateException e){
            System.out.println("Nothing update!" + e.getMessage());
        }
    }


    public static void save (Hoteln hoteln) {

        try(Session session = createSessionFactory().openSession()){

            Transaction transaction = session.getTransaction();
            transaction.begin();

            //action
            session.save(hoteln);

            transaction.commit();

            System.out.println("Save done ");

            //тут  сессия закроется автоматичесски
            //session.close();

        }
        catch (HibernateException e){
            System.err.println("Save Hoteln failed " + e.getMessage());
        }
        }

    public static void delete(Long id) {

        try (Session session = createSessionFactory().openSession()) {
            Query query = session.createQuery("Delete Hoteln where id = :Id");
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
