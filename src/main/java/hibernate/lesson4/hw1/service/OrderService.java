package hibernate.lesson4.hw1.service;

import hibernate.lesson4.hw1.dao.HotelDAO;
import hibernate.lesson4.hw1.dao.OrderDAO;
import hibernate.lesson4.hw1.model.Order;
import hibernate.lesson4.hw1.model.UserType;

public class OrderService {

    final static  OrderDAO orderDAO = new OrderDAO();

    public  void bookRoom(Order order, UserType usType) throws Exception {

            if (usType.equals(UserType.ADMIN)) {
                orderDAO.bookRoom(order);
            }
        else  {
            System.err.println("You havn't rights for modify repository");
        }
    }

    private  boolean ValidateroomId(long roomId) {
        return true;
    }

    private boolean ValidateuserId(long userId) {
        return true;
    }
}
