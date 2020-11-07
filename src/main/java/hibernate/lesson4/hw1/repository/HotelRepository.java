package hibernate.lesson4.hw1.repository;

import hibernate.lesson4.hw1.model.Hotel;
import hibernate.lesson4.hw1.model.UserType;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class HotelRepository {

    //считываем данные,
    //преобразуем данные в объект
    // мапим в объект

    //находим гостиницу по имени
    //возвращаем объект

    public static ArrayList<Hotel> hotelsByName = new ArrayList<>();
    public static ArrayList<Hotel> hotelsByCity = new ArrayList<>();


    public static ArrayList<Hotel> findHotelByName(String name, String path) throws Exception {
          String flag = "n";
        hotelsByName = writeToFile(readFromFile(path), name, flag);

        System.out.println(hotelsByName);
        return hotelsByName;
    }

    public static ArrayList<Hotel> findHotelByCity(String city, String path) throws Exception {
        String flag = "s";

        hotelsByCity = writeToFile(readFromFile(path), city, flag);
        //System.out.println(hotelsByCity)
        return hotelsByCity;
    }


    public static Integer CreateId(Integer min, Integer max) {
        int diff = max - min;
        Random random = new Random();
        int i = random.nextInt(diff + 1);
        i += min;
        return i;
    }


    //тут нужно использовать мап иначе затирается предыдущий объект хостел
    private static StringBuffer readFromFile(String path) throws Exception {//

        StringBuffer res = new StringBuffer();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = br.readLine()) != null) {
                res.append(line);//тут возможно не целый файл а записана какая то ерунда это нужно проверить
                res.append("\n");
            }
            //Уберем добавление пустой строки в последнем шаге цикла  while
            //метод позволяет в Java заменить символы в строке между указанными начальным и конечным индексами
            if (res.length() > 0){
                res.replace(res.length()-1, res.length(), "");
            }
        } catch (IOException e) {
            System.err.println("Reading from file " + path + " failed");
        }
        return res;
    }


    //Почему программа заменяет файлы в колекции АрейЛист HotelMap?
    private static ArrayList<Hotel> writeToFile(StringBuffer contentToWrite, String name, String flag) throws Exception {

        ArrayList<Hotel> hotels = new ArrayList<>();
        Hotel hotel1 = new Hotel(00, "", "", "", "");
        Hotel hotel2 = new Hotel(00, "", "", "", "");
        Hotel hotel3 = new Hotel(00, "", "", "", "");

        hotels.add(hotel1);
       hotels.add(hotel2);
        hotels.add(hotel3);

        ArrayList<Hotel> HotelMap = new ArrayList<>();

        String convString = contentToWrite.toString();
        String[] myList = convString.trim().split(",");
int i = 0;


for (int k = 0; k < 2  ; k++) {//у нас 2-ве записи в таблице значит у нас два пустых отеля

    // validate fild id проверка на целые числа типа long

            hotels.get(k).setId(Long.parseLong(myList[i].trim()));
    i++;

    // validate fild name  проверка на текстовое значение

            hotels.get(k).setName(myList[i]);
    i++;

    // validate fild country проверка на текстовое значение

            hotels.get(k).setCountry(myList[i]);
    i++;

    // validate fild city проверка на текстовое значение

            hotels.get(k).setCity(myList[i]);
    i++;


    // validate fild street проверка на текстовое значение

            hotels.get(k).setStreet(myList[i]);
    i++;
    //hotels.add(hotels.get(k));
}

        for (Hotel hotel : hotels) {
            if (findStringByCityOrName(hotel, name, flag)) {
                HotelMap.add(hotel);
            }
        }
        return HotelMap;

    }


    //делаем из стринга массив стрингов
    //каждый стринг преобразуем в нужное значение


    private static Boolean findStringByCityOrName(Hotel HotelMap, String name, String flag) throws Exception {

        if (flag.equals("s")){
            if (HotelMap.getCity().contains(name)) {
                return true;
            }
        }
        else  if (flag.equals("n")){
            if (HotelMap.getName().contains(name)) {
                return true;
            }
        }
        return false;
    }

    public  static  void addHotel(Hotel hotel, String path, UserType userType) throws Exception{
        //запишем отели в репозиторий
        //применим try с ресурсами

        //1.преобразуем входные данные объекта Hotel в стринг
        //сгенерируем новый айдишник
        //2.запишем наш стринг в файл HotelDb.txt

        //StringBuffer res = new StringBuffer();
        try (BufferedWriter br = new BufferedWriter(new FileWriter(path, true))) {

            //Сгенерируем новый айдишник и запишем его в StringBuffer а
            // StringBuffer запишется в hotelDb.txt

            //Room room1 = new Room(0,1,100, true, true, "02.05.2020",
            //kievPlaza);

            try {
                //сгенерируем айдишник и запишем его в файл
                long id = (HotelRepository.CreateId(0, 200));
                br.append(Long.toString(id));//Hotel id
                br.append(", ");

                br.write(hotel.getName());// Hotel name
                br.append(", ");

                br.write(String.valueOf(hotel.getCountry()));//Hotel country
                br.append(", ");

                br.append(String.valueOf(hotel.getCity()));//Hotel city
                br.append(", ");

                br.append(String.valueOf(hotel.getStreet()));//Hotel street
                br.append(", ");

                br.append("\n");


            } catch (Exception e) {
                System.err.println("Can't write to file");
            }
            System.out.println("New hotels was added");
        }

    }


    public static void deleteHotel(long hotelId, String pathHotels, UserType usType) throws Exception {
        delHotel(hotelId, pathHotels, usType);
    }

    private static void delHotel(long hotelId, String pathHotels, UserType usType) throws Exception {
        //замапим данные из файла HotelDb.txt
        //в них найдем отели по id, И  удалим  эти отели из файла HotelDb.txt

        //1.Считаем строку из текстового файла нашей БД (HotelDb.txt)
        //2.Замапим данные в объект Hotel
        //3.Сравним полученные данные с входными данными (long roomId)
        //если они сходятся то удалим нужную строку из БД
        //иначе выведем эксепшн такой комнаты нет
        //


        //найдем наши комнаты по ай ди

        writeToHotel(readFromFile(pathHotels), hotelId, pathHotels);
    }
    private static void writeToHotel(StringBuffer contentToWrite, long hotelId, String pathHotels) throws Exception {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(pathHotels, false))) {//append


            String convString = contentToWrite.toString();
            String[] linesJ = convString.trim().split("\n");
            String[] lines = convString.trim().split(",");
            System.out.println(lines.length);
            String hId = (Long.toString(hotelId));
            int i = 0;

            System.out.println(linesJ.length);
            for (int j = 0; j < linesJ.length;) {
                    if ((!lines[i].isEmpty() && !lines[i].trim().equals(hId))) {
                        i = writeNugnieHotelsToDb(br, lines, i, j);
                        br.append("\n");
                    } else {
                        i = i + 5;
                    }
                    j++;
            }
            System.out.println("Hotels were canceled");
        }
    }


    private static int writeNugnieHotelsToDb(BufferedWriter br, String[] lines, int i, int
    j) throws Exception {
        //Room room1 = new Room(0,1,100, true, true, "02.05.2020", kievPlaza);
        br.append(lines[i].trim());//Hotel id
        br.append(", ");
        i++;

        br.append(lines[i].trim());//Hotel name
        br.append(", ");
        i++;

        br.append(lines[i].trim());//Hotel city
        br.append(", ");
        i++;

        br.append(lines[i].trim());//Hotel country
        br.append(", ");
        i++;

        br.append(lines[i].trim());//Hotel street
        br.append(", ");
        i++;

        return i;
    }

}


