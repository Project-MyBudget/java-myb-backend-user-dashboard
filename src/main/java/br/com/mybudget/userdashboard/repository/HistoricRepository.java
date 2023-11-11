package br.com.mybudget.userdashboard.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.mybudget.userdashboard.model.entity.ExpenseEntity;

@Repository
public interface HistoricRepository extends JpaRepository<ExpenseEntity, Long>{

	@Query(value = "SELECT YEAR(exp.DATE_REFERENCE), MONTH(exp.DATE_REFERENCE), SUM(exp.VALUE) "
			+ "FROM TB_EXPENSES exp "
			+ "WHERE exp.ID_USER = ?1 "
			+ "AND exp.DATE_REFERENCE >= ?2 "
			+ "GROUP BY YEAR(exp.DATE_REFERENCE), MONTH(exp.DATE_REFERENCE) "
			+ "ORDER BY YEAR(exp.DATE_REFERENCE), MONTH(exp.DATE_REFERENCE) DESC", nativeQuery = true)
	List<Object[]> findAllExpensesLastSixMonths(Long idUser, Date dateLastSixMonths);
}
