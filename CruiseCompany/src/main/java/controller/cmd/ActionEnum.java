package controller.cmd;

import controller.cmd.admin.AddCruise;
import controller.cmd.admin.AddTickets;
import controller.cmd.admin.CruiseEditor;
import controller.cmd.user.*;
import controller.util.MessageManager;

import java.util.Locale;

public enum ActionEnum {

    LOGIN               {{ action = new Login(); }},
    SIGNIN              {{ action = new SignIn(); }},
    REGISTRATION        {{ action = new Registration(); }},
    SIGNUP              {{ action = new SignUp(); }},
    LOGOUT              {{ action = new SignOut(); }},
    MAIN                {{ action = new ShowTourList(); }},
    TOUR                {{ action = new OpenTour(); }},
    EXCURSIONS          {{ action = new ShowExcursionList(); }},
    PROFILE             {{ action = new ShowUserTickets(); }},
    ADD_CRUISE          {{ action = new AddCruise(); }},
    CONFIRM             {{ action = new Confirm(); }},
    EXCURSION           {{ action = new AddRemoveExcursion(); }},
    TICKET              {{ action = new ChooseTicket(); }},
    UA                  {{ action = new SwitchLanguage(MessageManager.UKRAINE); }},
    ENG                 {{ action = new SwitchLanguage(Locale.US); }},
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
