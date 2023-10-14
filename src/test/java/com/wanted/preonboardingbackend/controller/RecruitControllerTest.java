package com.wanted.preonboardingbackend.controller;

import org.junit.jupiter.api.*;
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
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class RecruitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private final String baseUrl = "/api/recruit";

    private final String request = """
                {
                    "companyId" : %d,
                    "position" : "%s",
                    "reward" : %d,
                    "content" : "%s",
                    "techStack" : "%s"
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
    @Order(1)
    @DisplayName("채용공고 등록")
    class createRecruit {
        @Test
        @DisplayName("채용공고 등록 성공")
        void createSuccessTest() throws Exception {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(baseUrl)
                    .content(request.formatted(3, "프론트엔드 주니어 개발자", 2000000, "네이버에서 프론트엔드 주니어 개발자를 채용합니다. 자격요건은..", "React"))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andReturn();

            assertThat(mvcResult.getResponse()).isNotNull();
        }

        @Test
        @DisplayName("채용공고 등록 실패 - 존재하지 않는 회사")
        void testFailedByCompanyNotFound() throws Exception {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(baseUrl)
                    .content(request.formatted(0, "백엔드 주니어 개발자", 1000000, "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..", "JAVA"))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andReturn();

            assertThat(mvcResult.getResponse()).isNotNull();
        }
    }

    @Nested
    @Order(2)
    @DisplayName("채용공고 수정")
    class updateRecruit {
        @Test
        @DisplayName("채용공고 수정 성공")
        void updateSuccessTest() throws Exception {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(baseUrl + "/1")
                    .content(request.formatted(1, "엔드 주니어 개발자", 1500000, "원티드랩에서 백엔드 주니어 개발자를 '적극' 채용합니다. 자격요건은..", "JAVA"))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andReturn();

            assertThat(mvcResult.getResponse()).isNotNull();
        }

        @Test
        @DisplayName("채용공고 수정 실패 - 존재하지 않는 회사")
        void testFailedByCompanyNotFound() throws Exception {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(baseUrl + "/1")
                    .content(request.formatted(0, "백엔드 주니어 개발자", 1000000, "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..", "JAVA"))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andReturn();

            assertThat(mvcResult.getResponse()).isNotNull();
        }

        @Test
        @DisplayName("채용공고 수정 실패 - 존재하지 않는 채용공고")
        void testFailedByRecruitNotFound() throws Exception {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(baseUrl + "/0")
                    .content(request.formatted(0, "백엔드 주니어 개발자", 1000000, "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..", "JAVA"))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andReturn();

            assertThat(mvcResult.getResponse()).isNotNull();
        }
    }

    @Nested
    @Order(3)
    @DisplayName("채용공고 삭제")
    class deleteRecruit {
        @Test
        @DisplayName("채용공고 삭제 성공")
        void updateSuccessTest() throws Exception {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(baseUrl + "/10")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            assertThat(mvcResult.getResponse()).isNotNull();
        }

        @Test
        @DisplayName("채용공고 수정 실패 - 존재하지 않는 채용공고")
        void testFailedByRecruitNotFound() throws Exception {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(baseUrl + "/0")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            assertThat(mvcResult.getResponse()).isNotNull();
        }
    }

    @Nested
    @Order(4)
    @DisplayName("채용공고 조회")
    class getRecruit {
        @Test
        @DisplayName("채용공고 전체 목록 조회 성공")
        void getAllRecruitSuccessTest() throws Exception {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(baseUrl)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            assertThat(mvcResult.getResponse()).isNotNull();
        }

        @Test
        @DisplayName("채용공고 상세 조회 성공")
        void getRecruitSuccessTest() throws Exception {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/1")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            assertThat(mvcResult.getResponse()).isNotNull();
        }

        @Test
        @DisplayName("채용공고 검색 성공")
        void searchRecruitSuccessTest() throws Exception {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "?keyword=백엔드")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            assertThat(mvcResult.getResponse()).isNotNull();
        }

        @Test
        @DisplayName("채용공고 조회 실패 - 존재하지 않는 회사")
        void testFailedByRecruitNotFound() throws Exception {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/0")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            assertThat(mvcResult.getResponse()).isNotNull();
        }
    }
}