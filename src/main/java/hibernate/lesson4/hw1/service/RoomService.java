package hibernate.lesson4.hw1.service;

import hibernate.lesson4.hw1.model.Filter;
import hibernate.lesson4.hw1.model.Room;
import hibernate.lesson4.hw1.model.UserType;
import hibernate.lesson4.hw1.repository.RoomRepository;

import java.util.ArrayList;

public class RoomService {
    private RoomRepository roomRepository = new RoomRepository();

    public ArrayList<Room> findRooms(Filter filter, String rooms, String reqRooms) throws Exception {
        //if ((validateCity(filter)))  не работает
        return RoomRepository.findRooms(filter, rooms, reqRooms);
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

    public void addRoom(Room room, String path, UserType usType) throws Exception {
        //если юзер у нас является админом то мы выполняем добавление комнаты,
        //иначе не добавляем

            if (usType.equals(UserType.ADMIN)) {
                RoomRepository.addRoom(room, path, usType);
            }
        else
            System.err.println("You havn't rights for modify repository");
    }
    public void deleteRoom(long roomId, String path, UserType usType) throws Exception{
        //если юзер у нас является админом то мы выполняем добавление комнаты,
        //иначе не добавляем

            if (usType.equals(UserType.ADMIN)) {
                RoomRepository.deleteRoom(roomId, path, usType);
            }
        else
            System.err.println("You havn't rights for modify repository");
        }
    }

