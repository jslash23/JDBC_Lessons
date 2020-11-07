package hibernate.lesson4.hw1.demo;

import hibernate.lesson4.hw1.controller.RoomController;
import hibernate.lesson4.hw1.model.Filter;
import hibernate.lesson4.hw1.model.Hotel;
import hibernate.lesson4.hw1.model.Room;
import hibernate.lesson4.hw1.model.UserType;

import java.util.ArrayList;


public class DemoRoom {
    public static void main(String[] args) throws Exception {
        Filter newf = new Filter(0,0,false,false,
                " "," "," ");
        Hotel kievPlaza = new Hotel(0,"KievPlaza","Ukraine","Kiev","Hreshatik");
        ArrayList<Hotel>  hotels = new ArrayList<>();
        hotels.add(kievPlaza);
        Room room1 = new Room(0,1,500, true, true, "02.05.2020",
                kievPlaza);
        long roomId = 46;

        /*  private long id;
        private String name;
        private String country;
        private String city;
        private  String street;*/


        String roomsPath = "C:/Users/Пользователь/Desktop/hibernate.lesson4.hw1/roomsDb.txt";
        String reqRooms = "C:/Users/Пользователь/Desktop/hibernate.lesson4.hw1/requestRooms.txt";

        System.out.println("Rooms with you parameters:  " + RoomController.findRooms(newf, roomsPath, reqRooms));

        //Нужно добавить нашу сущность room1 в репозиторий roomsPath
        RoomController.addRoom(room1, roomsPath, UserType.ADMIN);
        //RoomController.deleteRoom(roomId, roomsPath, UserType.ADMIN);
        //RoomController.addRoom(room1, roomsPath, UserType.USER);
        //RoomController.deleteRoom(roomId, roomsPath, UserType.USER);
    }
}
