package br.com.ufape.ecommerce.catalog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ufape.ecommerce.catalog.exception.DadoNaoEncontradoException;
import br.com.ufape.ecommerce.catalog.model.Categoria;
import br.com.ufape.ecommerce.catalog.repository.RepositoryCategoria;

@Service
public class ServiceCategoria {
	private final RepositoryCategoria repositorioCategoria;

    public ServiceCategoria(RepositoryCategoria repositorioCategoria) {
        this.repositorioCategoria = repositorioCategoria;
    }

    public List<Categoria> encontrarTodasCategorias() {
        return repositorioCategoria.findAll();
    }
    
    public Categoria encontrarCategoriaPorId(Long id) {
        return repositorioCategoria.findById(id)
            .orElseThrow(() -> new DadoNaoEncontradoException("Categoria com o id (" + id + ") não encontrada."));
    }

    public Categoria criarCategoria(Categoria categoria) {
        return repositorioCategoria.save(categoria);
    }
    
    public Categoria atualizarCategoria(Long id, Categoria categoriaAtualizada) {
        return repositorioCategoria.findById(id)
            .map(categoriaAtual -> {
            	categoriaAtual.setNome(categoriaAtualizada.getNome());
                return repositorioCategoria.save(categoriaAtual);
            })
            .orElseThrow(() -> new DadoNaoEncontradoException("Categoria com o id (" + id + ") não encontrada."));
    }

    public void removerCategoria(Long id) {
    	Categoria categoria = repositorioCategoria.findById(id)
            .orElseThrow(() -> new DadoNaoEncontradoException("Categoria com o id (" + id + ") não encontrada."));
        repositorioCategoria.delete(categoria);
    }
}
