package hibernate.lesson4.hw1.service;

import hibernate.lesson4.hw1.dao.RoomDAO;
import hibernate.lesson4.hw1.model.*;

import java.util.List;

public class RoomService {

    public List<Room> findRooms(Filter filter) throws Exception {
        //if ((validateCity(filter)))  не работает
        return RoomDAO.findRooms(filter);
        //throw  new Exception("can't find you city or room!");
    }



    private boolean validateCity(Filter filter) throws Exception {
        //Если поле пустое или не содержит буквы и цифры то кидаем  Эксепшн
        if (filter.getCity().isEmpty() || filter.getCity() == null ||
                !filter.getCity().matches("^[а-яА-ЯёЁa-zA-Z0-9]+$")) {
            throw new Exception("field city contains invalid data");
        }
        return true;
    }


    }

