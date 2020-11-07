package hibernate.lesson4.hw1.controller;

import hibernate.lesson4.hw1.model.Hotel;
import hibernate.lesson4.hw1.model.UserType;
import hibernate.lesson4.hw1.service.HotelService;

import java.util.ArrayList;

public class HotelController   {
    private static HotelService hotelService = new HotelService();//зависимость

    public static ArrayList<Hotel> findHotelByName(String name, String path) throws Exception{
        return hotelService.findHotelByName(name,path);//
    }

    public static  ArrayList<Hotel> findHotelByCity(String city, String path) throws Exception{
        return hotelService.findHotelByCity(city,path);//
    }


    public static void addHotel (Hotel hotel, String path, UserType usType) throws Exception {
      hotelService.addHotel(hotel, path, usType);
    }


    public static void deleteHotel(long hotelId, String pathHotels, UserType admin) throws Exception {
        hotelService.deleteHotel(hotelId, pathHotels, admin);
    }
}
