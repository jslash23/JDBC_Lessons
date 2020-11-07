package hibernate.lesson4.hw1;
import javax.persistence.*;

@Entity
@Table(name = "HOTEL_PR" )

public class Hotel {
    private long id;
    private String name;
    private String country;
    private String city;
    private String street;


    //@OneToOne(optional = false, mappedBy = "hotel")

    //Поле id
    @Id/////

    //////name = "HOTEL_N_SEQ" мы сами придумали, sequenceName = "HOTEL_SEQ" взяли из БД
    @SequenceGenerator(name = "HOTEL_N_SEQ", sequenceName = "HOTELPR_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HOTEL_N_SEQ")
    //@OneToOne(optional = false, mappedBy = "hotel")
    @Column(name = "id")
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
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
