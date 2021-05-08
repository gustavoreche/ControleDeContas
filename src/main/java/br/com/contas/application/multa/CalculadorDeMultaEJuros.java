package br.com.contas.application.multa;

import org.springframework.stereotype.Component;

import br.com.contas.model.RegraDeMultaPorConta;

@Component
public class CalculadorDeMultaEJuros {
	
	public RegraDeMultaPorConta calcula(Double valor, int dias, 
			double porcentagemMulta, double porcentagemJuros) {
		double valorOriginal = valor;
		double valorComMulta = valorOriginal + ((valorOriginal * porcentagemMulta) / 100);
		double valorJurosPorDia = (valorComMulta * porcentagemJuros) / 100;
		double valorTotalJuros = valorJurosPorDia * dias;
		double valorCorrigido = valorComMulta + valorTotalJuros;
		return montaObjeto(dias, valorOriginal, porcentagemMulta, valorComMulta,
				porcentagemJuros, valorJurosPorDia, valorCorrigido);
	}

	private RegraDeMultaPorConta montaObjeto(int dias, double valorOriginal, double porcentagemMulta, double valorComMulta,
			double porcentagemJuros, double valorJurosPorDia, double valorCorrigido) {
		return new RegraDeMultaPorConta(dias, valorOriginal, porcentagemMulta, valorComMulta, 
				porcentagemJuros, valorJurosPorDia, valorCorrigido);
	}

}
