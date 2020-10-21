package hibernate.lesson1;

import javax.persistence.*;

@Entity//анотация
@Table(name = "PRODUCTJD")//анотация дает компилятору подсказки и делает генерацию какого то нового кода
//используют его из библиотеки Гибернейта

public class Product {

   private long id;
    private String name;
    private String description;
    private  int price;

    //Хибернейту всегда нужен пустой конструктор что бы он мог с ним работать
    //он создает пустой объект а потом делает в него сет всех остальных значений


    @Id // аннотация для того чтоб Хибернейт понимал какая колонка является примари ключём в таблице
    //Маппинг полей с БД в поля джава класса


    @SequenceGenerator(name = "PRJD_SEQ", sequenceName = "PRODUCTJD_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRJD_SEQ")
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    @Column(name = "PRICE")
    public int getPrice() {
        return price;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
