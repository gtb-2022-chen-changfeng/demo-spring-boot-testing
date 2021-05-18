package com.thoughtworks.capability.gtb.springdatajpaintro;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Rollback
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void should_return_user_when_id_exists() {
        User savedUser = User.builder()
                .name("Panda1")
                .age(24L)
                .avatar("http://...")
                .description("A good guy.")
                .build();
        entityManager.persistAndFlush(savedUser);

        Optional<User> found = userRepository.findOneById(savedUser.getId());

        assertThat(found.isPresent()).isTrue();
        assertThat(found.get()).isEqualTo(User.builder()
                .id(savedUser.getId())
                .name("Panda1")
                .age(24L)
                .avatar("http://...")
                .description("A good guy.")
                .build());
    }

    @Test
    void should_return_user_list() {
        // given
        User user = User.builder()
                .name("Panda2")
                .age(24L)
                .avatar("http://...")
                .description("A good guy.")
                .educations(Collections.emptyList())
                .build();
        User savedUser = entityManager.persist(user);

        // when
        List<User> userList = userRepository.findAll(); // size: 2 ? size: 1

        // then
        Assertions.assertThat(userList).containsOnly(savedUser);

    }
}
