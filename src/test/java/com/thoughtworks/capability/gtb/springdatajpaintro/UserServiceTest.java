package com.thoughtworks.capability.gtb.springdatajpaintro;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private UserService userService;
    @Mock
    private UserRepository userRepository;
    private User user;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
        user = User.builder()
                .id(123L)
                .name("Panda")
                .age(24L)
                .avatar("http://...")
                .description("A good guy.")
                .build();
    }

    @Nested
    class FindByIdTest {

        @Nested
        class WhenIdExistsTest {

            @Test
            void should_return_user() {
                when(userRepository.findOneById(123L)).thenReturn(Optional.of(user));

                User foundUser = userService.findById(123L);

                assertThat(foundUser).isEqualTo(User.builder()
                        .id(123L)
                        .name("Panda")
                        .age(24L)
                        .avatar("http://...")
                        .description("A good guy.")
                        .build());
            }
        }

        @Nested
        class WhenUserIdNotExistedTest {

            @Test
            void should_throw_exception() {
                when(userRepository.findOneById(222L)).thenReturn(Optional.empty());

                UserNotExistedException thrownException = assertThrows(UserNotExistedException.class, () -> {
                    userService.findById(222L);
                });

                assertThat(thrownException.getMessage()).containsSequence("User Not Found");
            }
        }
    }

    @Nested
    class CreateUserTest {

        @Test
        void should_save_user_in_repository_when_create_user() {
            // given
            UserRepository userRepository = Mockito.mock(UserRepository.class);
            Mockito.when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
                User userArg = (User) invocation.getArguments()[0];
                userArg.setId(123L);
                return userArg;
            });
            UserService userService = new UserService(userRepository);
            User newUser = User.builder()
                    .name("Panda")
                    .age(24L)
                    .avatar("http://...")
                    .description("A good guy.")
                    .build();

            // when
            Long userId = userService.createUser(newUser);

            // then
            Assertions.assertThat(userId).isEqualTo(123L);
            Mockito.verify(userRepository).save(newUser);
        }

    }
}
