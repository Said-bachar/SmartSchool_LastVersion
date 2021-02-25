package com.ensa.SmartSchool.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

	private String username;
	private String password;
	private int adminId;
	
}
