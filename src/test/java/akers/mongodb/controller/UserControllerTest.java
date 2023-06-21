package akers.mongodb.controller;

import akers.mongodb.model.User;
import akers.mongodb.services.IUserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest {
    @Autowired
    IUserService iServices;

    //    User RECORD_1 = new User("1l", "Rayven Yor", "phong1", "123455");
//    User RECORD_2 = new User("2l", "David Landup", "phong2", "123455");
//    User RECORD_3 = new User("3l", "Jane Doe", "phong3", "123455");
    private String id;
    private String name;
    private String username;
    private String password ="123456";
    private String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
    private String numbers = "0123456789";
    private String newPass = "newpass";
    @BeforeEach
    void setUp(){
        String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        // specify length of random string
        int length = 10;

        for(int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphaNumeric.length());

            // get character specified by index
            // from the string
            char randomChar = alphaNumeric.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }

        id = sb.toString();
        name= "Phong"+ id;
        username = "acc"+ name;
        User us = new User(id, name, username, password);
        iServices.add(us);
    }
    @Test
    @Order(1)
    void getAll() {
        List us1 = iServices.getAll();
        assertThat(us1).size().isGreaterThan(0);
    }

    @Test
    @Order(2)
    void getById() {
        User us = iServices.getById(id);
        assertEquals(name,us.getName());
    }

   /* @Test
    @Order(3)
    void createUser() {
        User us = new User(id, name, username, password);
        iServices.add(us);
        User us1 = iServices.getById(id);
        assertNotNull(us1);
    }*/

    @Test
    @Order(4)
    void updateUser() {
        User us = iServices.getById(id);
        User us1 = new User(us.getId(),us.getName(),us.getUsername(),newPass);
        iServices.update(id,us1);
        assertNotEquals(us1, iServices.getById(id));
    }

    @Test
    @Order(5)
    void deleteUser() {
        iServices.delete(id);
        assertThat(iServices.existID(id)).isFalse();

    }
}

