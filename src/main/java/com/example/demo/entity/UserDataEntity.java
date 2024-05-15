package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "useData")
public class UserDataEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int useid;
	private String name;
	private String addres;
	private String policyno;
	private String mobileno;
	private String createDate;
	private String recodStatus;
	private String updateDate;

	public UserDataEntity(int i, String johnDoe, String s, String number, String p123456789, String active, LocalDateTime parse) {
	this.useid = i;
	this.name = johnDoe;
	this.addres = s;
	this.policyno = number;
	this.mobileno = p123456789;
	this.recodStatus = active;
	this.createDate = parse.toString();
	}
}
