package controller.cmd;

import model.service.UserService;

public enum ActionEnum {


    LOGIN        {{ action = new SignIn(); }},
    LOGOUT       {{ action = new SignOut(); }},
    REGISTRATION {{ action = new SignUp(); }},
    EMPTY        {{ action = new Empty(); }},
    MAIN         {{ action = new ShowTourList(); }},
    TOUR         {{ action = new OpenTour(); }};

    Action action;
    public Action getAction() {
        return action;
    }
}