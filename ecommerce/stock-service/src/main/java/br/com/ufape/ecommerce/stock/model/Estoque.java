package br.com.ufape.ecommerce.stock.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Estoque {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private long produtoId;
	private int quantidadeDisponivel;
	private int quantidadeMaxima;
	
	@JsonIgnore
	@ManyToOne
	private Armazem armazem;
	
	public Estoque(){}
	
	public Estoque(long produtoId, int quantidadeDisponivel, int quantidadeMaxima) {
		this.produtoId = produtoId;
		this.quantidadeDisponivel = quantidadeDisponivel;
		this.quantidadeMaxima = quantidadeMaxima;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(long idProduto) {
		this.produtoId = idProduto;
	}

	public int getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}

	public void setQuantidadeDisponivel(int quantidadeDisponivel) {
		this.quantidadeDisponivel = quantidadeDisponivel;
	}

	public int getQuantidadeMaxima() {
		return quantidadeMaxima;
	}

	public void setQuantidadeMaxima(int quantidadeMaxima) {
		this.quantidadeMaxima = quantidadeMaxima;
	}

	public Armazem getArmazem() {
		return armazem;
	}

	public void setArmazem(Armazem armazem) {
		this.armazem = armazem;
	}
	
}
