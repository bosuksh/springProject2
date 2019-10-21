package kr.co.springExample2.eatgo.domain;

public class Restaurant {

    private final String name;
    private final String address;
    private Long id;

    public Restaurant(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getInformation() {
        return name +" in " + address;
    }

    public String getAddress() {
        return address;
    }

    public Long getId() {
        return id;
    }
}
