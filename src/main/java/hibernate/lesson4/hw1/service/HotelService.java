package hibernate.lesson4.hw1.service;

import hibernate.lesson4.hw1.dao.HotelDAO;
import hibernate.lesson4.hw1.model.Hotel;


public class HotelService {

    final static HotelDAO hotelDAO = new HotelDAO();

    public Hotel findHotelByName(String name) throws Exception {

        if (ValidateName(name)) {
            return hotelDAO.findHotelByName(name);

        }
        throw new Exception("this fild can't be null!");

    }

    private boolean ValidateName(String name) {

        if (!name.isEmpty() && (name.matches("^[а-яА-ЯёЁa-zA-Z]+$"))) {
            return true;
        }
        return false;
    }

    public Hotel findHotelByCity(String city) throws Exception {

        if (ValidateCity(city)) {
            return hotelDAO.findHotelByCity(city);
        }
        throw new Exception("you filds can't be null!");
    }

    private boolean ValidateCity(String city) {
        if (!city.isEmpty() && (city.matches("^[а-яА-ЯёЁa-zA-Z]+$"))) {
            return true;
        }
        return false;
    }

}
