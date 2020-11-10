package hibernate.lesson4.hw1;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ROOM_PR")

public class Roomn {

    //Поле id
    @Id/////

    //////name = "ROOMPR_N_SEQ" мы сами придумали, sequenceName = "ROOMPR_SEQ" взяли из БД
    @SequenceGenerator(name = "ROOM_N_SEQ", sequenceName = "ROOMPR_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROOM_N_SEQ")

    private long id;
    private int numberOfGuests;
    private double price;
    private int breakfastIncluded;
    private int petsAllowed;
    private Date dateAvailableFrom;
    private Hoteln hoteln;


    //@GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    //we use association @ManyToOne with using generics
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HOTELPR_FK", nullable = false)

    public Hoteln getHoteln() {return hoteln;}
    public void setHoteln(Hoteln hoteln) {
        this.hoteln = hoteln;
    }

    //Поле nameOfGuests
    @Column(name = "NUMBEROFGUESTS")
    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }


    //Поле price
    @Column(name = "PRICE")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    //ПолеBreakfastIncluded
    @Column(name = "BREAKFASTINCLUDED")
    public int getBreakfastIncluded() {
        return breakfastIncluded;
    }

    public void setBreakfastIncluded(int breakfastIncluded) {
        this.breakfastIncluded = breakfastIncluded;
    }


    //Поле PetsAllawed
    @Column(name = "PETSALLOWED")
    public int getPetsAllowed() {
        return petsAllowed;
    }

    public void setPetsAllowed(int petsAllowed) {
        this.petsAllowed = petsAllowed;
    }


    //Поле DateAvailableFrom
    @Column(name = "DATEAVAILABLEFROM")
    @Temporal(value = TemporalType.DATE)
    public Date getDateAvailableFrom() {
        return dateAvailableFrom;
    }

    public void setDateAvailableFrom(Date dateAvailableFrom) {
        this.dateAvailableFrom = dateAvailableFrom;
    }

    //Поле Hoteln ссылающееся на класс Hoteln

    @Override
    public String toString() {
        return "Roomn{" +
                "id=" + id +
                ", numberOfGuests=" + numberOfGuests +
                ", price=" + price +
                ", breakfastIncluded=" + breakfastIncluded +
                ", petsAllowed=" + petsAllowed +
                ", dateAvailableFrom=" + dateAvailableFrom +
                ", hoteln=" + hoteln +
                '}';
    }
}
