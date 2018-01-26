package controller.util;

public interface RegexpParam {

    String QUANTITY = "[1-9]{1}[0-9]{2}";
    String NUMBER = "[1-9]{1}[0-9]{0,9}";
    String EXCURSION_CMD = "(remove|add)";
    String PRICE = "[1-9]{1}\\d{1,15}";
    String CVV = "[1-9]{1}\\d{2}";
    String NAME = "^[A-Za-z\\-]{2,100}$";
    String TOUR_NAME = "^[A-za-z\\s-,\\.]{4,200}$";
    String ROUTE = "^[A-Za-z\\s-,\\.;:0-9]{5,100}$";
    String LOGIN = "^[A-Za-z0-9_]{3,75}$";
    String PASSWORD = "[A-Za-z0-9]{4,16}";
    String EMAIL = "[a-z0-9\\.]{3,40}@[a-z]{1,7}\\.[a-z]{2,3}";
    String PHONE = "\\d{10,12}";
    String LOCALE_DATE_TIME =
            "((20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])T" +
            "([01]?[0-9]|2[0-3]):[0-5][0-9]";



}
