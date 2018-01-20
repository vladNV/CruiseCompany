package controller.util;

public class ActionResponse {
    private Act act;
    private String path;

    public ActionResponse(Act act, String path) {
        this.act = act;
        this.path = path;
    }

    public Act getAct() {
        return act;
    }

    public String getPath() {
        return path;
    }
}
