package hibernate.lesson4.hw1.controller;


import hibernate.lesson4.hw1.model.User;
import hibernate.lesson4.hw1.service.UserService;

public class UserController {
    private static UserService userService = new UserService();//зависимость

   public static void registerUser(User user, String userPath) throws Exception{

        userService.registerUser(user, userPath);//тут должна быть логика
    }
}


