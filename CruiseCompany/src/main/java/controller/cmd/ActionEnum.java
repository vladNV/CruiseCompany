package controller.cmd;

public enum ActionEnum {
    LOGIN               {{ action = new SignIn(); }},
    LOGOUT              {{ action = new SignOut(); }},
    REGISTRATION        {{ action = new SignUp(); }},
    EMPTY               {{ action = new Empty(); }},
    MAIN                {{ action = new ShowTourList(); }},
    TOUR                {{ action = new OpenTour(); }},
    EXCURSIONS          {{ action = new ShowExcursionList(); }},
    PROFILE             {{ action = new OpenProfile(); }},
    ADD_CRUISE          {{ action = new AddCruise(); }},
    CONFIRM             {{ action = new Confirm(); }},
    ADD_EXCURSION       {{ action = new AddExcursion(); }},
    REMOVE_EXCURSION    {{ action = new RemoveExcursion(); }},
    STANDARD            {{ action = new ChooseTicket(); }},
    PREMIUM             {{ action = new ChooseTicket(); }},
    LUXE                {{ action = new ChooseTicket(); }};

    Action action;
    public Action getAction() {
        return action;
    }
}
