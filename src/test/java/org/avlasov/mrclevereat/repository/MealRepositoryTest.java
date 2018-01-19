package org.avlasov.mrclevereat.repository;

import org.avlasov.mrclevereat.entity.ration.Meal;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created By artemvlasov on 31/12/2017
 **/
class MealRepositoryTest extends RepositoryTestCase {

    @Autowired
    private MealRepository mealRepository;

    @Test
    void findByOwnerId_WithExistingOwnerId_ReturnMealPage() {
        Page<Meal> byOwnerId = mealRepository.findByOwnerId(1, Pageable.unpaged());
        assertEquals(2, byOwnerId.getTotalElements());
    }

    @Test
    void findByOwnerId_WithNotExistingOwnerId_ReturnEmptyPage() {
        Page<Meal> byOwnerId = mealRepository.findByOwnerId(2, Pageable.unpaged());
        assertEquals(0, byOwnerId.getTotalElements());
    }

    @Test
    void findByOwnerIdAndCreatedDate() {
        List<Meal> byOwnerIdAndCreatedDate = mealRepository.findByOwnerIdAndCreatedDate(1, LocalDateTime.of(2017, 12, 31, 0, 0));
        assertThat(byOwnerIdAndCreatedDate, IsCollectionWithSize.hasSize(1));
    }

    @Test
    void findByOwnerIdAndCreatedDateBetween() {
        List<Meal> byOwnerIdAndCreatedDateBetween = mealRepository.findByOwnerIdAndCreatedDateBetween(1, LocalDateTime.of(2017, 10, 1, 0, 0), LocalDateTime.of(2018, 1, 1, 0, 0));
        assertThat(byOwnerIdAndCreatedDateBetween, IsCollectionWithSize.hasSize(2));
    }
}