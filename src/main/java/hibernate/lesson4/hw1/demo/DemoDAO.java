package hibernate.lesson4.hw1.demo;

import hibernate.lesson4.hw1.model.*;
import hibernate.lesson4.hw1.dao.HotelDAO;
import hibernate.lesson4.hw1.dao.OrderDAO;
import hibernate.lesson4.hw1.dao.RoomDAO;
import hibernate.lesson4.hw1.dao.UserDAO;

import java.util.*;

public class DemoDAO {
    public static void main(String[] args) throws Exception {

        RoomDAO roomDAO = new RoomDAO();
        HotelDAO hotelDAO = new HotelDAO();
        UserDAO userDAO = new UserDAO();
        OrderDAO orderDAO = new OrderDAO();


        Room room1 = new Room();
        Room room2 = new Room();
        Order order = new Order();
        User user = new User();

        room1.setNumberOfGuests(2);
        room1.setPrice(500);
        room1.setBreakfastIncluded(1);
        room1.setPetsAllowed(1);
        room1.setDateAvailableFrom(new Date());
        room1.setHotel(HotelDAO.findById(1L));

        room2.setNumberOfGuests(1);
        room2.setPrice(700);
        room2.setBreakfastIncluded(0);
        room2.setPetsAllowed(0);
        room2.setDateAvailableFrom(new Date());
        room2.setHotel(HotelDAO.findById(4L));

        //Create collections of Roomns
        List<Room> rooms = new ArrayList<>();

        //adding new room to collectiom rooms
        rooms.add(RoomDAO.findById(1L));
        rooms.add(RoomDAO.findById(5L));

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

       // hotelDAO.save(hotel1);
        //roomDAO.save(room1);
        hibernate.lesson4.hw1.controller.OrderController.bookRoom(order, UserType.USER) ;
        hibernate.lesson4.hw1.controller.RoomController.findRooms(filterNew);
        hibernate.lesson4.hw1.controller.HotelController.findHotelByName("Plaza");
        hibernate.lesson4.hw1.controller.HotelController.findHotelByCity("Kiev");

    }
}
