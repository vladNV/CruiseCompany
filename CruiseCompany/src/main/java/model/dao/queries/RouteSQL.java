package model.dao.queries;

public interface RouteSQL {
     String INSERT =      "insert into route(idtour, departure, arrival, idport, routename) " +
                          "values (?, ?, ?, ?, ?)";

     String UPDATE =      "update route set idtour = ?, departure = ?, arrival = ?, " +
                          "idport = ?, routename = ? where idroute = ?";

     String DELETE =      "delete from route where idroute = ?";

     String TOUR_ROUTES = "select * from route join port using(idport) where idtour = ?";
}
