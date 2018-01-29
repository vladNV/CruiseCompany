package controllerTest;

import org.junit.Test;
import static controller.util.RequestUtil.*;
import static org.junit.Assert.*;

public class RequestParserTest {


    @Test
    public void testGetActionFromURI() {

        assertEquals("premium", getActionFromURI("/ticket/premium"));
        assertEquals("ticket", getActionFromURI("/ticket"));
        assertEquals("tour", getActionFromURI("/tour/2"));
        assertEquals("add_excursion", getActionFromURI("/ticket/add_excursion"));
    }


}
