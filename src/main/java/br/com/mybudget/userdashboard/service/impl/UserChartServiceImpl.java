package br.com.mybudget.userdashboard.service.impl;

import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.mybudget.userdashboard.enuns.UserMaritalStatusEnum;
import br.com.mybudget.userdashboard.model.dto.ChartDebtsDTO;
import br.com.mybudget.userdashboard.model.dto.UserChartEnvelopeDTO;
import br.com.mybudget.userdashboard.model.entity.BudgetEntity;
import br.com.mybudget.userdashboard.repository.BudgetRepository;
import br.com.mybudget.userdashboard.service.UserChartService;
import br.com.mybudget.userdashboard.utils.CalculateBudgetPerChildUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserChartServiceImpl implements UserChartService  {

    @Autowired
    private BudgetRepository budgetRepository;

    @Override
    public UserChartEnvelopeDTO getUserDebts(Long idUser) {
        BudgetEntity budgetEntity = budgetRepository.findByIdUser(idUser);
        ChartDebtsDTO recommendedChart = getRecommendedChart(budgetEntity);
        UserChartEnvelopeDTO userChartEnvelopeDTO = new UserChartEnvelopeDTO();
        userChartEnvelopeDTO.setChartRecommendedDebts(recommendedChart);
        return userChartEnvelopeDTO;
    }

    private ChartDebtsDTO getRecommendedChart(BudgetEntity budget) {
        int children = budget.getUser().getChildrenNumber();
        boolean isSingle = Objects.equals(UserMaritalStatusEnum.SINGLE.getMaritalStatus(), budget.getUser().getCivilStatus());
        double percentEssentialsDebts = isSingle && children == 0 ? 0.30 : CalculateBudgetPerChildUtils.getBudgetEssentials(children);

        double percentNotEssentialsDebts = (1.0 - percentEssentialsDebts) - 0.30;
        double percentLeisure = (1.0 - (percentNotEssentialsDebts + percentEssentialsDebts)) - 0.20;

        double percentsOverall = percentLeisure + percentEssentialsDebts + percentNotEssentialsDebts;
        double percentLimitEconomy = percentsOverall < 1 ? 1 - percentsOverall : 0;

        return ChartDebtsDTO.builder()
                .essentialsDebts(budget.getBudget() * percentEssentialsDebts)
                .notEssentialsDebts(budget.getBudget() * percentNotEssentialsDebts)
                .spendingLimitLeisure(budget.getBudget() * percentLeisure)
                .spendingLimitEconomy(budget.getBudget() * percentLimitEconomy)
                .build();
    }

	@Override
	public BigDecimal getTotalBudgetAmount(Long idUser) {
		
		BigDecimal totalBudgetAmount = budgetRepository.getTotalBudgetAmountByIdUser(idUser);
		if (totalBudgetAmount != null) {
			log.info("[USER CHART RESOURCE] Founded Budget Amount.");
			return totalBudgetAmount;	
		}
		log.info("[USER CHART RESOURCE] Could not found Budget Amount.");
		return totalBudgetAmount;
	}
}
