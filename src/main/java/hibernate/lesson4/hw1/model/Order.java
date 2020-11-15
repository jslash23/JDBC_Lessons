package hibernate.lesson4.hw1.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ORDERPR")

public class Order {

    private long id;
    private Date dateFrom;
    private Date dateTo;
    private double moneyPaid;
    private User userOrdered;
    private Room room;

    @Column(name = "ID")

    //Поле id
    @Id/////

    //////name = "ORDERPR_N_SEQ" мы сами придумали, sequenceName = "ORDERPR_SEQ" взяли из БД
    @SequenceGenerator(name = "ORDERPR_N_SEQ", sequenceName = "ORDERPR_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDERPR_N_SEQ")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    //we use association @ManyToOne with using generics
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERPR_FK", nullable = false)
    public User getUser() {return userOrdered;}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOMPR_FK", nullable = false)
    public Room getRoom() {return room;}

    @Column(name = "DATEFROM")
    public Date getDateFrom() {return dateFrom;}
    public void setDateFrom(Date dateFrom) {this.dateFrom = dateFrom;}

    @Column(name = "DATETO")
    public Date getDateTo() {return dateTo;}
    public void setDateTo(Date dateTo) {this.dateTo = dateTo;}


    @Column(name = "MONEYPAID")
    public double getMoneyPaid() {return moneyPaid;}
    public void setMoneyPaid(double moneyPaid) {this.moneyPaid = moneyPaid;}

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", moneyPaid=" + moneyPaid +
                ", userOrdered=" + userOrdered +
                ", room=" + room +
                '}';
    }
}
