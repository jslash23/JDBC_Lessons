package hibernate.lesson4.hw1.controller;

import hibernate.lesson4.hw1.model.Hotel;
import hibernate.lesson4.hw1.service.HotelService;

public class HotelController {

    private final static  HotelService hotelService = new HotelService();//зависимость

    public  Hotel findHotelByName(String name) throws Exception{
        return hotelService.findHotelByName(name);//
    }

    public  Hotel findHotelByCity(String city) throws Exception{
        return hotelService.findHotelByCity(city);//
    }
}
