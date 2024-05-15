package com.example.demo.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDisplayDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserDataEntity;
import com.example.demo.repositry.UserDataRepositry;
import com.example.demo.service.UserDataService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDataServiceImpl implements UserDataService {

	@Autowired
	private UserDataRepositry repositry;

	@Override
	public UserDisplayDto saveUserDto(UserDto dto) {
		UserDataEntity entity = new UserDataEntity();
		entity.setAddres(dto.getAddres());
		entity.setMobileno(dto.getMobileno());
		entity.setPolicyno(dto.getPolicyno());
		entity.setRecodStatus("ACTIVE");
		entity.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		entity.setName(dto.getName());
		UserDataEntity save = repositry.save(entity);

		return mapEntityToDto(save);
	}

	@Override
	public List<UserDisplayDto> getAllUserDtos() {
		return repositry.findAll().stream().filter(i -> i.getRecodStatus().equals("ACTIVE")).map(UserDataServiceImpl::mapEntityToDto).toList();
	}

	public static UserDisplayDto mapEntityToDto(UserDataEntity entity) {
		UserDisplayDto dto = new UserDisplayDto();
		dto.setName(entity.getName());
		dto.setAddres(entity.getAddres());
		dto.setPolicyno(entity.getPolicyno());
		dto.setMobileno(entity.getMobileno());
		dto.setId(entity.getUseid());
		return dto;
	}
}
