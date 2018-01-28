package futures;

import static controller.util.RequestParser.nullCheck;

public class Param {
    private String incorrect;
    private String regexp;
    private String value;
    private String values[];

    public void setIncorrect(String incorrect) {
        nullCheck(incorrect);
        this.incorrect = incorrect;
    }

    public void setRegexp(String regexp) {
        nullCheck(regexp);
        this.regexp = regexp;
    }

    public void setValue(String value) {
        nullCheck(value);
        this.value = value;
    }

    public void setValues(String[] values) {
        nullCheck(values);
        this.values = values;
    }

    public String getValue() {
        return value;
    }

    public String[] getValues() {
        return values;
    }

    public String getIncorrect() {
        return incorrect;
    }

    public String getRegexp() {
        return regexp;
    }

    public long toLong() {
        return Long.parseLong(value);
    }

    public int toInt() {
        return Integer.parseInt(value);
    }

}
