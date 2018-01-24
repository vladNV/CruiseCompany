package model.dao.queries;

public interface ExcursionSQL {
     String INSERT =  "insert into excursion(idport, price, excursionname) values (?,?,?)";

     String UPDATE =        "update excursion set idport = ?, price = ?, " +
                            "excursionname = ? where idexcursion = ?";

     String DELETE =         "delete from excursion where idexcursion = ?";

     String FIND =           "select * from excursion where idexcursion = ?";

     String FIND_ALL =       "select * from excursion limit ?, ?";

     String PORT_JOIN =      "select * from excursion join port using(idport) limit ?, ?";

     String EXCURSION_PORT = "select * from route join port using (idport) " +
                             "join excursion using (idport)" +
                             "where idtour = ?";

     String USER_EXCURSION = "insert into user_excursion(idexcursion, idticket) values(?, ?)";
}
