package br.com.mybudget.userdashboard.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.mybudget.userdashboard.model.entity.ExpenseEntity;

@Repository
public interface HistoricRepository extends JpaRepository<ExpenseEntity, Long>{

	@Query(value = "SELECT YEAR(DATE_REFERENCE) AS Year, MONTH(DATE_REFERENCE) AS Month, SUM(VALUE) AS Total "
			+ "FROM TB_EXPENSES "
			+ "WHERE ID_USER = ?1 "
			+ "AND DATE_REFERENCE >= ?2 "
			+ "GROUP BY Year, Month "
			+ "ORDER BY Year, Month ASC", nativeQuery = true)
	List<Object[]> findAllExpensesLastSixMonths(Long userId, Date dateLastSixMonths);

	@Query(value = "SELECT ex.type, SUM(e.value) " +
			" FROM TB_USER u " +
			"    INNER JOIN TB_EXPENSES e ON e.id_user = u.id_user " +
			"    INNER JOIN TB_EXPENSES_TYPE ex ON e.id_exp_type = ex.id_exp_type " +
			" WHERE u.id_user = ?1 AND e.STATUS = 'A' AND MONTH(e.DATE_REFERENCE) = EXTRACT(month FROM (NOW())) " +
			" GROUP BY ex.type ", nativeQuery = true)
	List<Object[]> findUserChart(Long userId);
}
