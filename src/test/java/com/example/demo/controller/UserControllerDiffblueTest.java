package com.example.demo.controller;

import static org.mockito.Mockito.when;

import com.example.demo.dto.UserDisplayDto;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserDataService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class UserControllerTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserDataService userDataService;

    /**
     * Method under test: {@link UserController#saveUser(UserDto)}
     */
    @Test
    void testSaveUser() throws Exception {
        // Arrange
        when(userDataService.saveUserDto(Mockito.<UserDto>any()))
                .thenReturn(new UserDisplayDto(1, "Name", "42 Main St", "Policyno", "Mobileno"));

        UserDto userDto = new UserDto();
        userDto.setAddres("42 Main St");
        userDto.setMobileno("Mobileno");
        userDto.setName("Name");
        userDto.setPolicyno("Policyno");
        String content = (new ObjectMapper()).writeValueAsString(userDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"name\":\"Name\",\"addres\":\"42 Main St\",\"policyno\":\"Policyno\",\"mobileno\":\"Mobileno\"}"));
    }
}
