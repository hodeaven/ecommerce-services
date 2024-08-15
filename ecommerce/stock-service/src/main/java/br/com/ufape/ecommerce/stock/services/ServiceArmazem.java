package br.com.ufape.ecommerce.stock.services;

import br.com.ufape.ecommerce.stock.exception.DadoNaoEncontradoException;
import br.com.ufape.ecommerce.stock.model.Armazem;
import br.com.ufape.ecommerce.stock.repository.RepositoryArmazem;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceArmazem {

    private final RepositoryArmazem repositorioArmazem;

    public ServiceArmazem(RepositoryArmazem repositorioArmazem) {
        this.repositorioArmazem = repositorioArmazem;
    }

    public List<Armazem> encontrarTodosArmazens() {
        return repositorioArmazem.findAll();
    }
    
    public Armazem encontrarArmazemPorId(Long id) {
        return repositorioArmazem.findById(id)
            .orElseThrow(() -> new DadoNaoEncontradoException("Armazém com o id (" + id + ") não encontrado."));
    }

    public Armazem criarArmazem(Armazem warehouse) {
        return repositorioArmazem.save(warehouse);
    }
    
    public Armazem atualizarArmazem(Long id, Armazem armazemAtualizado) {
        return repositorioArmazem.findById(id)
            .map(armazem -> {
            	armazem.setNome(armazemAtualizado.getNome());
            	armazem.setLocal(armazemAtualizado.getLocal());
                return repositorioArmazem.save(armazem);
            })
            .orElseThrow(() -> new DadoNaoEncontradoException("Armazém com o id (" + id + ") não encontrado."));
    }

    public void removerArmazem(Long id) {
    	Armazem warehouse = repositorioArmazem.findById(id)
            .orElseThrow(() -> new DadoNaoEncontradoException("Armazém com o id (" + id + ") não encontrado."));
        repositorioArmazem.delete(warehouse);
    }
}
