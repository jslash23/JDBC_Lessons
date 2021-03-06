package hibernate.lesson4.hw1.service;

import hibernate.lesson4.hw1.dao.RoomDAO;
import hibernate.lesson4.hw1.model.*;

import java.util.List;

public class RoomService {

    final static RoomDAO roomDAO = new RoomDAO();

    public List<Room> findRooms(Filter filter) throws Exception {

        return roomDAO.findRooms(filter);

    }


    private boolean validateCity(Filter filter) throws Exception {
        if (filter.getCity().isEmpty() || filter.getCity() == null ||
                !filter.getCity().matches("^[а-яА-ЯёЁa-zA-Z0-9]+$")) {
            throw new Exception("field city contains invalid data");
        }
        return true;
    }


}

