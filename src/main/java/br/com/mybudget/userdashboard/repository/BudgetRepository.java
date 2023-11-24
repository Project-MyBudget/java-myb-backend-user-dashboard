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
    
    @Query(value = "SELECT (BUDGET + VALUE_SAVED) from TB_BUDGET WHERE ID_BUDGET = ?1", nativeQuery = true)
    BigDecimal getTotalBudgetAmountByIdUser(@Param("id") long id);
}
	