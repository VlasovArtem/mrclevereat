package org.avlasov.mrclevereat.entity.user;

import org.avlasov.mrclevereat.entity.diet.DietData;
import org.avlasov.mrclevereat.entity.user.enums.Gender;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created By artemvlasov on 20/01/2018
 **/
class UserTest {

    @Test
    void defaultConstruct() {
        new User();
    }

    @Test
    void getEmail() {
        assertEquals("test", getUser().getEmail());
    }

    @Test
    void getPassword() {
        assertArrayEquals("test".getBytes(), getUser().getPassword());
    }

    @Test
    void getLastName() {
        assertEquals("test", getUser().getLastName());
    }

    @Test
    void getFirstName() {
        assertEquals("test", getUser().getFirstName());
    }

    @Test
    void getBirthday() {
        assertEquals(LocalDate.now(), getUser().getBirthday());
    }

    @Test
    void getGender() {
        assertEquals(Gender.MALE, getUser().getGender());
    }

    @Test
    void getAge() {
        assertEquals(10, getUser().getAge());
    }

    @Test
    void getWeight() {
        assertEquals(120, getUser().getWeight());
    }

    @Test
    void getHeight() {
        assertEquals(174, getUser().getHeight());
    }

    @Test
    void getDietData() {
        assertNotNull(getUser().getDietData());
    }

    @Test
    void builder() {
        assertNotNull(User.builder("test", "test".getBytes()));
    }

    @Test
    void builder_WithSource() {
        assertEquals(30, User.builder(getUser()).age(30).build().getAge());
    }

    private User getUser() {
        return User.builder("test", "test".getBytes())
                .dietData(DietData.builder().build())
                .weight(120)
                .height((short) 174)
                .gender(Gender.MALE)
                .lastName("test")
                .firstName("test")
                .birthday(LocalDate.now())
                .age(10)
                .build();
    }
    
}