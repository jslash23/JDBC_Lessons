package hibernate.lesson4.hw1.demo;

import hibernate.lesson4.hw1.controller.HotelController;
import hibernate.lesson4.hw1.controller.OrderController;
import hibernate.lesson4.hw1.controller.RoomController;
import hibernate.lesson4.hw1.controller.UserController;
import hibernate.lesson4.hw1.model.*;
import hibernate.lesson4.hw1.dao.HotelDAO;
import hibernate.lesson4.hw1.dao.OrderDAO;
import hibernate.lesson4.hw1.dao.RoomDAO;
import hibernate.lesson4.hw1.dao.UserDAO;

import java.util.*;

public class DemoDAO {

  private static final   RoomDAO roomDAO = new RoomDAO();
   private static final  HotelDAO hotelDAO = new HotelDAO();
   private static final  UserDAO userDAO = new UserDAO();
   private static final  OrderDAO orderDAO = new OrderDAO();

    public static void main(String[] args) throws Exception {

        Room room1 = new Room();
        Room room2 = new Room();
        Order order = new Order();
        User user = new User();

        room1.setNumberOfGuests(2);
        room1.setPrice(500);
        room1.setBreakfastIncluded(1);
        room1.setPetsAllowed(1);
        room1.setDateAvailableFrom(new Date());
        room1.setHotel(hotelDAO.findById(1L));

        room2.setNumberOfGuests(1);
        room2.setPrice(700);
        room2.setBreakfastIncluded(0);
        room2.setPetsAllowed(0);
        room2.setDateAvailableFrom(new Date());
        room2.setHotel(hotelDAO.findById(4L));

        //Create collections of Roomns
        List<Room> rooms = new ArrayList<>();

        //adding new room to collectiom rooms
        rooms.add(roomDAO.findById(1L));
        rooms.add(roomDAO.findById(5L));

        order.getRoom().setId(10);
        order.getUser().setId(20);
        order.setDateFrom(new Date());
        order.setDateTo(new Date());

        Hotel hotel1 = new Hotel();

///
        hotel1.setName("KievPlaza");
        hotel1.setCountry("Ukraine");
        hotel1.setCity("Kiev");
        hotel1.setStreet("Hreshatik");
        hotel1.setRooms(rooms);

        Hotel hotel2 = new Hotel();

        hotel2.setName("Stolica");
        hotel2.setCountry("Ukraine");
        hotel2.setCity("Kiev");
        hotel2.setStreet("Pechersk");
        hotel2.setRooms(rooms);


        Filter filterNew = new Filter(2,100,true,true,new Date(),
        "Ukraine", "Kiev");

        OrderController orderController = new OrderController();
        RoomController roomController = new RoomController();
        HotelController hotelController = new HotelController();
        UserController userController = new UserController();

       hotelDAO.save(hotel1);
        //roomDAO.save(room1);
        //orderDAO.save(order);

        /*orderController.bookRoom(order, UserType.USER) ;
        roomController.findRooms(filterNew);
        hotelController.findHotelByName("Plaza");
        hotelController.findHotelByCity("Kiev");*/
    }
}
