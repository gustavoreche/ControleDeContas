package br.com.contas.application.multa;

import br.com.contas.model.RegraDeMultaPorConta;

public interface Multa {

	RegraDeMultaPorConta valorComMulta(int dias, Double valor);
	
}
