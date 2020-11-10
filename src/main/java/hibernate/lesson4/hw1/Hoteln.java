package hibernate.lesson4.hw1;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "HOTEL_PR" )

public class Hoteln {


    //Поле id
    @Id/////
    //////name = "HOTEL_N_SEQ" мы сами придумали, sequenceName = "HOTEL_SEQ" взяли из БД
    @SequenceGenerator(name = "HOTEL_N_SEQ", sequenceName = "HOTELPR_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HOTEL_N_SEQ")


    private long id;
    private String name;
    private String country;
    private String city;
    private String street;
    private List<Roomn> roomns;//hear we have Set in @OneToMany  but in Task List rooms



    //we use association @OneToMany with using generics
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hotel")

    public List<Roomn> getRoomns()
    {return roomns;}

    public void setRoomns(List roomns){this.roomns = roomns;}

    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    //@GeneratedValue(strategy = GenerationType.AUTO)

    //Поле nameOfGuests name
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    //Поле country
    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    //Поле nameOfGuests city
    @Column(name = "CITY")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    //Поле street
    @Column(name = "STREET")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }


    @Override
    public String toString() {
        return "Hoteln{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", roomns=" + roomns +
                '}';
    }
}
