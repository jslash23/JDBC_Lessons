package hibernate.lesson4.hw1;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "USERPR" )

public class Usern {

    private long id;
    private String name;
    private String password;
    private String country;
    private String userType;
    private List<Ordern> orderns;

    @Column(name = "ID")
    //Поле id
    @Id/////

    //////name = "USERPR_N_SEQ" мы сами придумали, sequenceName = "USERPR_SEQ" взяли из БД
    @SequenceGenerator(name = "USERPR_N_SEQ", sequenceName = "USERPR_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERPR_N_SEQ")

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    //we use association @OneToMany with using generics
    @OneToMany (mappedBy = "usern")
    //@JoinColumn(name = "ORDERPR_FK", nullable = false)
    public List<Ordern> getOrderns() {
        return orderns;
    }

    public void setOrderns(List<Ordern> orderns) {
        this.orderns = orderns;
    }



    //Поле nameOfGuests name
    @Column(name = "NAME")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    //Поле userName
    @Column(name = "PASSWORD")
    public String getPassword() {return password;}
    public  void setPassword(String password){this.password = password;}


    //Поле country
    @Column(name = "COUNTRY")
    public String getCountry() {return country;}
    public void setCountry(String country) {this.country = country;}


    //Поле userType
    @Column(name = "USERTYPE")
    public String getUserType() {return userType;}
    public void setUserType(String userType) {this.userType = userType;}

    @Override
    public String toString() {
        return "Usern{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", country='" + country + '\'' +
                ", userType='" + userType + '\'' +
                ", orderns=" + orderns +
                '}';
    }
}
