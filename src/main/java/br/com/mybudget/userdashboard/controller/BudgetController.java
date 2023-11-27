package br.com.mybudget.userdashboard.controller;

import br.com.mybudget.userdashboard.service.BudgetUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.mybudget.userdashboard.model.dto.TotalBudgetDTO;

@RestController
public class BudgetController {

	@Autowired
	private BudgetUserService budgetUserService;
	
	@GetMapping(value = "/total/{idUser}", produces = "application/json")
	public ResponseEntity<TotalBudgetDTO> getSumAndSavedValue(@PathVariable Long idUser) {
		
		TotalBudgetDTO res = budgetUserService.getTotalBudgetAmount(idUser);
		
		if (res == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
}
