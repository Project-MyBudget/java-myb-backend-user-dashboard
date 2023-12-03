package br.com.mybudget.userdashboard.model;

import br.com.mybudget.userdashboard.enuns.UserMaritalStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BudgetDetails {
    private double totalBudgetAmount;
    private char civilStatus;
    private int childNumber;
    private double spendingLimitEconomy;
}
