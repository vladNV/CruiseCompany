package controllerTest;

import controller.util.Regexp;
import org.junit.Test;
import static org.junit.Assert.*;

public class RegexpTest {

    @Test
    public void testRegexpLocaleDateTime() {
        assertEquals(true, "2017-01-24T16:26".matches(Regexp.LOCALE_DATE_TIME));
    }

    @Test
    public void testName() {
        assertEquals(true, "Vladyslav".matches(Regexp.NAME));
        assertEquals(true, "Nahaiev".matches(Regexp.NAME));
    }

    @Test
    public void regexpTour() {
        assertEquals(true, "Tour name".matches(Regexp.TOUR_NAME));
        assertEquals(true, "12".matches(Regexp.NUMBER));
        assertEquals(true, "100000".matches(Regexp.PRICE));
        assertEquals(false, "-12".matches(Regexp.NUMBER));
        assertEquals(false, "sdioahsadkjsdhaasd".matches(Regexp.PRICE));
    }

    @Test
    public void regexpRoute() {
        assertEquals(true, "Italy".matches(Regexp.ROUTE));
        assertEquals(true, "Italy weekend".matches(Regexp.ROUTE));
        assertEquals(true, "Italy, tour".matches(Regexp.ROUTE));
        assertEquals(true, "French".matches(Regexp.ROUTE));
        assertEquals(true, "Route 1".matches(Regexp.ROUTE));
    }

    @Test
    public void phone() {
        assertEquals(true, "380993268575".matches("\\+?[1-9]{1}\\d{8,13}"));
        assertEquals(true, "+380993268575".matches("\\+?[1-9]{1}\\d{8,13}"));
        assertEquals(false, "djkhddd9878".matches("\\+?[1-9]{1}\\d{8,13}"));
    }


}