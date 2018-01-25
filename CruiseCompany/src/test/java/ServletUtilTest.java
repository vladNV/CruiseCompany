import controller.util.RequestParser;
import org.junit.Test;
import static controller.util.RequestParser.*;
import static org.junit.Assert.*;


public class ServletUtilTest {

    @Test
    public void testGetIdFromURI() {
        assertEquals(3, RequestParser.getIdFromURI("/main/3"));
        assertEquals(3, RequestParser.getIdFromURI("/main/3/ticket"));
        assertEquals(5, RequestParser.getIdFromURI("/ticket/5"));
        assertEquals(0, RequestParser.getIdFromURI("/main"));
    }

}
