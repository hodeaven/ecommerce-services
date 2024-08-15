package br.com.ufape.ecommerce.catalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.ufape.ecommerce.catalog.model.Produto;

public interface RepositoryProduto extends JpaRepository<Produto, Long>{
	public List<Produto> findAllByCategoriaId(Long categoriaId);
}
