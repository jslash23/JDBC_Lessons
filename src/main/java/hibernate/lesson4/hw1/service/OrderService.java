package hibernate.lesson4.hw1.service;

import hibernate.lesson4.hw1.dao.OrderDAO;
import hibernate.lesson4.hw1.model.Order;
import hibernate.lesson4.hw1.model.UserType;

public class OrderService {
    //private OrderRepository orderRepository = new OrderRepository();

    public static void bookRoom(Order order, UserType usType) throws Exception {

            if (usType.equals(UserType.ADMIN)) {
                OrderDAO.bookRoom(order);
            }
        else  {
            System.err.println("You havn't rights for modify repository");
        }
    }

    private static boolean ValidateroomId(long roomId) {
        return true;
    }

    private static boolean ValidateuserId(long userId) {
        return true;
    }
}
