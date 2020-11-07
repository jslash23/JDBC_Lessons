package hibernate.lesson4.hw1.model;

public class Hotel {
    private long id;
    private String name;
    private String country;
    private String city;
    private  String street;

    public Hotel(long id, String name, String country, String city, String street) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.street = street;
    }

     /*@Override//переопределяем метод equals
    //сравниваем два объека по  по ссылкам
    public boolean equals(Object o) {
        if (this == o) return true;// this это тот объект у которого мы вызываем метод,
        // в данном случае это file1
        //если ссылки между собой равны (указывают на один и тот же объект) то мы возвращаем true

        if (o == null || getClass() != o.getClass()) return false;// если объект с которым мы
        // сравниваем налл  или классы не совпадают то возвращаем false


        File file = (File) o;//Делаем операцию кастом (Object тип  приводим к более узкому типу File)

        //Тут мы уже сравниваем поля
        if (!path.equals(file.path)) return false;//учитываем только поле path
        return true;
    }


    @Override
    public int hashCode() {
        return path.hashCode();
    }*/



    //Этот метод проверяет объекты по содержимому
    /*@Override
    public boolean equals(Object o) {
        if (this == o)//тут сравниваем ссылки объектов
            return true;//если ссылки указывают на один и тот же объект мы возвращаем тру
        if (o == null || getClass() != o.getClass())//если объект с которым мы сравниваем налл
            // или класы наших
            //объектов не совпадают (разные) то мы возвращаем false
            return false;

        File file = (File) o;//делаем операцию кастом объект типа обжект мы приводим к типу файл

        //Сравниваем поля
        if (size != file.size)// если поле size != полю size  другого объекта
            return false;// то возвращаем false
        if (!path.equals(file.path)) return false; // если поля path не равны между собой то возвр false
        if (!extension.equals(file.extension)) return false;//если поля extension не равны между собой
            // то возвращаем false
        return true; // если все проверки прошли то возвращаем true
    }

    @Override
    public int hashCode() {
        int result = size;
        result = 31 * result + path.hashCode();
        result = 31 * result + extension.hashCode();
        return result;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hotel hotel = (Hotel) o;

        if (id != hotel.id) return false;
        if (!name.equals(hotel.name)) return false;
        if (!country.equals(hotel.country)) return false;
        if (!city .equals(hotel.city)) return false;
        if(!street.equals(hotel.street))return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result +  name.hashCode();
        result = 31 * result +  country.hashCode();
        result = 31 * result +  city.hashCode();
        result = 31 * result +  street.hashCode();
        return result;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
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
