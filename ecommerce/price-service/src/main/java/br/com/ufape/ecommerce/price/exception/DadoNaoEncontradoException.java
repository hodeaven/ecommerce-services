package br.com.ufape.ecommerce.price.exception;

public class DadoNaoEncontradoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public DadoNaoEncontradoException(String message) {
        super(message);
    }
}
