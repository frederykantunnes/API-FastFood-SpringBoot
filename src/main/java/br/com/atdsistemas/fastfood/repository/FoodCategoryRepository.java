package br.com.atdsistemas.fastfood.repository;

import br.com.atdsistemas.fastfood.model.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
    List<FoodCategory> findAllByRestaurant_Id(long id);
}
