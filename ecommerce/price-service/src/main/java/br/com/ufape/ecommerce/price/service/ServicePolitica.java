package br.com.ufape.ecommerce.price.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ufape.ecommerce.price.exception.DadoNaoEncontradoException;
import br.com.ufape.ecommerce.price.model.Politica;
import br.com.ufape.ecommerce.price.repository.RepositoryPolitica;

@Service
public class ServicePolitica {
	private final RepositoryPolitica repositorioPolitica;

    public ServicePolitica(RepositoryPolitica repositorioPolitica) {
        this.repositorioPolitica = repositorioPolitica;
    }

    public List<Politica> encontrarTodasPoliticas() {
        return repositorioPolitica.findAll();
    }
    
    public Politica encontrarPoliticaPorId(Long id) {
        return repositorioPolitica.findById(id)
            .orElseThrow(() -> new DadoNaoEncontradoException("Política com o id (" + id + ") não encontrada."));
    }

    public Politica criarPolitica(Politica politica) {
        return repositorioPolitica.save(politica);
    }
    
    public Politica atualizarPolitica(Long id, Politica politicaAtualizada) {
        return repositorioPolitica.findById(id)
            .map(politicaAtual -> {
            	politicaAtual.setNome(politicaAtualizada.getNome());
            	politicaAtual.setDescricao(politicaAtualizada.getDescricao());
                return repositorioPolitica.save(politicaAtual);
            })
            .orElseThrow(() -> new DadoNaoEncontradoException("Política com o id (" + id + ") não encontrada."));
    }

    public void removerPolitica(Long id) {
        Politica politica = repositorioPolitica.findById(id)
            .orElseThrow(() -> new DadoNaoEncontradoException("Política com o id (" + id + ") não encontrada."));
        repositorioPolitica.delete(politica);
    }
}
