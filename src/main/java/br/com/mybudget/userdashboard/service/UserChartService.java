package br.com.mybudget.userdashboard.service;

import org.springframework.stereotype.Service;

import br.com.mybudget.userdashboard.model.dto.TotalBudgetDTO;
import br.com.mybudget.userdashboard.model.dto.UserChartEnvelopeDTO;

@Service
public interface UserChartService {
    UserChartEnvelopeDTO  getUserDebts(Long idUser);
    TotalBudgetDTO getTotalBudgetAmount(Long idUser);
}
