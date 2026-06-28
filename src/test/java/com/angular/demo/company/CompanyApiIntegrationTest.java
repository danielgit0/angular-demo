package com.angular.demo.company;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.generated.api.CompanyApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CompanyApiIntegrationTest {

  @Autowired private MockMvc mockMvc;

  @Test
  void shouldReturn403WhenUserIsMissingCompanyWriteScope() throws Exception {
    mockMvc
        .perform(
            post(CompanyApi.PATH_CREATE_COMPANY)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
        {
          "name": "Test Company",
          "address": "123 Tech Lane"
        }
        """)
                .with(jwt().authorities(new SimpleGrantedAuthority("SCOPE_company_read"))))
        .andExpect(status().isForbidden());
  }

  @Test
  void shouldReturn401WhenUnauthenticated() throws Exception {
    mockMvc
        .perform(
            post(CompanyApi.PATH_CREATE_COMPANY)
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
        {
          "name": "Test Company",
          "address": "123 Tech Lane"
        }
        """))
        .andExpect(status().isUnauthorized());
  }
}
