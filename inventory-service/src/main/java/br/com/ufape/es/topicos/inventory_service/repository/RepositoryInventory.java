package br.com.ufape.es.topicos.inventory_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ufape.es.topicos.inventory_service.model.Inventory;

public interface RepositoryInventory extends JpaRepository<Inventory, Long> {
    Inventory findByProductId(Long productId);
    Inventory findAllByWarehouseId(Long WarehouseId);
}
