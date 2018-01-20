package model.exceptions;

import java.sql.SQLException;

public class TicketPaidException extends SQLException {
    public TicketPaidException(String reason) {
        super(reason);
    }

    public TicketPaidException() {
    }
}
