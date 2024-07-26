package br.com.ufape.ecommerce.price.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ufape.ecommerce.price.model.Politica;

public interface RepositoryPolitica extends JpaRepository<Politica, Long> {

    //List<Politica> findByPrecoProdutoId(long id);
}
