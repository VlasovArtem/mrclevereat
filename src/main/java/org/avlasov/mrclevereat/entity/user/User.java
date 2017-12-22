package org.avlasov.mrclevereat.entity.user;

import org.avlasov.mrclevereat.entity.Base;

import java.time.LocalDate;

/**
 * Created By artemvlasov on 22/12/2017
 **/
public class User extends Base {

    private String email;
    private byte[] password;
    private String lastName;
    private String firstName;
    private LocalDate birthday;
    private int age;
    //Weight in kilograms
    private double weight;
    //Height in centimeters
    private short height;
    private DietData dietData;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public short getHeight() {
        return height;
    }

    public void setHeight(short height) {
        this.height = height;
    }

    public DietData getDietData() {
        return dietData;
    }

    public void setDietData(DietData dietData) {
        this.dietData = dietData;
    }

}
