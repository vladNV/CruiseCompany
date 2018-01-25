package model.dao.queries;

public interface ShipSQL {
    String FIND =      "select * from ship where idship = ?";

    String FIND_ALL =  "select * from ship";

    String FREE_SHIP = "select * from ship left join tour using(idship) " +
                       "where (tour.departure  or tour.arrival) is null " +
                       "or ? < tour.departure or ?  > tour.arrival";

}
