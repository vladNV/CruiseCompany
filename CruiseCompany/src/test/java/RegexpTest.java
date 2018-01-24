import controller.util.RegexpParam;
import org.junit.Test;
import static controller.util.RequestParser.*;
import static org.junit.Assert.*;

public class RegexpTest {

    @Test
    public void testRegexpLocaleDateTime() {
        assertEquals(true, "2017-01-24T16:26".matches(RegexpParam.LOCALE_DATE_TIME));
    }

}