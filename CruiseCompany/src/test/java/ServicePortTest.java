import model.service.PortService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ServicePortTest {
    PortService service;

    @Before
    public void init() {
        service = new PortService();
    }

}
