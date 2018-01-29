package futures;

import static controller.util.RequestUtil.nullArrayCheck;
import static controller.util.RequestUtil.nullCheck;

public class Param {
    private String incorrect;
    private String regexp;
    private String value;
    private String values[];

    public Param() { }

    private Param(String incorrect, String regexp, String value, String[] values) {
        this.incorrect = incorrect;
        this.regexp = regexp;
        this.value = value;
        this.values = values;
    }

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
        nullArrayCheck(values);
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

    public static Builder newParam() {
        return new Builder();
    }

    public static class Builder {
        private String incorrect;
        private String regexp;
        private String value;
        private String values[];

        public Builder incorrect(String incorrect) {
            nullCheck(incorrect);
            this.incorrect = incorrect;
            return this;
        }

        public Builder regexp(String regexp) {
            nullCheck(regexp);
            this.regexp = regexp;
            return this;
        }

        public Builder value(String value) {
            nullCheck(value);
            this.value = value;
            return this;
        }

        public Builder values(String[] values) {
            nullArrayCheck(values);
            this.values = values;
            return this;
        }

        public Param build() {
            return new Param(incorrect, regexp, value, values);
        }
    }

}
