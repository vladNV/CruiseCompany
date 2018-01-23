package model.entity;

import java.io.Serializable;
import java.util.List;

public class User implements Cloneable, Serializable, Entity {
    private final static long serialVersionUID = 1L;

    private int id;
    private String login;
    private String password;
    private String email;
    private Role role;
    private List<Ticket> tickets;

    private User(int id, String login, String password,
                 String email, List<Ticket> tickets, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.tickets = tickets;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public static Builder newUser() {
        return new Builder();
    }

    public static class Builder {
        private int id;
        private String login;
        private String password;
        private String email;
        private Role role;
        private List<Ticket> tickets;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder login(String login) {
            this.login = login;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder tickets(List<Ticket> tickets) {
            this.tickets = tickets;
            return this;
        }

        public Builder role(Role role) {
            this.role = role;
            return this;
        }

        public User build() {
            return new User(id, login, password,
                    email, tickets, role);
        }
    }

    @Override
    public int hashCode() {
        return 31 * id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj != null && obj.getClass() == getClass()) {
            User u = (User) obj;
            return id == u.id;
        }
        return false;
    }

    @Override
    public User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
