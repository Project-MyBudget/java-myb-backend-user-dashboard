package br.com.mybudget.userdashboard.model.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_BUDGET")
public class BudgetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BUDGET")
    private long id;

    @Column(name = "BUDGET", nullable = false)
    private double budget;

    @Column(name = "VALUE_SAVED", nullable = true)
    private double valueSaved;

    @Column(name = "SPENDING_LIMIT_ECONOMY", nullable = true)
    private double spendingLimitEconomy;

    @OneToOne
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER")
    private UserEntity user;
}