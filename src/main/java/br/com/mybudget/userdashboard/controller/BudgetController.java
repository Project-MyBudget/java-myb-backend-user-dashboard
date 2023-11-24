package br.com.mybudget.userdashboard.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.mybudget.userdashboard.model.dto.TotalBudgetDTO;
import br.com.mybudget.userdashboard.repository.BudgetRepository;
import br.com.mybudget.userdashboard.service.UserChartService;

@RestController
public class BudgetController {

	@Autowired
	private UserChartService userChartService;
	
	@GetMapping(value = "/total/{idUser}", produces = "application/json")
	public ResponseEntity<TotalBudgetDTO> sum(@PathVariable Long idUser) {
		
		BigDecimal res = userChartService.getTotalBudgetAmount(idUser);
		
		if (res == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(TotalBudgetDTO
															.builder()
															.totalAmount(res)
															.build());
	}
}
