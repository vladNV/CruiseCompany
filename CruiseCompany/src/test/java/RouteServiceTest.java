import model.exceptions.RouteTimeException;
import model.service.RouteService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDateTime;

public class RouteServiceTest {
    RouteService service;

    @Before
    public void init() {
        service = new RouteService();

    }

    @Test(expected = RouteTimeException.class)
    public void testExtractRoute() throws RouteTimeException {
        service.extractRoutes(new String[]{"Odessa, Ukraine"},
                new String[]{LocalDateTime
                        .of(2019, 12, 30, 12, 0, 0).toString()},
                new String[]{LocalDateTime.now().toString()},
                new String[]{"1"});
    }

}
