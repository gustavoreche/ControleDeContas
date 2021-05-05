package br.com.contas.application.multa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("ateCinco")
public class MultaMaisDeTresDiasAteCincoDiasDeAtraso implements Multa {
	
	private Multa proximaMulta;
	private CalculadorDeMultaEJuros calculadorDeMultaEJuros;
	
	@Autowired
	public MultaMaisDeTresDiasAteCincoDiasDeAtraso(@Qualifier("maisDeCinco") Multa multa,
			CalculadorDeMultaEJuros calculadorDeMultaEJuros) {
		this.proximaMulta = multa;
		this.calculadorDeMultaEJuros = calculadorDeMultaEJuros;
	}

	@Override
	public Double valorComMulta(int dias, Double valor) {
		if(dias <= 5) {
			return this.calculadorDeMultaEJuros.calcula(valor, dias, 3, 0.2);
		}
		return this.proximaMulta.valorComMulta(dias, valor);
	}

	@Override
	public void proximaMulta(Multa proximaMulta) {
		this.proximaMulta = proximaMulta;		
	}

}
