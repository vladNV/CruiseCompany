package controller.cmd;

import controller.util.MessageManager;

import java.util.Locale;

public enum ActionEnum {
    LOGIN               {{ action = new SignIn(); }},
    LOGOUT              {{ action = new SignOut(); }},
    REGISTRATION        {{ action = new SignUp(); }},
    MAIN                {{ action = new ShowTourList(); }},
    TOUR                {{ action = new OpenTour(); }},
    EXCURSIONS          {{ action = new ShowExcursionList(); }},
    PROFILE             {{ action = new ShowUserTickets(); }},
    ADD_CRUISE          {{ action = new AddCruise(); }},
    CONFIRM             {{ action = new Confirm(); }},
    EXCURSION           {{ action = new AddRemoveExcursion(); }},
    STANDARD            {{ action = new ChooseTicket(); }},
    PREMIUM             {{ action = new ChooseTicket(); }},
    LUXE                {{ action = new ChooseTicket(); }},
    UA                  {{ action = new SwitchLanguage(MessageManager.UKRAINE); }},
    ENG                 {{ action = new SwitchLanguage(Locale.ENGLISH); }},
    BUY                 {{ action = new BuyTicket(); }},
    SEARCH              {{ action = new Search(); }},
    ADDCRUISE           {{ action = new AddCruise();}},
    ADDTICKETS          {{ action = new AddTickets();}},
    CRUISEEDITOR        {{ action = new CruiseEditor();}};

    Action action;
    public Action getAction() {
        return action;
    }
}
