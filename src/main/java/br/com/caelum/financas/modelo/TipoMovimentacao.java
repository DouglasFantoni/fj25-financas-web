package br.com.caelum.financas.modelo;

public enum TipoMovimentacao {

	ENTRADA("Entrada"), SAIDA("Saida");

	private String tipo;

	TipoMovimentacao(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	@Override
	public String toString() {
		return getTipo();
	}
}
