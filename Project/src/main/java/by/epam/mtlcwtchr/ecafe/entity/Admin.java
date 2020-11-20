package by.epam.mtlcwtchr.ecafe.entity;

import java.io.Serializable;
import java.util.Objects;

public class Admin implements Actor, Serializable {

    private static final long serialVersionUID = -3519103179602622355L;

    private User user;

    public Admin(){

    }
    public Admin(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(user, admin.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+ "{" + user + '}';
    }

    @Override
    public boolean isPromoted() {
        return getUser().isPromoted();
    }

    @Override
    public int getId() {
        return getUser().getId();
    }
}
