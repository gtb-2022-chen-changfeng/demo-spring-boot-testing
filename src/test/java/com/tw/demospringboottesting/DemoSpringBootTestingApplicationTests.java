package com.tw.demospringboottesting;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class DemoSpringBootTestingApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_get_ok_when_get_users_api_is_successful() throws Exception {
        final var response = mockMvc.perform(MockMvcRequestBuilders.get("/api/users"))
                .andReturn()
                .getResponse();
        assertEquals(200,response.getStatus());
    }

    @Test
    void should_get_users_content_from_users_api() throws Exception {
        final var content = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/users"))
                .andReturn()
                .getResponse()
                .getContentAsString();
        final var users = new ObjectMapper().readValue(content,User[].class);
        assertEquals(2,users.length);
        assertEquals("Obama", users[0].getUserName());
        assertEquals("Clinton", users[1].getUserName());
    }

    @Test
    void should_test_users_api_using_fluent_testing_api() throws Exception {
        // check status code and content
        mockMvc
                .perform(MockMvcRequestBuilders.get("/api/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].userName", Matchers.is("Obama")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].userName", Matchers.is("Clinton")));
    }

    @Test
    void contextLoads() {
    }

}
