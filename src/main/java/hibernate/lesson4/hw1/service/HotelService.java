package hibernate.lesson4.hw1.service;

import hibernate.lesson4.hw1.model.Hotel;
import hibernate.lesson4.hw1.model.UserType;
import hibernate.lesson4.hw1.repository.HotelRepository;

import java.util.ArrayList;


public class HotelService   {

    private HotelRepository hotelRepository = new HotelRepository();

    /*
       private long id;
       private String name;
       private String country;
       private String city;
       private  String street;
        */

    public ArrayList<Hotel> findHotelByName  (String name, String path) throws Exception {

            if (ValidateName(name)){
            return hotelRepository.findHotelByName(name, path);

        }
        throw  new Exception("this fild can't be null!");

    }

    private  boolean ValidateName (String name){

        if (!name.isEmpty() && (name.matches("^[а-яА-ЯёЁa-zA-Z]+$")) ){
            return true;
        }
        return false;
    }

    public  ArrayList<Hotel> findHotelByCity (String city, String path) throws Exception {

            if (ValidateCity(city)) {
                return hotelRepository.findHotelByCity(city, path);
        }
        throw new Exception ("you filds can't be null!");
    }

    private   boolean ValidateCity(String city){
        if (!city.isEmpty() && (city.matches("^[а-яА-ЯёЁa-zA-Z]+$")) ){
            return true;
        }
        return false;
    }
    public void addHotel(Hotel hotel, String path, UserType userType) throws Exception{
        //если юзер у нас является админом то мы выполняем добавление комнаты,
        //иначе не добавляем

            if (userType.equals(UserType.ADMIN)) {
                HotelRepository.addHotel(hotel, path, userType);
            }
        else  {
            System.err.println("You havn't rights for modify repository");
        }
    }

    public void deleteHotel(long hotelId, String pathHotels, UserType usType) throws  Exception{

            if (usType.equals(UserType.ADMIN)) {
                HotelRepository.deleteHotel(hotelId, pathHotels, usType);
            }
         else {
                System.err.println("You havn't rights for modify repository");
            }
    }
}
