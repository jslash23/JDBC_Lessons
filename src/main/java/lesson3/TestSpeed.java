package lesson3;

public class TestSpeed {
     long id;
     String someString;
     int someNumber;

    public TestSpeed(long id, String someString, int someNumber) {
        this.id = id;
        this.someString = someString;
        this.someNumber = someNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSomeString() {
        return someString;
    }

    public void setSomeString(String someString) {
        this.someString = someString;
    }

    public int getSomeNumber() {
        return someNumber;
    }

    public void setSomeNumber(int someNumber) {
        this.someNumber = someNumber;
    }

    @Override
    public String toString() {
        return "TestSpeed{" +
                "id=" + id +
                ", someString='" + someString + '\'' +
                ", someNumber=" + someNumber +
                '}';
    }
    ///////////////////
}
