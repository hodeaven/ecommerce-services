package br.com.ufape.ecommerce.price.repository;
import br.com.ufape.ecommerce.price.model.Preco;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryPreco extends JpaRepository<Preco, Long> {

}
