package com.quaildev.source.allies.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.ByteArrayInputStream;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class WordsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MobyDickClasspathResourceProvider mockClassPathResourceProvider;

    @Mock
    private ClassPathResource mockResource;

    @Before
    public void setUp() throws Exception {
        given(mockClassPathResourceProvider.get()).willReturn(mockResource);
        given(mockResource.getInputStream()).willReturn(new ByteArrayInputStream("the the a".getBytes()));
    }

    @Test
    public void getRespondsOk() throws Exception {
        mvc.perform(get("/words").accept(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getReturnsContentFromParser() throws Exception {
        mvc.perform(get("/words").accept(APPLICATION_JSON))
                .andExpect(content().json("{ \"the\" : 2, \"a\" : 1 }"));
    }

}