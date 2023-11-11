package br.com.mybudget.userdashboard.repository;

import br.com.mybudget.userdashboard.model.entity.BudgetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<BudgetEntity, Long> {
    @Query(value = "SELECT b FROM BudgetEntity b INNER JOIN b.user u WHERE u.idUser = :id")
    BudgetEntity findByIdUser(@Param("id") long id);
}
