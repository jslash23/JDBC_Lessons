package hibernate.lesson4.hw1.repository;

import hibernate.lesson4.hw1.model.Filter;
import hibernate.lesson4.hw1.model.Hotel;
import hibernate.lesson4.hw1.model.Room;
import hibernate.lesson4.hw1.model.UserType;

import java.io.*;
import java.util.ArrayList;

public class RoomRepository {
    public static ArrayList<Room> findRooms = new ArrayList<>();

    private static Filter filter = new Filter(0, 0, false, false, null,
            null, null);


    public static ArrayList<Room> findRooms(Filter filter, String pathRooms, String pathReqRooms) throws Exception {
        //нам нужно пребразовать входные данные в фильтр (в объект Filter)
        //вызовем метод findHotelByCity и найдем гостиницу по городу
        //получив список гостиниц найденных по городу мы их
        // вернем.
        //Теперь если совпадает ай ди отеля который мы нашли с ай ди отеля в Room
        // Сверим наши поля в фильтре с полями  румов
        //если все поля совпадают то вернем комнаты

        Filter filter1 = writeToFilter(readFromFile(pathReqRooms));//Маппинг запроса комнат в объект Фильтр
        System.out.println(filter1);
        //нужно из файла Рум замапить строки в объект Рум
        String hotelsPath = "C:/Users/Пользователь/Desktop/hibernate.lesson4.hw1/HotelDb.txt";

        //найдем отели по городу
        ArrayList<Hotel> hotelsByCity = HotelRepository.findHotelByCity(filter1.getCity(), hotelsPath);

        //Взять результат вывода всех отелей по имени
        //Сверить у каждый отель по id с каждой строкой комнат по id
        //для этого нам нужно все румы из roomDb.txt замапить в ArrayList<Room>

        //Замапим наши комнаты из файла
        ArrayList<Room> allRooms = writeToRooms(readFromFile(pathRooms));

        //найдем наши комнаты по ай ди
        return findRoomsId(hotelsByCity, allRooms);
    }

    //Получим список комнат которые есть в отелях hotels
    private static ArrayList<Room> findRoomsId(ArrayList<Hotel> hotels, ArrayList<Room> rooms) {
        ArrayList<Room> roomsMap = new ArrayList<>();

        try {
            for (Hotel hotel : hotels) {
                for (Room room : rooms) {
                    Long hotId = hotel.getId();
                    Long roomId = room.getHotel().getId();
                    if (hotId.equals(roomId)) {
                        if (compareRoomsWithFilter(room, filter)) {
                            roomsMap.add(room);
                        }
                    }
                }
                //System.out.println("hotels:  " + hotel);
            }
        } catch (Exception e) {
            System.err.println("Error");
        }
        return roomsMap;
    }

    private static boolean compareRoomsWithFilter(Room room, Filter filter) {

        Integer guests = room.getNumbersOfGuests();
        Double price = room.getPrice();
        boolean breakfast = room.isBreakfastIncluded();
        boolean pets = room.isPetsAllowed();
        String dateAvailable = room.getDateAvableFrom();

        if (guests.equals(filter.getNumbersOfGuests()) && price.equals(filter.getPrice()) &&
                dateAvailable.equals(filter.getDateAvableFrom())) {
            if (breakfast == filter.isBreakfastIncluded() && pets == filter.isPetsAllowed()) {
                return true;
            }
        }

        return false;
    }

    //Маппинг данных из текстового файла requestRooms в объект фильтр


    //тут нужно использовать мап иначе затирается предыдущий объект хостел
    private static StringBuffer readFromFile(String path) throws Exception {//

        StringBuffer res = new StringBuffer();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = br.readLine()) != null) {
                res.append(line);//тут возможно не целый файл а записана какая то ерунда это нужно проверить
                res.append("\n");
            }
        } catch (IOException e) {
            System.err.println("Reading from file " + path + " failed");
        }
        return res;
    }


    //Нужно перевести входящий объект в строку  и записать в файл

    private static ArrayList<Room> writeToRooms(StringBuffer contentToWrite) {
        ArrayList<Room> rooms = new ArrayList<>();

        Room room1 = new Room(0, 0, 0, false, false,
                null, new Hotel(0, "", "", "", ""));

        Room room2 = new Room(0, 0, 0, false, false,
                null, new Hotel(0, "", "", "", ""));

        rooms.add(room1);
        rooms.add(room2);

        String convString = contentToWrite.toString();//это входящий объект

        String[] lines = convString.trim().split(",");
        int i = 0;

        for (int j = 0; j < rooms.size() - 1; j++) {
            long id = (UserRepository.CreateId(0, 200));

            rooms.get(j).setId(Long.parseLong(lines[i].trim()));
            i++;

            rooms.get(j).setNumbersOfGuests((Integer.parseInt(lines[i].trim())));
            i++;

            rooms.get(j).setPrice(Double.parseDouble(lines[i].trim()));
            i++;

            //validate fild breakfastIncluded проверка значения false или true
            try {
                if (!lines[i].isEmpty() && lines[i].trim().equals("true")) {
                    rooms.get(j).setBreakfastIncluded(true);
                } else if (!lines[i].isEmpty() && lines[i].trim().equals("false")) {
                    rooms.get(j).setBreakfastIncluded(false);
                }
            } catch (Exception e) {
                System.err.println("Fild breakfastIncluded  contains invalid data");
            }
            i++;

            //validate fild petsAllowed проверка значения false или true
            try {
                if (!lines[i].isEmpty() && lines[i].trim().equals("true")) {
                    rooms.get(j).setPetsAllowed(true);
                } else if (!lines[i].isEmpty() && lines[i].trim().equals("false")) {
                    rooms.get(j).setPetsAllowed(false);
                }
            } catch (Exception e) {
                System.err.println("Fild petsAllowed contains invalid data");
            }
            i++;

            rooms.get(j).setDateAvableFrom(lines[i].trim());
            i++;

            rooms.get(j).getHotel().setId(Long.parseLong(lines[i].trim()));
            i++;

        }
        return rooms;
    }

    //Почему программа заменяет файлы в колекции АрейЛист HotelMap?
    private static Filter writeToFilter(StringBuffer contentToWrite) throws Exception {

        String convString = contentToWrite.toString();
        String[] lines = convString.trim().split(",");

        int i = 0;
        //for (int j = 0; j < 2; j++) {

        //validate fild numberOfGuests проверка на целые числа
        try {
            if (!lines[i].isEmpty()) {
                filter.setNumbersOfGuests(Integer.parseInt(lines[i]));
            }
        } catch (Exception e) {
            System.err.println("Fild numberOfGuest contains invalid data");
        }
        i++;

        //validate fild price проверка на целые числа и числа с плавающей точкой
        try {//"^[\\-?\\d+(\\.\\d{0,})?\n ]+$"
            if (!lines[i].isEmpty()) {
                filter.setPrice(Double.parseDouble(lines[i]));
            }
        } catch (Exception e) {
            System.err.println("Fild price contains invalid data");
        }
        i++;

        //validate fild breakfastIncluded проверка значения false или true
        try {
            if (!lines[i].isEmpty() && lines[i].trim().equals("true")) {
                filter.setBreakfastIncluded(true);
            } else if (!lines[i].isEmpty() && lines[i].trim().equals("false")) {
                filter.setBreakfastIncluded(false);
            }
        } catch (Exception e) {
            System.err.println("Fild breakfastIncluded  contains invalid data");
        }
        i++;

        //validate fild petsAllowed проверка значения false или true
        try {
            if (!lines[i].isEmpty() && lines[i].trim().equals("true")) {
                filter.setPetsAllowed(true);
            } else if (!lines[i].isEmpty() && lines[i].trim().equals("false")) {
                filter.setPetsAllowed(false);
            }
        } catch (Exception e) {
            System.err.println("Fild petsAllowed contains invalid data");
        }
        i++;

        //validate fild dateAvailableFrom проверка значения даты Дата в формате DD/MM/YYYY:/  HH:MM:SS !!!!!!!!!!!!!!!!!!!!
        try {
            if (!lines[i].isEmpty()) {

                filter.setDateAvableFrom(lines[i].trim());
            }
        } catch (Exception e) {
            System.err.println("Fild dateAvailableFrom contains invalid data");
        }
        i++;


        //validate fild country проверка на целые Английские буквы
        try {
            if (!lines[i].isEmpty() && lines[i].trim().matches("^[\\D]+$")) {
                filter.setCountry(lines[i].trim());
            }
        } catch (Exception e) {
            System.err.println("Fild numberOfGuest contains invalid data");
        }
        i++;

        //validate fild city проверка на целые Английские буквы    matches("^[a-zA-Z]+$")*/)
        try {//{
            if (!lines[i].isEmpty() && lines[i].trim().matches("^[\\D]+$")) {
                filter.setCity(lines[i].trim());
            }
        } catch (Exception e) {
            System.err.println("Fild city contains invalid data");
        }
        return filter;
    }

    // method for add rooms if user is admin
    public static void addRoom(Room room, String pathRooms, UserType userType) throws Exception {


        //запишем комнаты в репозиторий
        //применим try с ресурсами

        //1.преобразуем входные данные объекта Room в стринг
        //сгенерируем новый айдишник
        //2.запишем наш стринг в файл RoomDb.txt

        //StringBuffer res = new StringBuffer();
        try (BufferedWriter br = new BufferedWriter(new FileWriter(pathRooms, true))) {

            //Сгенерируем новый айдишник и запишем его в StringBuffer а
            // StringBuffer запишется в userDb.txt

            //Room room1 = new Room(0,1,100, true, true, "02.05.2020",
            //kievPlaza);

            try {
                //сгенерируем айдишник и запишем его в файл
                long id = (UserRepository.CreateId(0, 200));
                br.append(Long.toString(id));//запишем новый id
                br.append(", ");

                String newRoom =  room.toString();  //////////////////////////
                br.append(newRoom);   //////////////////////////////////////

                /*br.append(Integer.toString(room.getNumbersOfGuests()));
                br.append(", ");

                br.append(String.valueOf(room.getPrice()));
                br.append(", ");

                br.write(String.valueOf(room.isBreakfastIncluded()));
                br.append(", ");

                br.append(String.valueOf(room.isPetsAllowed()));
                br.append(", ");


                br.append(room.getDateAvableFrom());
                br.append(", ");

                br.append(Long.toString(room.getHotel().getId()));
                br.append(", ");*/
                br.append("\n");


            } catch (Exception e) {
                System.err.println("Can't write to file");
            }
            System.out.println("New room was added");

        }
    }


    public static void deleteRoom(long roomId, String path, UserType usType) throws Exception {
        delRoom(roomId, path, usType);
    }


    private static void delRoom(long roomId, String path, UserType userType) throws Exception {

        //замапим данные из файла RoomDb.txt
        //в них найдем комнаты по id, И  удалим  эти комнаты из файла RoomDb.txt

        //1.Считаем строку из текстового файла нашей БД (RoomDb.txt)
        //2.Замапим данные в объект Room
        //3.Сравним полученные данные с входными данными (long roomId)
        //если они сходятся то удалим нужную строку из БД
        //иначе выведем эксепшн такой комнаты нет
        //


        //найдем наши комнаты по ай ди


        writeToRoom(readFromFile(path), roomId, path);
    }


    private static void writeToRoom(StringBuffer contentToWrite, long roomId, String roomPath) throws Exception {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(roomPath, false))) {//append


            String convString = contentToWrite.toString();
            String[] linesJ = convString.trim().split("\n");


            String[] lines = convString.trim().split(",");
            String rId = (Long.toString(roomId));

            ///////////////////////////////////////////////////////////////////////////////////////////
            for (String ln : linesJ) {
                if (!ln.contains(rId)) {
                    br.append(ln);
                }
            }
        }
    }

}


           /* int i = 0;

            System.out.println(linesJ.length);
            for (int j = 0; j < linesJ.length;) {
                if ((!lines[i].isEmpty() && !lines[i].trim().equals(rId))) {
                    i = writeNugnieRoomsToDb(br, lines, i, j);

                    br.append("\n");
                } else {
                    i = i + 7;
                }
                j++;
            }
            System.out.println("Rooms were canceled");
        }
    }*/


    /*private static int writeNugnieRoomsToDb(BufferedWriter br, String[] lines, int i, int
            j) throws Exception {
    //Room room1 = new Room(0,1,100, true, true, "02.05.2020", kievPlaza);
        br.append(lines[i].trim());//room id
        br.append(", ");
        i++;

        br.append(lines[i].trim());//numbers of guests
        br.append(", ");
        i++;

        br.append(lines[i].trim());//price
        br.append(", ");
        i++;

        br.append(lines[i].trim());//breacfast included
        br.append(", ");
        i++;

        br.append(lines[i].trim());//pets allowed
        br.append(", ");
        i++;

        br.append(lines[i].trim());//date avaible from
        br.append(", ");
        i++;

        br.append(lines[i].trim());//hotel
        br.append(", ");
        i++;

        return i;
    }*/
