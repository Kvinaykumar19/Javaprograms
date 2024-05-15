package com.example.demo.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.UserDataEntity;

public interface UserDataRepositry  extends JpaRepository<UserDataEntity, Integer>{

}
