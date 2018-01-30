package serviceTest;

import model.entity.User;
import model.exceptions.UniqueException;
import model.service.UserService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    UserService service = new UserService();

    @Test(expected = UniqueException.class)
    public void addUserTest() {
        service.registration("123","login","vlad.nagaev.vn@gmail.com" );
    }

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
