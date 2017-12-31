package org.avlasov.mrclevereat.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.avlasov.mrclevereat.entity.Base;
import org.avlasov.mrclevereat.entity.diet.DietData;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created By artemvlasov on 22/12/2017
 **/

@Entity
@Table(indexes = {
        @Index(name = "EMAIL_IDX", columnList = "email")
})

public class User extends Base {

    @Column(unique = true)
    private String email;
    @JsonIgnore
    private byte[] password;
    private String lastName;
    private String firstName;
    private LocalDate birthday;
    private int age;
    //Weight in kilograms
    private double weight;
    //Height in centimeters
    private short height;
    @Embedded
    private DietData dietData;

    public User(){}

    public String getEmail() {
        return email;
    }

    public byte[] getPassword() {
        return password;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public short getHeight() {
        return height;
    }

    public DietData getDietData() {
        return dietData;
    }

    public void setDietData(DietData dietData) {
        this.dietData = dietData;
    }

    public static UserBuilder builder(String email, byte[] password) {
        return new UserBuilder(email, password);
    }

    public static class UserBuilder {

        private final byte[] password;
        private String email;
        private String lastName;
        private String firstName;
        private LocalDate birthday;
        private int age;
        private double weight;
        private short height;
        private DietData dietData;

        public UserBuilder(String email, byte[] password) {
            this.email = email;
            this.password = password;
        }

        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder birthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }

        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }

        public UserBuilder weight(double weight) {
            this.weight = weight;
            return this;
        }

        public UserBuilder height(short height) {
            this.height = height;
            return this;
        }

        public UserBuilder dietData(DietData dietData) {
            this.dietData = dietData;
            return this;
        }

        public User build() {
            User user = new User();
            user.dietData = dietData;
            user.age = age;
            user.email = email;
            user.password = password;
            user.lastName = lastName;
            user.firstName = firstName;
            user.birthday = birthday;
            user.weight = weight;
            user.height = height;
            return user;
        }


    }

}
