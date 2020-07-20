package br.com.atdsistemas.fastfood.repository;

import br.com.atdsistemas.fastfood.model.FoodService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodServiceRepository extends JpaRepository<FoodService, Long> {
}
