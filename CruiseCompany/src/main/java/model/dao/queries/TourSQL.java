package model.dao.queries;

public interface TourSQL {
     String INSERT =             "insert into tour(tourname, idship, arrival, departure, region) " +
                                 "values(?, ?, ?, ?, ?)";

     String UPDATE =             "update tour set tourname = ?, idship = ?, arrival = ?, " +
                                 "departure = ?, region = ? where idtour = ?";

     String FIND =               "select * from tour where idtour = ?";

     String COUNT =              "select count(*) from tour";

     String COUNT_WITH_PARAM =   "select count(*) from tour where region like ?";

     String FIND_ALL =           "select * from tour limit ?, ?";

     String TOUR_WITH_SHIP =     "select * from tour join ship using(idship) limit ?, ?";

     String FIND_TOUR_SHIP =     "select * from tour join ship using(idship) where idtour = ?";

     String SEARCH_FOR_REGION =  "select * from tour join ship using(idship) where region like ? " +
                                 "limit ?, ?";
}
