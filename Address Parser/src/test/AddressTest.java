package test;

import org.junit.Ignore;
import org.junit.Test;
import sample.Address;

import static org.junit.Assert.*;

public class AddressTest {
    @Test
    public void testStreetCityInput() {
        String input = "Rued Langgaards Vej 7, 2300 København S";
        Address a = Address.parse(input);
        assertEquals("Rued Langgaards Vej", a.street());
        assertEquals("7", a.house());
        assertEquals("2300", a.postcode());
        assertEquals("København S", a.city());
    }

    @Test
    public void testStreetCityMalformedInput() {
        String input = "Rued Langgaards Vej   7,, 2300 København S";
        Address a = Address.parse(input);
        assertEquals("Rued Langgaards Vej", a.street());
        assertEquals("7", a.house());
        assertEquals("2300", a.postcode());
        assertEquals("København S", a.city());
    }

    @Test
    public void testStreetInput() {
        String input = "Valby Langgade 39";
        Address a = Address.parse(input);
        assertEquals("Valby Langgade", a.street());
        assertEquals("39", a.house());
    }

    @Test
    public void testCityInput() {
        String input = "2500 Valby";
        Address a = Address.parse(input);
        assertEquals("2500", a.postcode());
        assertEquals("Valby", a.city());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidInput1() {
        String input = "39 Gade 39 Gade";
        Address a = Address.parse(input);

    }
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidInput2() {
        String input = "Test 12, 123 City";
        Address a = Address.parse(input);
    }

    @Ignore
    //(expected = IllegalArgumentException.class)
    public void testInvalidInput3() {
        String input = "";
        Address a = Address.parse(input);
    }

}