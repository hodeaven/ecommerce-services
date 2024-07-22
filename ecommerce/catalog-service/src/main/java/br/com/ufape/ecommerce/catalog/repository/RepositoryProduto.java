package br.com.ufape.ecommerce.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.ufape.ecommerce.catalog.model.Produto;

public interface RepositoryProduto extends JpaRepository<Produto, Long>{
}
