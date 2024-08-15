package br.com.ufape.ecommerce.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.ufape.ecommerce.catalog.model.Categoria;

public interface RepositoryCategoria extends JpaRepository<Categoria, Long>{
	
}
