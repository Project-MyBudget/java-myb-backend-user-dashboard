package br.com.mybudget.userdashboard.service.impl;

import br.com.mybudget.userdashboard.model.dto.TotalBudgetDTO;
import br.com.mybudget.userdashboard.repository.BudgetRepository;
import br.com.mybudget.userdashboard.service.BudgetUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
public class BudgetUserServiceImpl implements BudgetUserService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Override
    public TotalBudgetDTO getTotalBudgetAmount(Long idUser) {

        String totalBudgetAmount = budgetRepository.getTotalBudgetAmountAndValueSavedByIdBudget(idUser);
        if (totalBudgetAmount != null) {
            log.info("[USER CHART RESOURCE] Founded Budget Amount.");
            return TotalBudgetDTO
                    .builder()
                    .totalBudgetAmount(new BigDecimal(totalBudgetAmount.split("-")[0]))
                    .totalValueSaved(new BigDecimal(totalBudgetAmount.split("-")[1]))
                    .build();
        }

        log.info("[USER CHART RESOURCE] Could not found Budget Amount.");
        return null;
    }
}
