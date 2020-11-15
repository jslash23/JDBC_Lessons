package hibernate.lesson4.hw1.service;

import hibernate.lesson4.hw1.dao.UserDAO;
import hibernate.lesson4.hw1.exceptions.BadRequestException;


import java.util.Scanner;

public class UserService  {
    final static  private UserDAO userDAO = new UserDAO();
}
