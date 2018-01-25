package model.dao.queries;

public interface ShipSQL {
    String FIND =      "select * from ship where idship = ?";

    String FIND_ALL =  "select * from ship";

    String FREE_SHIP = "select ship.* from ship left join tour using(idship) " +
                       "where tour.departure or tour.arrival " +
                       "not between ? and ?";

}
