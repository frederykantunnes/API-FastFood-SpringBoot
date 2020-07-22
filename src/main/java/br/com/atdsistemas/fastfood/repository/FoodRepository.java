package br.com.atdsistemas.fastfood.repository;

import br.com.atdsistemas.fastfood.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
}
