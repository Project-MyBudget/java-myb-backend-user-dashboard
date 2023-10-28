package br.com.mybudget.userdashboard.enuns;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum UserMaritalStatusEnum {
	SINGLE('S'),
	MARRIED('M'),
	DIVORCED('D'),
	WIDOWER('W');
	
	private char maritalStatus;
}
