package by.epam.mtlcwtchr.ecafe.entity;

import java.io.Serializable;
import java.util.Objects;

public class Ingredient implements Entity, Serializable {

    private static final long serialVersionUID = 6558144628028259825L;

    private int id;
    private String name;
    private int mass;
    private String pictureUrl;

    public Ingredient(){}
    public Ingredient(String name) {
        this.name = name;
    }
    public Ingredient(String name, String pictureUrl) {
        this(name);
        this.pictureUrl = pictureUrl;
    }
    public Ingredient(String name, String pictureUrl, int mass) {
        this(name, pictureUrl);
        this.mass = mass;
    }
    public Ingredient(int id, String name, String pictureUrl) {
        this(name, pictureUrl);
        this.id = id;
    }
    public Ingredient(int id, String name, String pictureUrl, int mass) {
        this(id, name, pictureUrl);
        this.mass = mass;
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
    public int getMass() {
        return mass;
    }
    public void setMass(int mass) {
        this.mass = mass;
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
        Ingredient that = (Ingredient) o;
        return id == that.id &&
                mass == that.mass &&
                Objects.equals(name, that.name) &&
                Objects.equals(pictureUrl, that.pictureUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, mass, pictureUrl);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mass=" + mass +
                ", pictureUrl='" + (pictureUrl!=null ? pictureUrl : "not assigned") + '\'' +
                '}';
    }

}
