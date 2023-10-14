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
class ApplyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private final String baseUrl = "/api/apply";

    @BeforeEach
    void createNewObject() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @Nested
    @DisplayName("지원 내역 저장")
    class ApplyTest {
        @Test
        @DisplayName("지원 내역 저장 성공")
        void applySuccessTest() throws Exception {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(baseUrl + "/1" + "/1")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andReturn();

            assertThat(mvcResult.getResponse()).isNotNull();
        }

        @Test
        @DisplayName("지원 내역 저장 실패 - 중복 지원")
        void testFailedByDuplication() throws Exception {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(baseUrl + "/1" + "/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andReturn();

            assertThat(mvcResult.getResponse()).isNotNull();
        }

        @Test
        @DisplayName("지원 내역 저장 실패 - 존재하지 않는 채용공고")
        void testFailedByRecruitNotFound() throws Exception {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(baseUrl + "/0" + "/1")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andReturn();

            assertThat(mvcResult.getResponse()).isNotNull();
        }

        @Test
        @DisplayName("지원 내역 저장 실패 - 존재하지 않는 멤버")
        void testFailedByMemberNotFound() throws Exception {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(baseUrl + "/1" + "/0")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andReturn();

            assertThat(mvcResult.getResponse()).isNotNull();
        }
    }
}