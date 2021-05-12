package com.thoughtworks.capability.gtb.springdatajpaintro.blankfordemo;

import com.thoughtworks.capability.gtb.springdatajpaintro.User;
import org.junit.jupiter.api.Test;

public class UserServiceTest {

    @Test
    public void testing_not_with_mockito() {

    }

    @Test
    public void testing_with_mockito() {

    }

    @Test
    public void testing_with_mockito_injection() {

    }

    User buildUser() {
        return User.builder()
                .id(123L)
                .name("Panda")
                .age(24L)
                .avatar("http://...")
                .description("A good guy.")
                .build();
    }

}
