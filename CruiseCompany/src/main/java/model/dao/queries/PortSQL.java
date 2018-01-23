package model.dao.queries;

public interface PortSQL {
    String INSERT = "insert into port(portname, city, country) values (?, ?, ?)";
    String UPDATE = "update port set portname = ?, city = ?, country = ? where idport = ?";
    String DELETE = "delete from port where idport = ?";
    String FIND = "select * from port where idport = ?";
    String FIND_ALL = "select * from port limit ?, ?";
}
