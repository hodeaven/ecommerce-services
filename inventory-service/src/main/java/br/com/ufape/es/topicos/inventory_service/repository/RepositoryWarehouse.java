package br.com.ufape.es.topicos.inventory_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ufape.es.topicos.inventory_service.model.Warehouse;

public interface RepositoryWarehouse extends JpaRepository<Warehouse, Long> {
    
}
