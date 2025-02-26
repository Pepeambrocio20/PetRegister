package co.edu.unipiloto.petregister;

import java.io.Serializable;

public class Pet implements Serializable {
    private String name;
    private int age;
    private String breed;
    private String sex;
    private String description;

    public Pet(String name, int age, String breed, String sex, String description) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.sex = sex;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}