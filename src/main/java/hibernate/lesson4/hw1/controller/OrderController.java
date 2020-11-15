package hibernate.lesson4.hw1.controller;

import hibernate.lesson4.hw1.model.Order;
import hibernate.lesson4.hw1.model.UserType;
import hibernate.lesson4.hw1.service.OrderService;

public class OrderController  {
    private static OrderService orderService = new OrderService();//зависимость

    public static void bookRoom(Order order, UserType user) throws Exception {
        OrderService.bookRoom(order, user);
    }

}
