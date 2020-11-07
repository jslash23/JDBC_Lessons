package hibernate.lesson4.hw1.demo;
import hibernate.lesson4.hw1.controller.OrderController;
import hibernate.lesson4.hw1.model.UserType;

public class DemoOrder {
    public static void main(String[] args)  throws  Exception{
        String orderPath = "C:/Users/Пользователь/Desktop/hibernate.lesson4.hw1/OrderDb.txt";
        long roomId = 1020;
        long userId = 5003;
        String dateFrom = "2018, Month.AUGUST";
        String dateTo = "2020, Month.SEPTEMBER";// 15, 12,45
       //OrderController.bookRoom(roomId,userId,dateFrom,dateTo, orderPath, UserType.ADMIN) ;
        long roomId1 = 1040;
        long userId1 = 5007;
        String dateFrom1 = "2021, Month.AUGUST";
        String dateTo1 = "2022, Month.SEPTEMBER";// 15, 12,45
        //OrderController.bookRoom(roomId1,userId1,dateFrom1,dateTo1, orderPath, UserType.ADMIN);
     OrderController.cancelReservation(roomId, userId, orderPath, UserType.ADMIN);
    }
}
