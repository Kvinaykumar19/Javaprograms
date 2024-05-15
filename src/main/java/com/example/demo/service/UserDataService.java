package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.UserDisplayDto;
import com.example.demo.dto.UserDto;

public interface UserDataService {

	UserDisplayDto saveUserDto(UserDto dto);

	List<UserDisplayDto> getAllUserDtos();
}
