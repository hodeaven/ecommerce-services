package br.com.ufape.es.topicos.product_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ufape.es.topicos.product_service.model.Product;

@Repository
public interface RepositoryProduct extends JpaRepository<Product, Long> {
    Product findByName(String name);
}
