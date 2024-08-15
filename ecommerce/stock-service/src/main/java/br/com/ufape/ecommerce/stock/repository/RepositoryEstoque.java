package br.com.ufape.ecommerce.stock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ufape.ecommerce.stock.model.Estoque;

public interface RepositoryEstoque extends JpaRepository<Estoque, Long> {
	List<Estoque> findByArmazemId(long armazemId);
}
