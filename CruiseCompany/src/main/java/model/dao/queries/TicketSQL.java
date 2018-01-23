package model.dao.queries;

public interface TicketSQL {
      String INSERT = "insert into ticket(idtour, iduser, arrival, departure, price, type) " +
                    "values(?, ?, ?, ?, ?, ?)";
      String UPDATE = "update ticket set idtour = ?, iduser = ?, arrival = ?, " +
                    "departure = ?, price = ?, type = ?";
      String FIND = "select * from ticket where idticket = ?";
      String TICKETS_TOUR = "select * from ticket where idtour = ? limit ?, ?";
      String QUANTITY_TICKET = "select count(*) as amount, ticket.*, tour.* " +
                    "from ticket join tour using(idtour) where idtour = ? and iduser is null " +
                    "group by ticket.type";
      String FIND_BY_TYPE = "select * from ticket join tour using (idtour) where type = ? and idtour = ? limit 1";
      String UPDATE_USER_TICKET = "update ticket set iduser = ?, amount_passengers = ? " +
                    "where idticket = ? and iduser is null";
      String USER_TICKETS = "select * from ticket join tour using(idtour) where iduser = ?";
      String EXIST_TICKET = "select idticket from ticket where idtour = ? and iduser = ?";
}
