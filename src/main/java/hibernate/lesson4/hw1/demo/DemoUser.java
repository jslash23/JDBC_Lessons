package hibernate.lesson4.hw1.demo;

import hibernate.lesson4.hw1.controller.UserController;
import hibernate.lesson4.hw1.model.User;
import hibernate.lesson4.hw1.model.UserType;

public class DemoUser {

    public static void main(String[] args)throws Exception{

        User newUser = new User(0,"Max","123","Ukraine", UserType.USER);
        String userPath = "C:/Users/Пользователь/Desktop/hibernate.lesson4.hw1/UserDb.txt";

       /*  private long id;
    private String country;
    private String city;
    private  String street;*/
       //Calendar d1 =  Calendar.set(year + 1900, 05, 18);
        //GregorianCalendar calendar1 = new GregorianCalendar(2018,Calendar.DECEMBER,29);

        System.out.println("registerUser:   ");
       UserController.registerUser(newUser, userPath);
    }
}
