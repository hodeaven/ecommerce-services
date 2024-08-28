package br.com.ufape.es.topicos.price_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ufape.es.topicos.price_service.model.Price;

public interface RepositoryPrice extends JpaRepository<Price, Long> {
    
}
