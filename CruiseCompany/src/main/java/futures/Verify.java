package futures;

import java.util.LinkedList;

public class Verify {

    private LinkedList<String> remarks = new LinkedList<>();

    public Verify validate(Param param) {
        if (!param.getValue().matches(param.getRegexp())) {
            remarks.add(param.getIncorrect());
        }
        return this;
    }

    public Verify validateArray(Param param) {
        for (String s : param.getValues()) {
            if (!s.matches(param.getRegexp())) {
                remarks.add(param.getIncorrect());
                return this;
            }
        }
        return this;
    }

    public boolean allRight() {
        return !remarks.isEmpty();
    }

    public LinkedList<String> getRemarks() {
        return remarks;
    }
}
