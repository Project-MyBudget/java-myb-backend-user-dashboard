package br.com.mybudget.userdashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.mybudget.userdashboard.model.dto.HistoricLastSixMonthsDTO;
import br.com.mybudget.userdashboard.service.HistoricService;

@RestController
public class HistoricController {

	@Autowired
	private HistoricService expensesService;
	
	/**
	 * This method is responsible for bringing a user's expenses from the last six months.
	 * 
	 * @return
	 */
	@GetMapping(value = "/historic/{userId}", produces = "application/json")
	public ResponseEntity<List<HistoricLastSixMonthsDTO>> getHistoricExpenses(@PathVariable long userId) {
		List<HistoricLastSixMonthsDTO> response = expensesService.getHistoricExpenses(userId);

		if (response != null) {
			return ResponseEntity.ok(response);
		}

		return ResponseEntity.notFound().build();
	}
}
