package br.com.ufape.ecommerce.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.ufape.ecommerce.stock.model.Estoque;
import br.com.ufape.ecommerce.stock.repository.RepositoryArmazem;
import br.com.ufape.ecommerce.stock.repository.RepositoryEstoque;

@RestController
@RequestMapping("/api")
public class ControllerEstoque {

	@Autowired
	private RepositoryEstoque estoqueService;
	
	@Autowired
	private RepositoryArmazem armazemService;
	
	@GetMapping("/estoques")
	public List<Estoque> listarEstoque() {
		return estoqueService.findAll();
	}
	
	@GetMapping("/estoques/{id}")
	public Estoque buscarEstoque(@PathVariable long id) {
		return estoqueService.findById(id).orElse(null);
	}
	
	@DeleteMapping("/estoques/{id}")
	public void apagarEstoque(@PathVariable long id) {
		estoqueService.deleteById(id);
	}
	
	@PostMapping("/armazens/{id}/estoques")
	public Estoque criarNovoEstoque(@PathVariable long id, @RequestBody Estoque novoEstoque) {
		if(armazemService.findById(id).equals(null)) {
			return null;
		} else {
			novoEstoque.setArmazem(armazemService.findById(id).orElse(null));
			return estoqueService.save(novoEstoque);
		}
	}
	
	@GetMapping("/armazens/{id}/estoques")
	public List<Estoque> listarTodosEstoquesDeUmArmazem(@PathVariable long id) {
		return estoqueService.findByArmazemId(id);
	}
	
	@PutMapping("/estoques/{id}")
	public Estoque atualizarEstoque(@PathVariable long id, @RequestBody Estoque novoEstoque) {
		Estoque estoqueAntigo = estoqueService.findById(id).orElse(null);
		if(estoqueAntigo.equals(null)) return null;
		estoqueAntigo.setProdutoId(novoEstoque.getProdutoId());
		estoqueAntigo.setQuantidadeDisponivel(novoEstoque.getQuantidadeDisponivel());
		estoqueAntigo.setQuantidadeMaxima(novoEstoque.getQuantidadeMaxima());
		return estoqueService.save(estoqueAntigo);
	}
	
}
