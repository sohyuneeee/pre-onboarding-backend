package com.wanted.preonboardingbackend.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private final String baseUrl = "/api/company";

    private final String request = """
                {
                  "companyName" : "%s",
                  "country" : "%s",
                  "city" : "%s"
                }
                """;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @Nested
    @DisplayName("회사 등록")
    class createCompany {
        @Test
        @DisplayName("회사 등록 성공")
        void signupSuccessTest() throws Exception {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(baseUrl)
                    .content(request.formatted("카카오", "한국", "판교"))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andReturn();

            assertThat(mvcResult.getResponse()).isNotNull();
        }

        @Test
        @DisplayName("회사 등록 실패 - 중복된 회사 이름")
        void testFailedByDuplication() throws Exception {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(baseUrl)
                    .content(request.formatted("원티드랩", "한국", "서울"))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andReturn();

            assertThat(mvcResult.getResponse()).isNotNull();
        }
    }
}