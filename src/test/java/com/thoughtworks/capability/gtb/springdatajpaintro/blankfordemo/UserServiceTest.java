package com.thoughtworks.capability.gtb.springdatajpaintro.blankfordemo;

import com.thoughtworks.capability.gtb.springdatajpaintro.User;
import org.junit.jupiter.api.Test;

class UserServiceTest {

    @Test
    void testing_not_with_mockito() {

    }

    @Test
    void testing_with_mockito() {

    }

    @Test
    void testing_with_mockito_injection() {

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
