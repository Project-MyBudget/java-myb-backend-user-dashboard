package br.com.mybudget.userdashboard.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CalculateBudgetPerChildUtils {

    private static final int CHILDREN_NUMBER_THREE = 3;
    private static final int CHILDREN_NUMBER_ONE = 1;

    public static double getBudgetEssentials(int childNumber) {
        double budgetPercent = 0.30;

        if (childNumber == CHILDREN_NUMBER_ONE) {
            budgetPercent += 0.10;
        } else if (childNumber > CHILDREN_NUMBER_ONE && childNumber <= CHILDREN_NUMBER_THREE) {
            budgetPercent += 0.15;
        } else if (childNumber > CHILDREN_NUMBER_THREE){
            budgetPercent += 0.30;
        }

        return budgetPercent;
    }
}
