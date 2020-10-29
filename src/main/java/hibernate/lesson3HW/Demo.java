package hibernate.lesson3HW;

import java.util.Date;

public class Demo {
    public static void main(String[] args) {
        RoomDAO roomDAO = new RoomDAO();
        HotelDAO hotelDAO = new HotelDAO();

        Room room1 = new Room();

        Hotel hotel1 = new Hotel();

        hotel1.setName("KievPlaza");
        hotel1.setCountry("Ukraine");
        hotel1.setCity("Kiev");
        hotel1.setStreet("Hreshatik");

        room1.setHotel(hotel1);
        room1.setNumberOfGuests(2);
        room1.setPrice(500);
        room1.setBreakfastIncluded(1);
        room1.setPetsAllowed(1);
        room1.setDateAvailableFrom(new Date());

        //hotelDAO.saveAll(hotel1);
        roomDAO.saveAll(room1);


       // System.out.println(roomDAO.findById(10L));

    }
}
