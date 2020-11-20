package by.epam.mtlcwtchr.ecafe.entity;

import java.io.Serializable;
import java.util.Objects;

public class Category implements Entity, Serializable {

    private static final long serialVersionUID = -8346416776890720424L;

    private int id;
    private String name;
    private String pictureUrl;

    public Category(){}
    public Category(String name) {
        this.name = name;
    }
    public Category(String name, String pictureUrl) {
        this(name);
        this.pictureUrl = pictureUrl;
    }
    public Category(int id, String name, String pictureUrl) {
        this(name, pictureUrl);
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPictureUrl() {
        return pictureUrl;
    }
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category that = (Category) o;
        return id == that.id &&
                Objects.equals(name, that.name)&&
                Objects.equals(pictureUrl, that.pictureUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, pictureUrl);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pictureUrl='" + (pictureUrl!=null ? pictureUrl : "not assigned") + '\'' +
                '}';
    }

}
