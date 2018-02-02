package sample;

import java.util.*;
import java.util.regex.*;

public class Address {
    private final String street, house, floor, side, postcode, city;

    private Address(String _street, String _house, String _floor, String _side, String _postcode, String _city) {
        street = _street;
        house = _house;
        floor = _floor;
        side = _side;
        postcode = _postcode;
        city = _city;
    }

    public String toString() {
        return (street != null ? street + " " : "") +
                (house != null ? house + " " : "") +
                (floor != null ? floor + " " : "") +
                (side != null ? side + " " : "") +
                (postcode != null ? postcode + " " : "") +
                (city != null ? city + " " : "");
    }

    public static class Builder {
        private String street = "Unknown", house, floor, side, postcode, city;
        public Builder street(String _street) { street = _street; return this; }
        public Builder house(String _house)   { house = _house;   return this; }
        public Builder floor(String _floor)   { floor = _floor;   return this; }
        public Builder side(String _side)     { side = _side;     return this; }
        public Builder postcode(String _postcode) { postcode = _postcode; return this; }
        public Builder city(String _city)     { city = _city;     return this; }
        public Address build() {
            return new Address(street, house, floor, side, postcode, city);
        }
    }

    public String street()   { return street; }
    public String house()    { return house; }
    public String floor()    { return floor; }
    public String side()     { return side; }
    public String postcode() { return postcode; }
    public String city()     { return city; }

    public static Address parse(String s) {
        final String regex = "((?<street>[a-zA-ZåæøÅÆØ ]+?)\\s+(?<house>\\d+))?\\s*[,]*\\s*((?<postcode>\\d{4})\\s+(?<city>[a-zA-ZåæøÅÆØ ]+))?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        Builder b = new Builder();
        if (matcher.matches()) {
            return b.city(matcher.group("city")).
                    house(matcher.group("house")).
                    postcode(matcher.group("postcode")).
                    street(matcher.group("street")).build();
        }
        throw new IllegalArgumentException("Invalid address " + s);
    }
}
