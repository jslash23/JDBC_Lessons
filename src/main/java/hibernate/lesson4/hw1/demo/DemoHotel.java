package hibernate.lesson4.hw1.demo;

import hibernate.lesson4.hw1.controller.HotelController;
import hibernate.lesson4.hw1.model.Hotel;
import hibernate.lesson4.hw1.model.UserType;

public class DemoHotel {


    public static void main(String[] args) throws Exception {
        Hotel harkovPlaza = new Hotel(0,"HarkovPlaza","Ukraine","Harkov","anystreet");
        long hotelId = 94;

        String pathHotels = "C:/Users/Пользователь/Desktop/hibernate.lesson4.hw1/HotelDb.txt";

        //HotelController.addHotel(harkovPlaza, pathHotels, UserType.ADMIN );

        HotelController.deleteHotel(hotelId, pathHotels, UserType.ADMIN);
        //HotelController.deleteHotel(hotelId, pathHotels, UserType.USER);
    }
}
