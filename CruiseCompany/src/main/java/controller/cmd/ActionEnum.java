package controller.cmd;

import model.service.UserService;

public enum ActionEnum {


    LOGIN           {{ action = new SignIn(); }},
    LOGOUT          {{ action = new SignOut(); }},
    REGISTRATION    {{ action = new SignUp(); }},
    EMPTY           {{ action = new Empty(); }},
    MAIN            {{ action = new ShowTourList(); }},
    TOUR            {{ action = new OpenTour(); }},
    EXCURSIONS      {{ action = new ShowExcursionList(); }},
    PROFILE         {{ action = new OpenProfile(); }},
    ADD_CRUISE      {{ action = new AddCruise(); }},
    // TODO fix to TICKET
    TICKETSTANDARD  {{ action = new ChooseTicket(); }},
    TICKETPREMIUM   {{ action = new ChooseTicket(); }},
    TICKETLUXE      {{ action = new ChooseTicket(); }};


    Action action;
    public Action getAction() {
        return action;
    }
}
