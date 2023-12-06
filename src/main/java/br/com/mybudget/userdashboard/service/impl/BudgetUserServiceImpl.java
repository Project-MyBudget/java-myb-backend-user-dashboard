package br.com.mybudget.userdashboard.service.impl;

import br.com.mybudget.userdashboard.model.dto.TotalBudgetDTO;
import br.com.mybudget.userdashboard.repository.BudgetRepository;
import br.com.mybudget.userdashboard.service.BudgetUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Component
public class BudgetUserServiceImpl implements BudgetUserService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Override
    public TotalBudgetDTO getTotalBudgetAmount(Long userId) {

        List<Object[]> totalBudgetAmount = budgetRepository.getTotalBudgetAmountAndValueSavedByIdBudget(userId);
        for (Object[] row : totalBudgetAmount) {
            log.info("[USER CHART RESOURCE] Founded Budget Amount.");
            return TotalBudgetDTO
                    .builder()
                    .totalBudgetAmount((double) row[0])
                    .totalValueSaved((double) row[1])
                    .build();
        }

        log.info("[USER CHART RESOURCE] Could not found Budget Amount.");
        return null;
    }
}
