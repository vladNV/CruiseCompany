package controller.util;

import java.util.Locale;

public enum Currency {


    UAH(28.9351852f), USD(1);

    private float currency;

    Currency(float currency) {
        this.currency = currency;
    }

    public float value() {
        return currency;
    }

    public static Currency currency(Locale locale) {
        if (locale.equals(Locale.US)) {
            return USD;
        } else if (locale.equals(MessageManager.UKRAINE)) {
            return UAH;
        } else {
            return USD;
        }
    }

    public static class Const {
        // one dollar is 1000 points
        public static final long ACCURACY = 1000;
    }

}
