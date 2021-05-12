package com.thoughtworks.capability.gtb.springdatajpaintro.blankfordemo;

import com.thoughtworks.capability.gtb.springdatajpaintro.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class SpringBootTestUserControllerTest {
    private User firstUser;

    @BeforeEach
    public void beforeEach() {
        firstUser = User.builder()
                .name("Panda")
                .age(24L)
                .avatar("http://...")
                .description("A good guy.")
                .build();
    }

    @Nested
    class GetUserById {

        @Nested
        class WhenUserIdExists {

            @Test
            public void should_return_user_by_id() throws Exception {

            }
        }

    }
}
