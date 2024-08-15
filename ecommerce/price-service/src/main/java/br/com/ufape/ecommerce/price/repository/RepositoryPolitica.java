package br.com.ufape.ecommerce.price.repository;
import br.com.ufape.ecommerce.price.model.Politica;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryPolitica extends JpaRepository<Politica, Long> {

}
