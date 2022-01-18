package com.thoughtworks.capability.gtb.springdatajpaintro;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootTestUserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;

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
    class GetUserByIdTest {

        @Nested
        class WhenUserIdExistsTest {

            @Test
            void should_return_user_by_id_with_jsonPath() throws Exception {
                User savedUser = userRepository.save(firstUser);

                ResponseEntity<User> responseEntity = restTemplate.getForEntity("/users/{id}", User.class, savedUser.getId());

                assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
                assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
                User user = responseEntity.getBody();
                assertThat(user).isNotNull();
                assertThat(user.getId()).isEqualTo(savedUser.getId());
                assertThat(user.getName()).isEqualTo(savedUser.getName());
                assertThat(user.getAge()).isEqualTo(savedUser.getAge());
                assertThat(user.getAvatar()).isEqualTo(savedUser.getAvatar());
                assertThat(user.getDescription()).isEqualTo(savedUser.getDescription());
                assertThat(user.getEducations()).isEmpty();

            }
        }

        @Nested
        class WhenUserIdNotExistedTest {

            @Test
            void should_return_NOT_FOUND() throws Exception {

                ResponseEntity<ErrorResult> responseEntity = restTemplate.getForEntity("/users/{id}", ErrorResult.class, 234L);

                assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
                assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(MediaType.APPLICATION_JSON);
                assertThat(Objects.requireNonNull(responseEntity.getBody()).getMessage()).containsSequence("User Not Found");
            }
        }
    }

    @Nested
    class CreateUserTest {

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
        class WhenRequestIsValidTest {

            @Test
            void should_create_new_user_and_return_its_id() throws Exception {

                ResponseEntity<Long> responseEntity = restTemplate.postForEntity("/users", newUserRequest, Long.class);
                assertThat(responseEntity.getBody()).isPositive();
            }
        }
    }
}
