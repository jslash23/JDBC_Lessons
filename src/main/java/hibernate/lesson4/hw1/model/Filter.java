package hibernate.lesson4.hw1.model;

public class Filter {


    private int numbersOfGuests;
    private double price;
    private boolean breakfastIncluded;
    private boolean petsAllowed;
    private String dateAvableFrom;

    //вместо поля   private Hotel hotel пишем
    private String country;
    private String city;

    public Filter(int numbersOfGuests, double price, boolean breakfastIncluded,
                  boolean petsAllowed, String dateAvableFrom, String country, String city) {
        this.numbersOfGuests = numbersOfGuests;
        this.price = price;
        this.breakfastIncluded = breakfastIncluded;
        this.petsAllowed = petsAllowed;
        this.dateAvableFrom = dateAvableFrom;
        this.country = country;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Filter{" +
                "numbersOfGuests=" + numbersOfGuests +
                ", price=" + price +
                ", breakfastIncluded=" + breakfastIncluded +
                ", petsAllowed=" + petsAllowed +
                ", dateAvableFrom=" + dateAvableFrom +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public int getNumbersOfGuests() {
        return numbersOfGuests;
    }

    public double getPrice() {
        return price;
    }

    public boolean isBreakfastIncluded() {
        return breakfastIncluded;
    }

    public boolean isPetsAllowed() {
        return petsAllowed;
    }

    public String getDateAvableFrom() {
        return dateAvableFrom;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public void setNumbersOfGuests(int numbersOfGuests) {
        this.numbersOfGuests = numbersOfGuests;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBreakfastIncluded(boolean breakfastIncluded) {
        this.breakfastIncluded = breakfastIncluded;
    }

    public void setPetsAllowed(boolean petsAllowed) {
        this.petsAllowed = petsAllowed;
    }

    public void setDateAvableFrom(String dateAvableFrom) {
        this.dateAvableFrom = dateAvableFrom;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
