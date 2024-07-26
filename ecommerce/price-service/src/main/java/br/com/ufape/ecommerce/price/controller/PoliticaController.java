package br.com.ufape.ecommerce.price.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ufape.ecommerce.price.model.Politica;
import br.com.ufape.ecommerce.price.repository.RepositoryPolitica;
import br.com.ufape.ecommerce.price.repository.RepositoryPrecoProduto;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/politicas")
public class PoliticaController {
    
    @Autowired
    private RepositoryPolitica politicaRepository;

    @Autowired
    private RepositoryPrecoProduto precoProdutoRepository;
    
    
    @GetMapping
    public List<Politica> getPolitica() {
        return politicaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Politica getPoliticaById(@RequestParam long id) {
        return this.politicaRepository.findById(id).orElse(null);
    }

    @PostMapping
    public String addPolitica(@RequestBody Politica politica) {
        politica.setPrecos(precoProdutoRepository.findById(politica.precoProdutoId).orElse(null));
        politicaRepository.save(politica);
        return "Política adicionada com sucesso!";
    }
    
    @PostMapping("/preco/politica/{id}")
    public Politica associatePrecoProduto(@PathVariable long id, @RequestBody Politica politica) {
		if(precoProdutoRepository.findById(id).equals(null)) {
			return null;
		} else {
			politica.setPrecos(precoProdutoRepository.findById(id).orElse(null));
			return politicaRepository.save(politica);
		}
	}

    
    @DeleteMapping("/{id}")
    public String deletePolitica(@RequestParam long id) {
        this.politicaRepository.deleteById(id);
        return "Política excluída com sucesso!";
    }

    @PutMapping("/{id}")
    public String putPolitica(@RequestBody Politica novaPolitica) {
        Politica politica = politicaRepository.findById(novaPolitica.getId()).orElse(null);
        if (politica != null) {
            politica.setNome(novaPolitica.getNome());
            politica.setDescricao(novaPolitica.getDescricao());
            politica.setTipo(novaPolitica.getTipo());
            politicaRepository.save(politica);

            
            return "Política atualizada com sucesso!";
        }
        
        return null;
    }
}
