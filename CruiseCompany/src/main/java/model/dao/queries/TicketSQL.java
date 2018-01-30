package model.dao.queries;

public interface TicketSQL {
      String INSERT =             "insert into ticket(idtour, price, type, place, bonus) " +
                                  "values(?, ?, ?, ?, ?)";

      String UPDATE =             "update ticket set idtour = ?, iduser = ?, person = ?, " +
                                  "place = ?, price = ?, type = ?";

      String FIND =               "select * from ticket where idticket = ?";

      String TICKETS_TOUR =       "select * from ticket where idtour = ? and iduser is null";

      String QUANTITY_TICKET =    "select count(*) as amount, ticket.*, tour.* " +
                                  "from ticket join tour using(idtour) where idtour = ? and " +
                                  "iduser is null group by ticket.type";

      String FIND_BY_TYPE =       "select * from ticket join tour using (idtour) where type = ? " +
                                  "and idtour = ? and iduser is null limit 1";

      String CHOOSE_TICKET =      "select * from ticket join tour using (idtour) " +
                                  "where idticket = ?";

      String UPDATE_USER_TICKET = "update ticket set iduser = ?, amount_passengers = ?, person = ? " +
                                  "where idticket = ? and iduser is null";

      String USER_TICKETS =       "select * from ticket join tour using(idtour) where iduser = ?";

      String EXIST_TICKET =       "select idticket from ticket where idtour = ? and iduser = ?";
}
