package model.entity;

public enum TicketBonus {
    CINEMA("cinema"), SPORT("sport"), LIBRARY("library"),
    BEAUTY("beauty"), POOL("pool"), TENNIS("tennis");

    private String description;

    TicketBonus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
