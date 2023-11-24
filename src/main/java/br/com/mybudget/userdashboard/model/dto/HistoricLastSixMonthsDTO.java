package br.com.mybudget.userdashboard.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HistoricLastSixMonthsDTO {

	private Integer year;
	private Integer month;
	private double totalValueExpenses;
}
