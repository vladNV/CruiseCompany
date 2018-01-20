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

    private static final ActionResponse none =
            new ActionResponse(Act.NONE, URI.NONE);

    public static ActionResponse Default() {
        return none;
    }

}
