package br.com.contas.infrastructure.service;

import java.time.Period;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.contas.application.multa.Multa;
import br.com.contas.application.repositorie.ContasAPagarRepository;
import br.com.contas.application.repositorie.RegraDeMultaPorContaRepository;
import br.com.contas.model.ContasAPagar;
import br.com.contas.model.RegraDeMultaPorConta;
import br.com.contas.model.dto.ContasAPagarInclusaoDTO;
import br.com.contas.model.dto.ContasAPagarListagemDTO;

@Service
public class ContasAPagarService {

	private ContasAPagarRepository contasAPagarRepository;
	private RegraDeMultaPorContaRepository regraDeMultaPorContaRepository;
	private Multa multa;

	@Autowired
	public ContasAPagarService(ContasAPagarRepository contasAPagarRepository,
			RegraDeMultaPorContaRepository regraDeMultaPorContaRepository,
			@Qualifier("ateTres") Multa multa) {
		this.contasAPagarRepository = contasAPagarRepository;
		this.regraDeMultaPorContaRepository = regraDeMultaPorContaRepository;
		this.multa = multa;
	}
	
	public List<ContasAPagarListagemDTO> listaTodasContas() {
		return ContasAPagarListagemDTO.converte(contasAPagarRepository.findAll());
	}
	
	public void inclui(ContasAPagarInclusaoDTO conta) {
		double valorCorrigido = 0;
		int diasDeAtraso = 0;
		RegraDeMultaPorConta regraDeMultaPorConta = new RegraDeMultaPorConta();
		if(conta.getDataPagamento().isAfter(conta.getDataVencimento())) {
			diasDeAtraso = Period.between(conta.getDataVencimento(), conta.getDataPagamento()).getDays();
			regraDeMultaPorConta = this.multa.valorComMulta(diasDeAtraso, conta.getValorOriginal());
			valorCorrigido = regraDeMultaPorConta.getValorCorrigido();
		}
		
		ContasAPagar contaAPagar = contasAPagarRepository.save(conta.converteParaOModelcom(valorCorrigido, diasDeAtraso));
		vinculaRegraDeMultaPorContaAConta(regraDeMultaPorConta, contaAPagar);
	}

	private void vinculaRegraDeMultaPorContaAConta(RegraDeMultaPorConta regraDeMultaPorConta,
			ContasAPagar contaAPagar) {
		if(Objects.nonNull(regraDeMultaPorConta.getDiasDeAtraso())) {
			regraDeMultaPorConta.vinculaAConta(contaAPagar);
			this.regraDeMultaPorContaRepository.save(regraDeMultaPorConta);
		}
	}

}
