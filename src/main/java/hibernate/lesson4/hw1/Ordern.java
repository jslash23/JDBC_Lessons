package hibernate.lesson4.hw1;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ORDERPR")

public class Ordern {

    private long id;
    private Date dateFrom;
    private Date dateTo;
    private double moneyPaid;
    private Usern usernOrdered;
    private Roomn roomn;

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
    public Usern getUser() {return usernOrdered;}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOMPR_FK", nullable = false)
    public Roomn getRoomn() {return roomn;}

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
        return "Ordern{" +
                "id=" + id +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", moneyPaid=" + moneyPaid +
                ", usernOrdered=" + usernOrdered +
                ", roomn=" + roomn +
                '}';
    }
}
