package br.com.mybudget.userdashboard.repository;

import br.com.mybudget.userdashboard.model.entity.BudgetEntity;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<BudgetEntity, Long> {
    @Query(value = "SELECT b FROM BudgetEntity b INNER JOIN b.user u WHERE u.idUser = :id")
    BudgetEntity findByIdUser(@Param("id") long id);
    
    @Query(
    		value = "SELECT concat(b.BUDGET + b.VALUE_SAVED + e.SALARY, '-', b.VALUE_SAVED) " +
                    " FROM TB_BUDGET b " +
                    "   JOIN TB_USER u ON u.ID_USER = b.ID_USER " +
                    "   JOIN TB_EMPLOYMENTS e ON e.ID_USER = u.ID_USER " +
                    " WHERE b.ID_USER = ?1 ",
    		nativeQuery = true)
    String getTotalBudgetAmountAndValueSavedByIdBudget(@Param("id") long id);
}
	