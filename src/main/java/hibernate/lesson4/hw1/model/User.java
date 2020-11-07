package hibernate.lesson4.hw1.model;

public class User {
    private long id;
    private String userName;
    private String password;
    private String country;
    private Enum UserType;


    public User(long id, String userName, String password, String country, Enum userType) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.country = country;
        UserType = userType;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Enum getUserType() {
        return UserType;
    }

    public void setUserType(Enum userType) {
        UserType = userType;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", country='" + country + '\'' +
                ", UserType=" + UserType +
                '}';
    }
}
