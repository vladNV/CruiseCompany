package serviceTest;

import controller.util.RequestUtil;
import org.junit.Test;

import static org.junit.Assert.*;


public class ServletUtilTest {

    @Test
    public void testGetIdFromURI() {
        assertEquals(3, RequestUtil.getIdFromURI("/main/3"));
        assertEquals(3, RequestUtil.getIdFromURI("/main/3/ticket"));
        assertEquals(5, RequestUtil.getIdFromURI("/ticket/5"));
        assertEquals(0, RequestUtil.getIdFromURI("/main"));
    }

}
