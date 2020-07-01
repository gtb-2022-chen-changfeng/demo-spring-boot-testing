package com.thoughtworks.capability.gtb.springdatajpaintro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

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
    class FindById {

        @Nested
        class WhenIdExists {

            @Test
            public void should_return_user() {
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
        class WhenUserIdNotExisted {

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
}
