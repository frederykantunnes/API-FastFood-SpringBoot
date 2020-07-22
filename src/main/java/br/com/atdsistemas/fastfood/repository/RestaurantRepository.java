package br.com.atdsistemas.fastfood.repository;

import br.com.atdsistemas.fastfood.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Page<Restaurant> findAllByServices_Id(Long id, Pageable pageable);
}
