package org.avlasov.mrclevereat.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.avlasov.mrclevereat.entity.Base;
import org.avlasov.mrclevereat.entity.diet.DietData;
import org.avlasov.mrclevereat.entity.user.enums.Gender;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Created By artemvlasov on 22/12/2017
 **/

@Entity
@Table(indexes = {
        @Index(name = "EMAIL_IDX", columnList = "email")
})

public class User extends Base {

    @Column(unique = true, nullable = false)
    @NotNull
    private String email;
    @Column(nullable = false)
    @NotNull
    private byte[] password;
    private String lastName;
    private String firstName;
    private LocalDate birthday;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private int age;
    //Weight in kilograms
    @Column(nullable = false)
    @NotNull
    private double weight;
    //Height in centimeters
    @Column(nullable = false)
    @NotNull
    private short height;
    @Embedded
    private DietData dietData;

    User(){}

    User(String email, byte[] password, String lastName, String firstName, LocalDate birthday, Gender gender, int age, double weight, short height, DietData dietData) {
        this.email = email;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.dietData = dietData;
    }

    public String getEmail() {
        return email;
    }

    @JsonIgnore
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

    public Gender getGender() {
        return gender;
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

    public static UserBuilder builder(String email, byte[] password) {
        return new UserBuilder(email, password);
    }

    public static UserBuilder builder(User user) {
        Objects.requireNonNull(user.password);
        Objects.requireNonNull(user.email);
        return new UserBuilder(user);
    }

    public static class UserBuilder {

        private final byte[] password;
        private String email;
        private String lastName;
        private String firstName;
        private LocalDate birthday;
        private Gender gender;
        private int age;
        private double weight;
        private short height;
        private DietData dietData;

        UserBuilder(String email, byte[] password) {
            this.email = email;
            this.password = password;
        }

        UserBuilder(User user) {
            password = user.password;
            email = user.email;
            lastName = user.lastName;
            firstName = user.firstName;
            birthday = user.birthday;
            age = user.age;
            weight = user.weight;
            height = user.height;
            dietData = user.dietData;
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

        public UserBuilder gender(Gender gender) {
            this.gender = gender;
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
            return new User(email, password, lastName, firstName, birthday, gender, age, weight, height, dietData);
        }
    }

}
