package br.com.ufape.ecommerce.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ufape.ecommerce.stock.model.Armazem;

public interface RepositoryArmazem extends JpaRepository<Armazem, Long>{

}
