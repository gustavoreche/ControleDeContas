package br.com.contas.application.multa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.contas.model.RegraDeMultaPorConta;

@Component
@Qualifier("ateTres")
public class MultaAteTresDiasDeAtraso implements Multa {
	
	private Multa proximaMulta;
	private CalculadorDeMultaEJuros calculadorDeMultaEJuros;
	
	@Autowired
	public MultaAteTresDiasDeAtraso(@Qualifier("ateCinco") Multa multa,
			CalculadorDeMultaEJuros calculadorDeMultaEJuros) {
		this.proximaMulta = multa;
		this.calculadorDeMultaEJuros = calculadorDeMultaEJuros;
	}

	@Override
	public RegraDeMultaPorConta valorComMulta(int dias, Double valor) {
		if(dias <= 3) {
			return this.calculadorDeMultaEJuros.calcula(valor, dias, 2, 0.1);
		}
		return this.proximaMulta.valorComMulta(dias, valor);
	}

}
