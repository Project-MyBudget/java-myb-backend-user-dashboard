package br.com.mybudget.userdashboard.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExpenseTypeEnum {
    ESSENTIALS_EXPENSE("E"),
    NOT_ESSENTIALS_EXPENSE("N"),
    LEISURE_EXPENSE("L");

    private String type;

    public static ExpenseTypeEnum fromValue(String type) {
        for (ExpenseTypeEnum typeEnum : ExpenseTypeEnum.values()) {
            if (type.equals(typeEnum.type)) {
                return typeEnum;
            }
        }

        return null;
    }
}
