import controller.util.RegexpParam;
import org.junit.Test;
import static controller.util.RequestParser.*;
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


}