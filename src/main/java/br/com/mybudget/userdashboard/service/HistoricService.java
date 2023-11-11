package br.com.mybudget.userdashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.mybudget.userdashboard.model.dto.HistoricLastSixMonthsDTO;

@Service
public interface HistoricService {

	public List<HistoricLastSixMonthsDTO> getHistoricExpenses(long userId);
}
