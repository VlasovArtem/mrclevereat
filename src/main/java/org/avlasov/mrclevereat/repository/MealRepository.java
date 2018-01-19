package org.avlasov.mrclevereat.repository;

import org.avlasov.mrclevereat.entity.ration.Meal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created By artemvlasov on 29/12/2017
 **/
public interface MealRepository extends JpaRepository<Meal, Long> {

    Page<Meal> findByOwnerId(long id, Pageable pageable);
    List<Meal> findByOwnerIdAndCreatedDate(long id, LocalDate localDate);
    List<Meal> findByOwnerIdAndCreatedDateBetween(long id, LocalDate from, LocalDate to);

}