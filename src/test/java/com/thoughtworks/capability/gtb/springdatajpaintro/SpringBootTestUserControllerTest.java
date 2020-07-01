package com.thoughtworks.capability.gtb.springdatajpaintro;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootTestUserControllerTest {

    @MockBean
    private UserService userService;
    @Autowired
    private TestRestTemplate restTemplate;

    private User firstUser;

    @BeforeEach
    public void beforeEach() {
        firstUser = User.builder()
                .id(123L)
                .name("Panda")
                .age(24L)
                .avatar("http://...")
                .description("A good guy.")
                .build();
    }

    @AfterEach
    public void afterEach() {
        Mockito.reset(userService);
    }

    @Nested
    class GetUserById {

        @Nested
        class WhenUserIdExists {

            @Test
            public void should_return_user_by_id_with_jsonPath() throws Exception {
                when(userService.findById(123L)).thenReturn(firstUser);

                ResponseEntity<User> responseEntity = restTemplate.getForEntity("/users/{id}", User.class, 123L);

                assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
                assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
                assertThat(responseEntity.getBody()).isEqualTo(firstUser);

                verify(userService).findById(123L);
            }
        }

        @Nested
        class WhenUserIdNotExisted {

            @Test
            public void should_return_NOT_FOUND() throws Exception {
                when(userService.findById(123L)).thenThrow(new UserNotExistedException("foobar"));

                ResponseEntity<ApiError> responseEntity = restTemplate.getForEntity("/users/{id}", ApiError.class, 123L);

                assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
                assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
                assertThat(Objects.requireNonNull(responseEntity.getBody()).getMessage()).containsSequence("foobar");

                verify(userService).findById(123L);
            }
        }
    }

    @Nested
    class CreateUser {

        private User newUserRequest;

        @BeforeEach
        public void beforeEach() {
            newUserRequest = User.builder()
                    .name("Panda")
                    .age(24L)
                    .avatar("http://...")
                    .description("A good guy.")
                    .build();
        }

        @Nested
        class WhenRequestIsValid {

            @Test
            public void should_create_new_user_and_return_its_id() throws Exception {
                when(userService.createUser(newUserRequest)).thenReturn(666L);

                ResponseEntity<Long> responseEntity = restTemplate.postForEntity("/users", newUserRequest, Long.class);
                assertThat(responseEntity.getBody()).isEqualTo(666L);
            }
        }
    }
}
