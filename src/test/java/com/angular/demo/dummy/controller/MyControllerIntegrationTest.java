/*
package com.angular.demo.dummy.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class MyControllerIntegrationTest {

  @Autowired private MockMvc mockMvc;

  @Test
  void fullFlow_CreateThenRetrieveMessage() throws Exception {
    mockMvc
        .perform(
            post("/angular")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                {
                  "id": 1,
                  "message": "Diabetes sucks"
                }
                """))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.message").value("Diabetes sucks"));

    mockMvc
        .perform(get("/angular/{id}", 1L))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.message").value("Diabetes sucks"));
  }

  @Test
  void returnsStatusNotFound() throws Exception {
    mockMvc
        .perform(get("/angular/{id}", 1L).contentType(MediaType.APPLICATION_PROBLEM_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.title").value("Not Found"))
        .andExpect(jsonPath("$.status").value(404))
        .andExpect(jsonPath("$.detail").value("Resource with id 1 not found."))
        .andExpect(jsonPath("$.instance").value("/angular/1"))
        .andExpect(jsonPath("$.resourceId").value(1));
  }
}
*/
