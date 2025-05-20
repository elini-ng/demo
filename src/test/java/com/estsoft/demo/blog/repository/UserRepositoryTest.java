package com.estsoft.demo.blog.repository;

import com.estsoft.demo.blog.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void testFindByEmail() {
        //given:
        String email = "test@test.com";
        String password = "test";
        User savedUser = userRepository.save(new User(email,password));

        //when:java version
        User userInfo = userRepository.findByEmail(email).orElseThrow();

        //then:
        assertEquals(savedUser.getEmail(), userInfo.getEmail());
        assertEquals(savedUser.getPassword(), userInfo.getPassword());
    }

    @Test
    public void testFindAll() {
        //given:
        userRepository.save(new User("test1@test.com","test1"));
        userRepository.save(new User("test2@test.com","test2"));

        //when:
        List<User> userInfos = userRepository.findAll();

        //then:
        assertEquals(2, userInfos.size());
    }

    @Test
    public void testSave() {
        //given:
        User user = new User("test@test.com","test");

        //when:
        User savedUser = userRepository.save(user);

        //then:
        assertEquals(savedUser.getEmail(), user.getEmail());
        assertEquals(savedUser.getPassword(), user.getPassword());
    }

}