package controllerTest;

import controller.util.RegexpParam;
import org.junit.Test;
import static org.junit.Assert.*;

public class RegexpTest {

    @Test
    public void testRegexpLocaleDateTime() {
        assertEquals(true, "2017-01-24T16:26".matches(RegexpParam.LOCALE_DATE_TIME));
    }

    @Test
    public void testName() {
        assertEquals(true, "Vladyslav".matches(RegexpParam.NAME));
        assertEquals(true, "Nahaiev".matches(RegexpParam.NAME));
    }

    @Test
    public void regexpTour() {
        assertEquals(true, "Tour name".matches(RegexpParam.TOUR_NAME));
        assertEquals(true, "12".matches(RegexpParam.NUMBER));
        assertEquals(true, "100000".matches(RegexpParam.PRICE));
        assertEquals(false, "-12".matches(RegexpParam.NUMBER));
        assertEquals(false, "sdioahsadkjsdhaasd".matches(RegexpParam.PRICE));
    }

    @Test
    public void regexpRoute() {
        assertEquals(true, "Italy".matches(RegexpParam.ROUTE));
        assertEquals(true, "Italy weekend".matches(RegexpParam.ROUTE));
        assertEquals(true, "Italy, tour".matches(RegexpParam.ROUTE));
        assertEquals(true, "French".matches(RegexpParam.ROUTE));
        assertEquals(true, "Route 1".matches(RegexpParam.ROUTE));
    }

    @Test
    public void phone() {
        assertEquals(true, "380993268575".matches("\\+?[1-9]{1}\\d{8,13}"));
        assertEquals(true, "+380993268575".matches("\\+?[1-9]{1}\\d{8,13}"));
        assertEquals(false, "djkhddd9878".matches("\\+?[1-9]{1}\\d{8,13}"));
    }


}