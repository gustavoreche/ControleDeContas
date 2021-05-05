package br.com.contas.application.multa;

public interface Multa {

	Double valorComMulta(int dias, Double valor);
	void proximaMulta(Multa proximaMulta);
	
}
