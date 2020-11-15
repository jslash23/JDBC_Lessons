package hibernate.lesson4.hw1.controller;

import hibernate.lesson4.hw1.model.Hotel;
import hibernate.lesson4.hw1.service.HotelService;

public class HotelController   {
    private static   HotelService hotelService = new HotelService();//зависимость

    public static Hotel findHotelByName(String name) throws Exception{
        return hotelService.findHotelByName(name);//
    }

    public static Hotel findHotelByCity(String city) throws Exception{
        return hotelService.findHotelByCity(city);//
    }
}
