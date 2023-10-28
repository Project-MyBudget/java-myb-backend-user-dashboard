package br.com.mybudget.userdashboard.service.impl;

import br.com.mybudget.userdashboard.enuns.UserMaritalStatusEnum;
import br.com.mybudget.userdashboard.model.dto.ChartDebtsDTO;
import br.com.mybudget.userdashboard.model.dto.UserChartEnvelopeDTO;
import br.com.mybudget.userdashboard.model.entity.BudgetEntity;
import br.com.mybudget.userdashboard.model.entity.UserEntity;
import br.com.mybudget.userdashboard.repository.UserRepository;
import br.com.mybudget.userdashboard.service.UserChartService;
import br.com.mybudget.userdashboard.utils.CalculateBudgetPerChildUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class UserChartServiceImpl implements UserChartService  {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserChartEnvelopeDTO getUserDebts(Long idUser) {

        return null;
    }

    private ChartDebtsDTO getRecommendedChart(BudgetEntity budget) {
        ChartDebtsDTO chartDebtsDTO = new ChartDebtsDTO();

        // Arrange
        // Pensar melhor nessa lógica
        int children = budget.getUser().getChildrenNumber();
        boolean isSingle =
                Objects.equals(UserMaritalStatusEnum.SINGLE.getMaritalStatus(), budget.getUser().getCivilStatus());
        double percentEssentialsDebts =
                isSingle && children == 0 ? 0.30 : CalculateBudgetPerChildUtils.getBudgetEssentials(children);


        return null;
    }
}
