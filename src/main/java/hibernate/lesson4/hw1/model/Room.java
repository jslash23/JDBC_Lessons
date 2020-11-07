package hibernate.lesson4.hw1.model;

public class Room  {

    private long id;
    private int numbersOfGuests;
    private double price;
    private boolean breakfastIncluded;
    private boolean petsAllowed;
    private String dateAvableFrom;
    private Hotel hotel;

    public Room(long id, int numbersOfGuests, double price, boolean breakfastIncluded, boolean petsAllowed,
                String dateAvableFrom, Hotel hotel) {
        this.id = id;
        this.numbersOfGuests = numbersOfGuests;
        this.price = price;
        this.breakfastIncluded = breakfastIncluded;
        this.petsAllowed = petsAllowed;
        this.dateAvableFrom = dateAvableFrom;
        this.hotel = hotel;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumbersOfGuests() {
        return numbersOfGuests;
    }

    public void setNumbersOfGuests(int numbersOfGuests) {
        this.numbersOfGuests = numbersOfGuests;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isBreakfastIncluded() {
        return breakfastIncluded;
    }

    public void setBreakfastIncluded(boolean breakfastIncluded) {
        this.breakfastIncluded = breakfastIncluded;
    }

    public boolean isPetsAllowed() {
        return petsAllowed;
    }

    public void setPetsAllowed(boolean petsAllowed) {
        this.petsAllowed = petsAllowed;
    }

    public String getDateAvableFrom() {
        return dateAvableFrom;
    }

    public void setDateAvableFrom(String dateAvableFrom) {
        this.dateAvableFrom = dateAvableFrom;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", numbersOfGuests=" + numbersOfGuests +
                ", price=" + price +
                ", breakfastIncluded=" + breakfastIncluded +
                ", petsAllowed=" + petsAllowed +
                ", dateAvableFrom='" + dateAvableFrom + '\'' +
                ", hotel=" + hotel +
                '}';
    }
}







