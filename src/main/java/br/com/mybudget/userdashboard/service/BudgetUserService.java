package br.com.mybudget.userdashboard.service;

import br.com.mybudget.userdashboard.model.dto.TotalBudgetDTO;
import org.springframework.stereotype.Service;

@Service
public interface BudgetUserService {
    TotalBudgetDTO getTotalBudgetAmount(Long idUser);
}
