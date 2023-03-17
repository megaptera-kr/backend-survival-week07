package kr.megaptera.assignment.controllers;

import kr.megaptera.assignment.applications.post.*;
import org.mockito.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.web.servlet.*;

@WebMvcTest
public class PostControllerTest {
    @Mock
    private MockMvc mockMvc;

    @MockBean
    private GetPostDtosService getPostDtosService;

    @MockBean
    private GetPostDtoService getPostDtoService;

    @MockBean
    private CreatePostDtoService createPostDtoService;


    @MockBean
    private UpdatePostDtoService updatePostDtoService;

    @MockBean
    private DeletePostDtoService deletePostDtoService;
    
}
