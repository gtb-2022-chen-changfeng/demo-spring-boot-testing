package com.tw.demospringboottesting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user = new User(1,"ccf");
    @Test
    void getUserName() {
        final var res = user.getUserName();
        assertEquals("ccf",res);
    }

    @Test
    void getId() {
        final var res = user.getId();
        assertEquals(1,res);
    }
}
