package hibernate.lesson4.hw1.controller;

import hibernate.lesson4.hw1.model.Filter;
import hibernate.lesson4.hw1.model.Room;
import hibernate.lesson4.hw1.model.UserType;
import hibernate.lesson4.hw1.service.RoomService;

import java.util.List;

public class RoomController {

    private final static  RoomService roomService = new RoomService();//

    public List<Room> findRooms(Filter filter) throws Exception {
        //return userService.registerUser(user);//тут должна быть логика
        return roomService.findRooms(filter);
    }


}
