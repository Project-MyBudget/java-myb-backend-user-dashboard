package br.com.mybudget.userdashboard.repository;

import br.com.mybudget.userdashboard.model.BudgetDetails;
import br.com.mybudget.userdashboard.model.entity.BudgetEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<BudgetEntity, Long> {
    @Query(value = "SELECT b FROM BudgetEntity b INNER JOIN b.user u WHERE u.idUser = :id")
    BudgetEntity findByIdUser(@Param("id") long id);

    @Query(
            value = "SELECT " +
                    "   b.BUDGET + b.VALUE_SAVED + e.SALARY as totalBudgetAmount, " +
                    "   u.STATUS_CIVIL civilStatus, " +
                    "   u.CHILDREN_NUMBER childNumber, " +
                    "   b.SPENDING_LIMIT_ECONOMY " +
                    "FROM TB_BUDGET b " +
                    "   INNER JOIN TB_USER u ON u.ID_USER = b.ID_USER " +
                    "   INNER JOIN TB_EMPLOYMENTS e ON e.ID_USER = u.ID_USER " +
                    "WHERE b.ID_USER = ?1",
            nativeQuery = true)
    List<Object[]> getTotalBudgetDetailsById(long id);
    
    @Query(
    		value = "SELECT SUM(b.BUDGET + b.VALUE_SAVED + e.SALARY), b.VALUE_SAVED" +
                    " FROM TB_BUDGET b " +
                    "   JOIN TB_USER u ON u.ID_USER = b.ID_USER " +
                    "   JOIN TB_EMPLOYMENTS e ON e.ID_USER = u.ID_USER " +
                    " WHERE b.ID_USER = ?1 ",
    		nativeQuery = true)
    List<Object[]> getTotalBudgetAmountAndValueSavedByIdBudget(@Param("id") long id);
}
	