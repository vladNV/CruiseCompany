package serviceTest;

import model.service.UserService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {
    UserService service = new UserService();

    @Before
    public void init() {
        service = new UserService();
    }

    @Test
    public void testUniqueEmail() {
        assertEquals(false,service.uniqueEmail("vlad.nagaev.vn@gmail.com"));
        assertEquals(true, service.uniqueEmail("uniqueemail@gmail.com"));
    }




}
