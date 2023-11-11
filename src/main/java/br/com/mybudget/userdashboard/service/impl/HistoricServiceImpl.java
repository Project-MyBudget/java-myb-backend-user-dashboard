package br.com.mybudget.userdashboard.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mybudget.userdashboard.model.dto.HistoricLastSixMonthsDTO;
import br.com.mybudget.userdashboard.repository.HistoricRepository;
import br.com.mybudget.userdashboard.service.HistoricService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class HistoricServiceImpl implements HistoricService{
	
	@Autowired
	private HistoricRepository expenseRepository;
	
	
	@Override
	public List<HistoricLastSixMonthsDTO> getHistoricExpenses(long userId) {
		try {
			log.info("[GET HISTORIC LAST SIX MONTHS] Searching for all User({}) expenses over the last six months",
					userId);

			Date dateLastSixMonths = new Date(System.currentTimeMillis() - (6L * 30 * 24 * 60 * 60 * 1000));
			List<Object[]> response = expenseRepository.findAllExpensesLastSixMonths(userId, dateLastSixMonths);

			if (response != null && !response.isEmpty()) {

				List<HistoricLastSixMonthsDTO> listHistoricLastSixMonths = new ArrayList<>();
				HistoricLastSixMonthsDTO historicLastSixMonthsDto = new HistoricLastSixMonthsDTO();

				for (Object[] row : response) {
					try {
						Integer year = (Integer) row[0];
						Integer month = (Integer) row[1];
						Double totalValue = (Double) row[2];

						String date = month + "/" + year;

						historicLastSixMonthsDto.setMonth(month);
						historicLastSixMonthsDto.setYear(year);
						historicLastSixMonthsDto.setTotalValueExpences(totalValue);

						listHistoricLastSixMonths.add(historicLastSixMonthsDto);

						log.info("[HISTORIC LAST SIX MONTHS] Sucess get historic expenses! User({}) = {} - $ {}",
								userId, date, totalValue);
					} catch (Exception e) {
						log.error("[ERROR GET HISTORIC] Error get historic expenses! User({})", userId);
					}
				}
				return listHistoricLastSixMonths;
			}
			log.info("[HISTORIC IS EMPTY] There is no history of expenses for the User({})", userId);
		} catch (Exception e) {
			log.error("[ERROR] Error searching for all User({}) expenses over the last six months - [ERROR]: {}", userId, e);
		}
		return null;
	}

}
