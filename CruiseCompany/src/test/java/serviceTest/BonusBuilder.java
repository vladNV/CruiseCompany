package serviceTest;

import model.entity.Ticket;
import org.junit.Test;

public class BonusBuilder {

    @Test
    public void testBonus() {
        Ticket ticket = Ticket.newTicket()
                .bonus("cinema,pool,tennis")
                .build();
        System.out.println(ticket.getBonus());

    }


}
