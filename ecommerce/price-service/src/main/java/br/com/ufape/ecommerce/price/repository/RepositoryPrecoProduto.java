package br.com.ufape.ecommerce.price.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ufape.ecommerce.price.model.PrecoProduto;


public interface RepositoryPrecoProduto extends JpaRepository<PrecoProduto, Long> {

    //List<PrecoProduto> findByPoliticaId(long id);
}
