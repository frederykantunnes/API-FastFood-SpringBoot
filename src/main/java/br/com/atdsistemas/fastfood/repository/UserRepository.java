package br.com.atdsistemas.fastfood.repository;

import br.com.atdsistemas.fastfood.model.Additional;
import br.com.atdsistemas.fastfood.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
