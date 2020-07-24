package br.com.atdsistemas.fastfood.repository;

import br.com.atdsistemas.fastfood.model.Additional;
import br.com.atdsistemas.fastfood.model.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AdditionalRepository extends JpaRepository<Additional, Long> {
    List<Additional> findAllByRestaurant_Id(long id);
}
