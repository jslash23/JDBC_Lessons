package hibernate.lesson4.hw1.model;

import java.util.Date;

public class Filter {


    private int numbersOfGuests;
    private double price;
    private boolean breakfastIncluded;
    private boolean petsAllowed;
    private Date dateAvableFrom;

    //вместо поля   private Hotel hotel пишем
    private String country;
    private String city;

    public Filter(int numbersOfGuests, double price, boolean breakfastIncluded,
                  boolean petsAllowed, Date dateAvableFrom, String country, String city) {
        this.numbersOfGuests = numbersOfGuests;
        this.price = price;
        this.breakfastIncluded = breakfastIncluded;
        this.petsAllowed = petsAllowed;
        this.dateAvableFrom = dateAvableFrom;
        this.country = country;
        this.city = city;
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

    public Date getDateAvableFrom() {
        return dateAvableFrom;
    }

    public void setDateAvableFrom(Date dateAvableFrom) {
        this.dateAvableFrom = dateAvableFrom;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
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


}
