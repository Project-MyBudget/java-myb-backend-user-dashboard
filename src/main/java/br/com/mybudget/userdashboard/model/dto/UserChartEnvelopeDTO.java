package br.com.mybudget.userdashboard.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserChartEnvelopeDTO {
    private ChartDebtsDTO chartRecommendedDebts;
    private ChartDebtsDTO chartUserDebts;
}
