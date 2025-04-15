package com.estsoft.demo.lombok;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class PersonTest {
    @Test
    public void testLombok() {
        Person person = new Person();
        person.setId(1L);
        person.setName("name1");
        person.setAge(20);
        person.setHobbies(Arrays.asList("Jogging","Travelling"));

        System.out.println(person.getId());
        System.out.println(person.getName());
        System.out.println(person.getAge());
        System.out.println(person.getHobbies());
    }
}