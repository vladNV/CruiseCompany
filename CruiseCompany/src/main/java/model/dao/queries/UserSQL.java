package model.dao.queries;

public interface UserSQL {
     String INSERT =        "insert into user(login, password, email) values (?, ?, ?)";

     String UPDATE =        "update user set login = ?, password = ?, email = ?, role = ? " +
                            "where iduser = ?";

     String FIND =          "select * from user where iduser = ?";

     String FIND_ALL =      "select * from user limit ?, ?";

     String FIND_BY_LOGIN = "select * from user where email like ? limit 1";

     String TAKE_MONEY =    "update protected_bank set money = (money - ?) where card = ? " +
                            "and cvv = ? and money >= ?";

     String PUT_MONEY =     "update protected_bank set money = (money + ?) where card = ?";
}
