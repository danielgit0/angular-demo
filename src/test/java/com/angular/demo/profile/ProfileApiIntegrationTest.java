package com.angular.demo.profile;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ProfileApiIntegrationTest {

  @Autowired private MockMvc mockMvc;

  @Test
  void shouldReturn200WhenUserHasProfileReadScope() throws Exception {
    mockMvc
        .perform(
            get("/api/v1/profiles")
                .param("page", "0")
                .param("pageSize", "10")
                .with(jwt().authorities(new SimpleGrantedAuthority("SCOPE_profile_read"))))
        .andExpect(status().isOk());
  }

  @Test
  void shouldReturn403WhenUserIsMissingRequiredScope() throws Exception {
    mockMvc
        .perform(
            get("/api/v1/profiles")
                .param("page", "0")
                .param("pageSize", "10")
                .with(jwt().authorities(new SimpleGrantedAuthority("SCOPE_company_read"))))
        .andExpect(status().isForbidden());
  }

  @Test
  void shouldReturn401WhenUserIsUnauthenticated() throws Exception {
    mockMvc
        .perform(get("/api/v1/profiles").param("page", "0").param("pageSize", "10"))
        .andExpect(status().isUnauthorized());
  }
}
