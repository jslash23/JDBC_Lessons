package hibernate.lesson4.hw1.controller;

import hibernate.lesson4.hw1.model.Order;
import hibernate.lesson4.hw1.model.UserType;
import hibernate.lesson4.hw1.service.OrderService;

public class OrderController  {
    private final static   OrderService orderService = new OrderService();//зависимость

    public  void bookRoom(Order order, UserType user) throws Exception {
        orderService .bookRoom(order, user);
    }

}
