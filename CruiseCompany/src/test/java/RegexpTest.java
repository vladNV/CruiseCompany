import controller.util.RegexpParam;
import org.junit.Test;
import static org.junit.Assert.*;

public class RegexpTest {

    @Test
    public void testRegexpLocaleDateTime() {
        assertEquals(true, "2017-01-24T16:26".matches(RegexpParam.LOCALE_DATE_TIME));
    }

    @Test
    public void regexpTour() {
        assertEquals(true, "Tour name  ".matches(RegexpParam.TOUR_NAME));
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


}