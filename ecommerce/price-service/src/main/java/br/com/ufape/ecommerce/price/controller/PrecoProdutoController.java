package br.com.ufape.ecommerce.price.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.ufape.ecommerce.price.model.Politica;
import br.com.ufape.ecommerce.price.model.PrecoProduto;
import br.com.ufape.ecommerce.price.repository.RepositoryPolitica;
import br.com.ufape.ecommerce.price.repository.RepositoryPrecoProduto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/precos")
public class PrecoProdutoController {
    
    @Autowired
    private RepositoryPrecoProduto precoProdutoRepository;

    @Autowired
    private RepositoryPolitica politicaRepository;

    @GetMapping
    public List<PrecoProduto> getPreco() {
        return precoProdutoRepository.findAll();
    }

    @GetMapping("/{id}")
    public PrecoProduto getPrecoById(@PathVariable long id) {
        return this.precoProdutoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public String addPreco(@RequestBody PrecoProduto preco) {
        Politica politica = politicaRepository.findById(preco.politicaId).orElse(null);
        System.out.println("politica: " + politica);
        preco.setPoliticas(politica);
        this.precoProdutoRepository.save(preco);
        return "Preço adicionado com sucesso!";
    }
    
   
    @DeleteMapping("/{id}")
    public String deletePreco(@PathVariable long id) {
        this.precoProdutoRepository.deleteById(id);
        //preco id excluido com sucesso
        return "Preço excluído com sucesso!";
    }

    @PutMapping("/{id}")
    public String putPreco(@PathVariable String id, @RequestBody PrecoProduto novoPreco) {
        PrecoProduto preco = precoProdutoRepository.findById(Long.parseLong(id)).orElse(null);
        if (preco != null) {
            preco.setPreco(novoPreco.getPreco());
            precoProdutoRepository.save(preco);
            return "Preço atualizado com sucesso!";
        }
        
        return null;
    }


    
}
