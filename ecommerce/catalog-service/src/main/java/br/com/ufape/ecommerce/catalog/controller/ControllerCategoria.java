package br.com.ufape.ecommerce.catalog.controller;

import java.util.List;
import br.com.ufape.ecommerce.catalog.repository.RepositoryCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import br.com.ufape.ecommerce.catalog.model.Categoria;

@RestController
@RequestMapping("/api/categorias")
public class ControllerCategoria {

    @Autowired
    RepositoryCategoria categoriaService;

    @PostMapping
    public Categoria criarCategoria(@RequestBody Categoria categoria) {
        return categoriaService.save(categoria);
    }

    @GetMapping
    public List<Categoria> pegarCategoria() {
        return categoriaService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deletarCategoria(@PathVariable Long id) {
        categoriaService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Categoria atualizarCategoria(@PathVariable Long id, @RequestBody Categoria novaCategoria) {
        Categoria categoriaAntiga = categoriaService.findById(id).orElse(null);
        if (categoriaAntiga == null) {
            return null;
        }
        categoriaAntiga.setNome(novaCategoria.getNome());
            return categoriaService.save(categoriaAntiga);
    }
}
