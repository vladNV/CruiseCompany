import model.entity.Excursion;
import model.service.ExcursionService;
import org.junit.Before;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Test;


public class ExcursionServiceTest {
    ExcursionService service;

    @Before
    public void initExcursionDao() {
        service = new ExcursionService();
    }

    @Test
    public void testShowExcursion() {
        Excursion e = mock(Excursion.class);
        when(e.getId()).thenReturn(3);
        int id = e.getId();
        assertEquals(id, service.getExcursion(3).getId());
    }


}
