package controller.util;

import java.util.LinkedHashSet;

public class Verify {

    private LinkedHashSet<String> remarks = new LinkedHashSet<>();
    private String incorrect;
    private String regexp;

    public Verify equalSize(String[] ... arr) {
        int size = arr[0].length;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].length != size) {
                remarks.add(this.incorrect);
                return this;
            }
        }
        return this;
    }

    public Verify validate(String val) {
        if (!(val.trim().isEmpty()) && !val.matches(this.regexp)) {
            remarks.add(this.incorrect);
        }
        return this;
    }

    public Verify validateArray(String[] val) {
        for (String s : val) {
            if (!s.matches(this.regexp)) {
                remarks.add(incorrect);
                return this;
            }
        }
        return this;
    }

    public Verify equalValues(String var1, String var2) {
        if (!var1.equals(var2)) {
            remarks.add(this.incorrect);
        }
        return this;
    }

    public Verify regexp(String regexp) {
        this.regexp = regexp;
        return this;
    }

    public Verify incorrect(String incorrect) {
        this.incorrect = incorrect;
        return this;
    }

    public boolean allRight() {
        return remarks.isEmpty();
    }

    public LinkedHashSet<String> getRemarks() {
        return remarks;
    }
}
