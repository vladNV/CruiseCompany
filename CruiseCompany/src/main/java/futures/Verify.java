package futures;

import java.util.LinkedList;

public class Verify {

    private LinkedList<String> remarks = new LinkedList<>();

    public Verify validate(Param ... params) {
        for (Param param : params) {
            if (!param.getValue().matches(param.getRegexp())) {
                remarks.add(param.getIncorrect());
            }
        }
        return this;
    }

    public Verify equalSize(String incorrect, String[] ... arr) {
        int size = arr[0].length;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].length != size) {
                remarks.add(incorrect);
                return this;
            }
        }
        return this;
    }

    public Verify validateArray(Param ... arrays) {
        for (Param array : arrays) {
            for (String s : array.getValues()) {
                if (!s.matches(array.getRegexp())) {
                    remarks.add(array.getIncorrect());
                    return this;
                }
            }
        }
        return this;
    }

    public Verify validate(String val, String inc, String regexp) {
        if (!val.matches(regexp)) {
            remarks.add(inc);
        }
        return this;
    }

    public boolean allRight() {
        return remarks.isEmpty();
    }

    public LinkedList<String> getRemarks() {
        return remarks;
    }
}
