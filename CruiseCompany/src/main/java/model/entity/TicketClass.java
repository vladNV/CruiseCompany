package model.entity;

public enum TicketClass {
    STANDARD(0.4f), PREMIUM(0.4f), LUXE(0.2f);

    private float relative;

    TicketClass(float relative) {
        this.relative = relative;
    }

    public float getRelative() {
        return relative;
    }
}
