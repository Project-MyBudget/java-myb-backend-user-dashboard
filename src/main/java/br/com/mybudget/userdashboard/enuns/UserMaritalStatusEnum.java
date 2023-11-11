package br.com.mybudget.userdashboard.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserMaritalStatusEnum {
	SINGLE('S'),
	MARRIED('M'),
	DIVORCED('D'),
	WIDOWER('W');
	
	private char maritalStatus;
}
