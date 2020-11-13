package hibernate.lesson4.hw1.demo;

import hibernate.lesson4.hw1.Hoteln;
import hibernate.lesson4.hw1.Roomn;
import hibernate.lesson4.hw1.Usern;
import hibernate.lesson4.hw1.dao.HotelDAO;
import hibernate.lesson4.hw1.dao.OrderDAO;
import hibernate.lesson4.hw1.dao.RoomDAO;
import hibernate.lesson4.hw1.dao.UserDAO;
import org.hibernate.criterion.Order;

import java.util.*;

public class DemoDAO {
    public static void main(String[] args) {

        RoomDAO roomDAO = new RoomDAO();
        HotelDAO hotelDAO = new HotelDAO();
        UserDAO userDAO = new UserDAO();
        OrderDAO orderDAO = new OrderDAO();


        Roomn roomn1 = new Roomn();
        Roomn roomn2 = new Roomn();

        roomn1.setNumberOfGuests(2);
        roomn1.setPrice(500);
        roomn1.setBreakfastIncluded(1);
        roomn1.setPetsAllowed(1);
        roomn1.setDateAvailableFrom(new Date());
        roomn1.setHoteln(HotelDAO.findById(1L));

        roomn2.setNumberOfGuests(1);
        roomn2.setPrice(700);
        roomn2.setBreakfastIncluded(0);
        roomn2.setPetsAllowed(0);
        roomn2.setDateAvailableFrom(new Date());
        roomn2.setHoteln(HotelDAO.findById(4L));

        //Create collections of Roomns
        List<Roomn> roomns = new ArrayList<>();

        //adding new room to collectiom roomns
        roomns.add(RoomDAO.findById(1L));
        roomns.add(RoomDAO.findById(5L));


        Hoteln hoteln1 = new Hoteln();

///
        hoteln1.setName("KievPlaza");
        hoteln1.setCountry("Ukraine");
        hoteln1.setCity("Kiev");
        hoteln1.setStreet("Hreshatik");
        hoteln1.setRoomns(roomns);

        Hoteln hoteln2 = new Hoteln();

        hoteln2.setName("Stolica");
        hoteln2.setCountry("Ukraine");
        hoteln2.setCity("Kiev");
        hoteln2.setStreet("Pechersk");
        hoteln2.setRoomns(roomns);


        hotelDAO.save(hoteln1);
        //roomDAO.save(roomn1);


    }
}
