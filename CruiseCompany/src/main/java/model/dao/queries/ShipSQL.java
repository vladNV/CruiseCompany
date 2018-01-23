package model.dao.queries;

public interface ShipSQL {
    String FIND = "select * from ship where idship = ?";
    String FIND_ALL = "select * from ship limit ?, ?";
}
