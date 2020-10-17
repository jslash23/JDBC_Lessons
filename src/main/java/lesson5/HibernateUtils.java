package lesson5;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.hibernate.cfg.Environment;

import java.util.Properties;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {

    private static   SessionFactory sessionFactory;

    //Метод сканирует папку resources находит файл hibernate.cfg.xml и скармливает его Хибернейту
    //то есть  конфигурирует Хибернейт используя hibernate.cfg.xml

   public     SessionFactory createSessionFactory(){
        return new Configuration().configure().buildSessionFactory();
    }


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "oracle.jdbc.driver.OracleDriver");
                settings.put(Environment.URL, "jdbc:oracle:thin:@sl22-database1.cshzc28zzyct.us-east-2.rds.amazonaws.com:1521:ORCL");
                settings.put(Environment.USER, "main");
                settings.put(Environment.PASS, "db123slash22");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(Product.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }


    public void  shutDown(){
        sessionFactory.close();
    }


}
