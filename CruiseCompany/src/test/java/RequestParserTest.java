import controller.util.RequestParser;
import org.junit.Test;
import static controller.util.RequestParser.*;
import static org.junit.Assert.*;

public class RequestParserTest {

    @Test
    public void testGetActionFromURI() {
        assertEquals(null, getActionFromURI("/"));
        assertEquals(null, getActionFromURI("//"));
        assertEquals("premium", getActionFromURI("/ticket/premium"));
        assertEquals("ticket", getActionFromURI("/ticket"));
        assertEquals("tour", getActionFromURI("/tour/2"));
        assertEquals("add_excursion", getActionFromURI("/ticket/add_excursion"));
    }


}
