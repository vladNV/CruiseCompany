package controller.util;

public interface RegexpParam {

    String NUMBER = "[1-9]{1}[0-9]{0,9}";
    String EXCURSION_CMD = "(remove|add)";
    String PRICE = "[1-9]{1}[0-9]{1, 16}";
    String CVV = "[1-9]{1}[0-9]{2}";
    String NAME = "^[A-Z][a-z\\-]{2,100}$";
    String LOGIN = "^[A-Za-z0-9_]{3,100}$";
    String PASSWORD = "[A-Za-z0-9]{4,100}";
    String EMAIL = "[a-z0-9\\.]{3,40}@[a-z]{1,7}\\.[a-z]{2,3}";


}
