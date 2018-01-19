package model.dao.mapper;

import model.entity.*;
import model.entity.Entity;
import model.util.Role;
import model.util.TicketClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class EntityMapper {

    static class UserMapper implements Mapper<User> {
        @Override
        public User extract(ResultSet rs) throws SQLException {
            return User.newUser()
                    .id(rs.getInt("iduser"))
                    .email(rs.getString("email"))
                    .login(rs.getString("login"))
                    .password(rs.getString("password"))
                    .role(Role.valueOf(rs.getString("role").toUpperCase()))
                    .build();
        }
    }

    static class PortMapper implements Mapper<Port> {
        @Override
        public Port extract(ResultSet rs) throws SQLException {
            return Port.newPort()
                    .id(rs.getInt("idport"))
                    .name(rs.getString("portname"))
                    .city(rs.getString("city"))
                    .country(rs.getString("country"))
                    .build();
        }
    }

    static class RouteMapper implements Mapper<Route> {
        @Override
        public Route extract(ResultSet rs) throws SQLException {
            return Route.newRoute()
                    .id(rs.getInt("idroute"))
                    .arrival(rs.getTimestamp("arrival").toLocalDateTime())
                    .departure(rs.getTimestamp("departure").toLocalDateTime())
                    .name(rs.getString("routename"))
                    .port(extractIfWithoutNext(rs, mapperFactory(EnumMapper.PortMapper)))
                    .build();
        }
    }

    static class ShipMapper implements Mapper<Ship> {
        @Override
        public Ship extract(ResultSet rs) throws SQLException {
            return Ship.newShip()
                    .id(rs.getInt("idship"))
                    .name(rs.getString("shipname"))
                    .capacity(rs.getInt("capacity"))
                    .build();
        }
    }

    static class ExcursionMapper implements Mapper<Excursion> {
        @Override
        public Excursion extract(ResultSet rs) throws SQLException {
            return Excursion.newExcursion()
                    .id(rs.getInt("idexcursion"))
                    .name(rs.getString("excursionname"))
                    .price(rs.getLong("price"))
                    .port(extractIfWithoutNext(rs, mapperFactory(EnumMapper.PortMapper)))
                    .build();
        }
    }

    static class TicketMapper implements Mapper<Ticket> {
        @Override
        public Ticket extract(ResultSet rs) throws SQLException {
            return Ticket.newTicket()
                    .id(rs.getInt("idticket"))
                    .arrival(rs.getTimestamp("arrival").toLocalDateTime())
                    .departure(rs.getTimestamp("departure").toLocalDateTime())
                    .price(rs.getLong("price"))
                    .type(TicketClass.valueOf(rs.getString("type")))
                    .tour(extractIfWithoutNext(rs, mapperFactory(EnumMapper.TourMapper)))
                    .build();
        }
    }

    static class TourMapper implements Mapper<Tour> {
        @Override
        public Tour extract(ResultSet rs) throws SQLException {
            return Tour.newTour()
                    .id(rs.getInt("idtour"))
                    .arrival(rs.getTimestamp("arrival").toLocalDateTime())
                    .departure(rs.getTimestamp("departure").toLocalDateTime())
                    .name(rs.getString("tourname"))
                    .region(rs.getString("region"))
                    .ship(extractIfWithoutNext(rs, mapperFactory(EnumMapper.ShipMapper)))
                    .build();
        }
    }

    static <T extends Entity> T
    extractIfWithoutNext(ResultSet set, Mapper<T> mapper)
            throws SQLException {
        return mapper.extract(set);
    }


    public static <T extends Entity> T extractIf(ResultSet set, Mapper<T> mapper)
            throws SQLException {
        if (set.next()) {
            return mapper.extract(set);
        }
        return null;
    }

    public static <T extends Entity> List<T>
    extractWhile(ResultSet set, Mapper<T> mapper) throws SQLException {
        List<T> generics = new ArrayList<>();
        while (set.next()) {
            generics.add(mapper.extract(set));
        }
        return generics;
    }

    public static int getKey(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    @SuppressWarnings("unchecked")
    public static  <T extends Mapper<?>> T mapperFactory(EnumMapper type) {
        try {
            switch (type) {
                case RouteMapper:
                    return (T) new RouteMapper();
                case UserMapper:
                    return (T) new UserMapper();
                case PortMapper:
                    return (T) new PortMapper();
                case ShipMapper:
                    return (T) new ShipMapper();
                case TourMapper:
                    return (T) new TourMapper();
                case ExcursionMapper:
                    return (T) new ExcursionMapper();
                case TicketMapper:
                    return (T) new TicketMapper();
                default:
                    throw new IllegalArgumentException();
            }
        } catch (ClassCastException e) {
            throw new RuntimeException(e);
        }
    }
}
