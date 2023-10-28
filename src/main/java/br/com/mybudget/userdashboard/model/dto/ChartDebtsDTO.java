package br.com.mybudget.userdashboard.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChartDebtsDTO {
    private double essentialsDebts;
    private double notEssentialsDebts;
    private double spendingLimitEconomy;
    private double spendingLimitLeisure;
}
