package br.com.mybudget.userdashboard.model;

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
