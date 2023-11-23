package br.com.mybudget.userdashboard.model.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TotalBudgetDTO {
	
	private BigDecimal totalAmount;
}
