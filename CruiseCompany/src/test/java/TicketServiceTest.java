import model.entity.*;
import model.exceptions.ServiceException;
import model.service.TicketService;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class TicketServiceTest {
    TicketService service;

    @Before
    public void init() {
        service = new TicketService();
    }

    @Test
    public void testChooseTicket() {
        Ticket ticket = mock(Ticket.class);
        when(ticket.getType()).thenReturn(TicketClass.LUXE);
        assertEquals(ticket.getType(), service
                .chooseTicket(TicketClass.LUXE, 10).getType());
    }

    @Test(expected = ServiceException.class)
    public void testBuyTicketNotEnoughMoney() throws ServiceException {
        Ticket t = mock(Ticket.class);
        Tour tt = mock(Tour.class);
        Set<Excursion> set = mock(Set.class);
        User user = mock(User.class);
        long money = 100;
        long card = 1234;
        int cvv = 111;
        when(user.getId()).thenReturn(4);
        when(t.getTour()).thenReturn(tt);
        when(tt.getId()).thenReturn(3);
        service.buyTicket(t, set, user, money, card, cvv);
    }

}
