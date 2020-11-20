package by.epam.mtlcwtchr.ecafe.entity;

import java.io.Serializable;
import java.util.Objects;

public class Hall implements Serializable {

    private static final long serialVersionUID = -2717157075749098350L;

    private int id;
    private int guestsNumber;
    private String name;
    private String description;

    public Hall(){}
    public Hall(int guestsNumber, String name, String description) {
        this.name = name;
        this.guestsNumber = guestsNumber;
        this.description = description;
    }

    public Hall(int id, int guestsNumber, String hallName, String hallDescription) {
        this(guestsNumber, hallName, hallDescription);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGuestsNumber() {
        return guestsNumber;
    }

    public void setGuestsNumber(int guestsNumber) {
        this.guestsNumber = guestsNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hall hall = (Hall) o;
        return id == hall.id &&
                guestsNumber == hall.guestsNumber &&
                Objects.equals(description, hall.description) &&
                Objects.equals(name, hall.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, guestsNumber, name, description);
    }

    @Override
    public String toString() {
        return  getClass().getSimpleName() + "{" +
                "id=" + id +
                ", guestsNumber=" + guestsNumber +
                ", hallDescription='" + description + '\'' +
                ", hallName='" + name + '\'' +
                '}';
    }
}
