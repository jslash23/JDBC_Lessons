package hibernate.lesson3HW;

import java.util.Date;

public class Demo {
    public static void main(String[] args) {
        RoomDAO roomDAO = new RoomDAO();
        HotelDAO hotelDAO = new HotelDAO();

        Room room1 = new Room();
        Room room2 = new Room();

        Hotel hotel0 = new Hotel();
        Hotel hotel1 = new Hotel();


        hotel0.setName("");
        hotel0.setCountry("");
        hotel0.setCity("");
        hotel0.setStreet("");

        hotel1.setName("KievPlaza");
        hotel1.setCountry("Ukraine");
        hotel1.setCity("Kiev");
        hotel1.setStreet("Hreshatik");

        Hotel hotel2 = new Hotel();

        hotel1.setName("Stolica");
        hotel1.setCountry("Ukraine");
        hotel1.setCity("Kiev");
        hotel1.setStreet("Pechersk");


        room1.setHotel(hotel1);
        room1.setNumberOfGuests(2);
        room1.setPrice(500);
        room1.setBreakfastIncluded(1);
        room1.setPetsAllowed(1);
        room1.setDateAvailableFrom(new Date());


        room2.setId(31L);
        room2.setHotel(hotel2);
        room2.setNumberOfGuests(1);
        room2.setPrice(700);
        room2.setBreakfastIncluded(0);
        room2.setPetsAllowed(0);
        room2.setDateAvailableFrom(new Date());


      // hotelDAO.saveAll(hotel1);
       //roomDAO.saveAll(room1);

        //System.out.println(hotelDAO.findById(20L));

        //System.out.println(roomDAO.findById(26L));
       // roomDAO.delete(26L);
        //roomDAO.deleteAllRooms();
        //hotelDAO.deleteAllHotels();
         roomDAO.update(room2);

    }
}
