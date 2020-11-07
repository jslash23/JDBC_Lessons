package hibernate.lesson4.hw1.repository;

import java.io.*;


public class OrderRepository {
    public static void bookRoom(long roomId, long userId, String dateFrom, String dateTo, String order) throws InterruptedException {
        bookRooms(roomId, userId, dateFrom, dateTo, order);

    }

    private static void bookRooms(long roomId, long userId, String dateFrom, String dateTo, String order) {


        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(order, true))) {

            bufferedWriter.append(Long.toString(roomId));
            bufferedWriter.append(",");
            bufferedWriter.append(Long.toString(userId));
            bufferedWriter.append(",");
            bufferedWriter.append(dateFrom);
            bufferedWriter.append(",");
            bufferedWriter.append(dateTo);
            bufferedWriter.append(",");
            bufferedWriter.append("\n");
            System.out.println("New orders booked");
        } catch (IOException e) {
            System.err.println("Can't write to file");
        }
    }


    public static void cancelReservation(long roomId, long userId, String order) throws Exception {
        cancelReserv(roomId, userId, order);
    }

    private static void cancelReserv(long roomId, long userId, String order) throws Exception {
        //найдем наши комнаты для отмены брони, удалим заказ

        //замапим данные из файла ОrderDb.txt
        //в них найдем комнаты, пользователя и если есть удалим  этот заказ из файла orderDb.txt

        //1.Считаем строку из текстового файла нашей БД (ОrderDb.txt)
        //2.Замапим данные в объект Order
        //3.Сравним полученные данные с входными данными (long roomId, long userId,)
        //если они сходятся то удалим нужную строку из БД
        //иначе выведем эксепшн такого заказа нет
        //

        writeToOrder(readFromFile(order), roomId, userId, order);
    }

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

    private static void writeToOrder(StringBuffer contentToWrite, long roomId, long userId, String orderPath) throws Exception {
        //Нужно записать  данные в OrderDb.txt
       /* ArrayList<Order> orders = new ArrayList<>();
        Order order1 = new Order(0, new User(0, "", "", "", UserType.USER, false),
                new Room(0, 0, 0, false, false, "",
                        new Hotel(0, "", "", "", "")), "", "", 0);

        Order order2 = new Order(0, new User(0, "", "", "", UserType.USER, false),
                new Room(0, 0, 0, false, false, "",
                        new Hotel(0, "", "", "", "")), "", "", 0);

        Order order3 = new Order(0, new User(0, "", "", "", UserType.USER, false),
                new Room(0, 0, 0, false, false, "",
                        new Hotel(0, "", "", "", "")), "", "", 0);

        orders.add(order1);
        orders.add(order2);*/

        StringBuffer res = new StringBuffer();
        try (BufferedWriter br = new BufferedWriter(new FileWriter(orderPath, false))) {//append


            String convString = contentToWrite.toString();
            String[] linesJ = convString.trim().split("\n");
            String[] lines = convString.trim().split(",");
            String rId = (Long.toString(roomId));
            String usId = (Long.toString(userId));
            int i = 0;

            System.out.println(linesJ.length);
            for (int j = 0; j < linesJ.length; ) {


                if ((!lines[i].isEmpty() && !lines[i].trim().equals(rId)) && (!lines[i+1].isEmpty()
                        && !lines[i+1].trim().equals(usId))) {
                    i = writeNugnieOrdersToDb(/*orders,*/ br, lines, i, j);
                    br.append("\n");
                } else {
                    i = i + 6;
                }
                j++;
            }
            System.out.println("Order was canceled");
        }
    }

    private static int writeNugnieOrdersToDb(/*ArrayList<Order> orders,*/ BufferedWriter br, String[] lines, int i, int
            j) throws Exception {

        //orders.get(j).setId(Long.parseLong(lines[i].trim()));//trim("\n")

        //br.append(Long.toString(orders.get(j).getId()));
        br.append(lines[i].trim());
        br.append(", ");
        i++;

        //записать параметры значений
        /*orders.get(j).getUser().setId(Long.parseLong(lines[i].trim()));
        br.append(Long.toString(orders.get(j).getUser().getId()));*/
        br.append(lines[i].trim());
        //нужно полностью прописывать параметры юзера или писать сокращенно
        br.append(", ");
        i++;

        //Add date to to BufferedWriter br
        br.append(lines[i].trim());
        br.append(", ");
        i++;

        br.append(lines[i].trim());
        br.append(", ");
        i++;


        //add date from to BufferedWriter br
        br.append(lines[i].trim());
        br.append(", ");
        i++;

        br.append(lines[i].trim());
        br.append(", ");
        i++;
       return i;
    }
}









