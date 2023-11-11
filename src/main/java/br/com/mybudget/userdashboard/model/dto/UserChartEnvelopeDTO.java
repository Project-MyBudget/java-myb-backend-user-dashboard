package br.com.mybudget.userdashboard.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserChartEnvelopeDTO  implements Serializable {
    private ChartDebtsDTO chartRecommendedDebts;
    private ChartDebtsDTO chartUserDebts;
}
