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
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diet_data_id", referencedColumnName = "id")
    private DietData dietData;

    private User(){}

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

    public static class UserBuilder {

        private User user;

        public UserBuilder(String email, byte[] password) {
            user = new User();
            user.email = email;
            user.password = password;
        }

        public UserBuilder lastName(String lastName) {
            user.lastName = lastName;
            return this;
        }

        public UserBuilder firstName(String firstName) {
            user.firstName = firstName;
            return this;
        }

        public UserBuilder birthday(LocalDate birthday) {
            user.birthday = birthday;
            return this;
        }

        public UserBuilder age(int age) {
            user.age = age;
            return this;
        }

        public UserBuilder weight(double weight) {
            user.weight = weight;
            return this;
        }

        public UserBuilder height(short height) {
            user.height = height;
            return this;
        }

        public UserBuilder dietData(DietData dietData) {
            user.dietData = dietData;
            return this;
        }

        public User build() {
            return user;
        }


    }

}
