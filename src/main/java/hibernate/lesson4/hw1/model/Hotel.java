package hibernate.lesson4.hw1.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "HOTELPR")

public class Hotel {

    private long id;
    private String name;
    private String country;
    private String city;
    private String street;
    private List<Room> rooms = new ArrayList<>();

    //we use association @OneToMany with using generics
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            targetEntity = Room.class, mappedBy = "hotel")//another table(ROOM_PR) can acces

    // to this table throw class hotel

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List rooms) {
        this.rooms = rooms;
    }

    //Поле id
    @Id
    @SequenceGenerator(name = "HOTEL_N_SEQ", sequenceName = "HOTELPR_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HOTEL_N_SEQ")

    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "CITY")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "STREET")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }


    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", rooms=" + rooms +
                '}';
    }
}
