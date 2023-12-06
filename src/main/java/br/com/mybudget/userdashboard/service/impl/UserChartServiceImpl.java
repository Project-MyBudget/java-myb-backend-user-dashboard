package br.com.mybudget.userdashboard.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mybudget.userdashboard.enuns.ExpenseTypeEnum;
import br.com.mybudget.userdashboard.enuns.UserMaritalStatusEnum;
import br.com.mybudget.userdashboard.model.BudgetDetails;
import br.com.mybudget.userdashboard.model.dto.ChartDebtsDTO;
import br.com.mybudget.userdashboard.model.dto.UserChartEnvelopeDTO;
import br.com.mybudget.userdashboard.repository.BudgetRepository;
import br.com.mybudget.userdashboard.repository.HistoricRepository;
import br.com.mybudget.userdashboard.service.UserChartService;
import br.com.mybudget.userdashboard.utils.CalculateBudgetPerChildUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserChartServiceImpl implements UserChartService {

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private HistoricRepository historicRepository;

    @Override
    public UserChartEnvelopeDTO getUserDebts(Long userId) {
        BudgetDetails budgetDetails = new BudgetDetails();
        List<Object[]> objBudgetDetails = budgetRepository.getTotalBudgetDetailsById(userId);

        for (Object[] budget : objBudgetDetails) {
            log.info("{} - {} ", budget, budget[1]);
            budgetDetails = BudgetDetails.builder()
                    .totalBudgetAmount((Double) budget[0])
                    .civilStatus((Character) budget[1])
                    .childNumber((Integer) budget[2])
                    .spendingLimitEconomy((Double) budget[3])
                    .build();
        }

        ChartDebtsDTO recommendedChart = getRecommendedChart(budgetDetails);
        ChartDebtsDTO userChart = getUserChart(budgetDetails, userId);

        UserChartEnvelopeDTO userChartEnvelopeDTO = new UserChartEnvelopeDTO();
        userChartEnvelopeDTO.setChartRecommendedDebts(recommendedChart);
        userChartEnvelopeDTO.setChartUserDebts(userChart);
        return userChartEnvelopeDTO;
    }

    private ChartDebtsDTO getRecommendedChart(BudgetDetails budget) {
        try {
            log.info("[INFO] Creating recommended chart to user");
            int children = budget.getChildNumber();
            boolean isSingle = budget.getCivilStatus() == UserMaritalStatusEnum.SINGLE.getMaritalStatus();
            double percentEssentialsDebts = isSingle && children == 0 ? 0.30 : CalculateBudgetPerChildUtils.getBudgetEssentials(children);

            double percentNotEssentialsDebts = (1.0 - percentEssentialsDebts) - 0.30;
            double percentLeisure = (1.0 - (percentNotEssentialsDebts + percentEssentialsDebts)) - 0.20;

            double percentsOverall = percentLeisure + percentEssentialsDebts + percentNotEssentialsDebts;
            double percentLimitEconomy = percentsOverall < 1 ? 1 - percentsOverall : 0;

            return ChartDebtsDTO.builder()
                    .essentialsDebts(budget.getTotalBudgetAmount() * percentEssentialsDebts)
                    .notEssentialsDebts(budget.getTotalBudgetAmount() * percentNotEssentialsDebts)
                    .spendingLimitLeisure(budget.getTotalBudgetAmount() * percentLeisure)
                    .spendingLimitEconomy(budget.getTotalBudgetAmount() * percentLimitEconomy)
                    .build();
        } catch (Exception ex) {
            log.error("[ERROR] Error to create recommenced chart. More details: {} ", ex.getMessage());
            log.error(ex.getMessage(), ex);
        }

        return null;
    }

    private ChartDebtsDTO getUserChart(BudgetDetails budget, Long userId) {
        ChartDebtsDTO chartDebts = new ChartDebtsDTO();
        try {
            List<Object[]> expenses = historicRepository.findUserChart(userId);
            chartDebts.setSpendingLimitEconomy(budget.getSpendingLimitEconomy());
            for (Object[] row : expenses) {
                try {
                    ExpenseTypeEnum type = ExpenseTypeEnum.fromValue(String.valueOf(row[0]));

                    if (ExpenseTypeEnum.LEISURE_EXPENSE == type) {
                        chartDebts.setSpendingLimitLeisure((Double) row[1]);
                    } else if (ExpenseTypeEnum.ESSENTIALS_EXPENSE == type) {
                        chartDebts.setEssentialsDebts((Double) row[1]);
                    } else if (ExpenseTypeEnum.NOT_ESSENTIALS_EXPENSE == type) {
                        chartDebts.setNotEssentialsDebts((Double) row[1]);
                    }
                } catch (Exception ex) {
                    log.error("[ERROR] Error to get chart field. More details: {}", ex.getMessage());
                    log.error(ex.getMessage(), ex);
                }
            }
        } catch (Exception ex) {
            log.error("[ERROR] Error to get user chart: {} ", ex.getMessage());
        }

        return chartDebts;
    }
}
