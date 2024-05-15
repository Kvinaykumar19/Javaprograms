package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDisplayDto;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserDataService;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserDataService userDataService;

	@GetMapping("/hai")
	public String sayhai() {
		return "HAI TO ALL ";
	}

	@PostMapping("/save")
	public ResponseEntity<UserDisplayDto> saveUser(@RequestBody UserDto userDto) {
		UserDisplayDto savedUserDto = userDataService.saveUserDto(userDto);
		return new ResponseEntity<>(savedUserDto, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<UserDisplayDto>> getAllUsers() {
		List<UserDisplayDto> userDtoList = userDataService.getAllUserDtos();
		return new ResponseEntity<>(userDtoList, HttpStatus.OK);
	}
}
