package br.com.contas.infrastructure.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.contas.application.repositorie.ContasAPagarRepository;
import br.com.contas.model.ContasAPagar;
import br.com.contas.model.dto.ContasAPagarInclusaoDTO;
import br.com.contas.model.dto.ContasAPagarListagemDTO;

@Component
public class ContasAPagarControllerHooks {
	
	protected static final String NOME_CONTA_1 = "teste";
	protected static final Double VALOR_ORIGINAL_CONTA_1 = 1.0;
	protected static final LocalDate DATA_PAGAMENTO_CONTA_1 = LocalDate.now();
	protected static final Double VALOR_CORRIGIDO_CONTA_1 = 0.0;
	protected static final Integer QUANTIDADE_DIAS_DE_ATRASO_CONTA_1 = 0;
	
	protected static final String NOME_CONTA_2 = "teste 2";
	protected static final Double VALOR_ORIGINAL_CONTA_2 = 2.0;
	protected static final LocalDate DATA_VENCIMENTO_CONTA_2 = LocalDate.now();
	protected static final LocalDate DATA_PAGAMENTO_CONTA_2 = LocalDate.now();
	protected static final Double VALOR_CORRIGIDO_CONTA_2 = 0.0;
	protected static final Integer QUANTIDADE_DIAS_DE_ATRASO_CONTA_2 = 0;
	
	protected static final String NOME_CONTA_3 = "teste 3";
	protected static final Double VALOR_ORIGINAL_CONTA_3 = 100.0;
	protected static final LocalDate DATA_VENCIMENTO_CONTA_3 = LocalDate.now();
	protected static final LocalDate DATA_PAGAMENTO_CONTA_3 = LocalDate.now().plusDays(2);
	protected static final Double VALOR_CORRIGIDO_CONTA_3 = 102.204;
	protected static final Integer QUANTIDADE_DIAS_DE_ATRASO_CONTA_3 = 2;
	
	protected static final String NOME_CONTA_4 = "teste 4";
	protected static final Double VALOR_ORIGINAL_CONTA_4 = 200.0;
	protected static final LocalDate DATA_VENCIMENTO_CONTA_4 = LocalDate.now();
	protected static final LocalDate DATA_PAGAMENTO_CONTA_4 = LocalDate.now().plusDays(4);
	protected static final Double VALOR_CORRIGIDO_CONTA_4 = 207.648;
	protected static final Integer QUANTIDADE_DIAS_DE_ATRASO_CONTA_4 = 4;
	
	protected static final String NOME_CONTA_5 = "teste 5";
	protected static final Double VALOR_ORIGINAL_CONTA_5 = 300.0;
	protected static final LocalDate DATA_VENCIMENTO_CONTA_5 = LocalDate.now();
	protected static final LocalDate DATA_PAGAMENTO_CONTA_5 = LocalDate.now().plusDays(6);
	protected static final Double VALOR_CORRIGIDO_CONTA_5 = 320.67;
	protected static final Integer QUANTIDADE_DIAS_DE_ATRASO_CONTA_5 = 6;

	private ContasAPagarRepository contasAPagarRepository;
	private ContasAPagarInclusaoDTO contaIncluida;
    private TestRestTemplate restTemplate;
	
	@Autowired
	public ContasAPagarControllerHooks(ContasAPagarRepository contasAPagarRepository, 
			TestRestTemplate restTemplate) {
		this.contasAPagarRepository = contasAPagarRepository;
		this.restTemplate = restTemplate;
	}
	
	private ResponseEntity<ContasAPagarListagemDTO[]> listaTodasContas() {
		String urlEndpoint = new StringBuilder().append("/contas").toString();
		return this.restTemplate.getForEntity(urlEndpoint, ContasAPagarListagemDTO[].class);
	}
	
	private ResponseEntity<String> incluiConta(ContasAPagarInclusaoDTO request) {
		String urlEndpoint = new StringBuilder().append("/contas").toString();
		HttpEntity<ContasAPagarInclusaoDTO> entity = new HttpEntity<ContasAPagarInclusaoDTO>(request);
		return this.restTemplate.postForEntity(urlEndpoint, entity, String.class);
	}
	
	protected void limpaBaseDeDados() {
		this.contasAPagarRepository.deleteAll();
	}
	
	protected ResponseEntity<String> dadoOPreenchimentoCorretamenteNoEndpointDeInclusaoDeConta() {
		return incluiConta(incluiUmaConta());
	}
	
	private @Valid ContasAPagarInclusaoDTO incluiUmaConta() {
		this.contaIncluida = new ContasAPagarInclusaoDTO(NOME_CONTA_2, VALOR_ORIGINAL_CONTA_2, DATA_VENCIMENTO_CONTA_2, DATA_PAGAMENTO_CONTA_2);
		return this.contaIncluida;
	}
	
	protected ResponseEntity<String> dadoOPreenchimentoCorretamenteCom2DiasDeAtrasoNoPagamentoNoEndpointDeInclusaoDeConta() {
		return incluiConta(incluiUmaContaCom2DiasDeAtrasoDePagamento());
	}
	
	private @Valid ContasAPagarInclusaoDTO incluiUmaContaCom2DiasDeAtrasoDePagamento() {
		this.contaIncluida = new ContasAPagarInclusaoDTO(NOME_CONTA_3, VALOR_ORIGINAL_CONTA_3, DATA_VENCIMENTO_CONTA_3, DATA_PAGAMENTO_CONTA_3);
		return this.contaIncluida;
	}
	
	protected ResponseEntity<String> dadoOPreenchimentoCorretamenteCom4DiasDeAtrasoNoPagamentoNoEndpointDeInclusaoDeConta() {
		return incluiConta(incluiUmaContaCom4DiasDeAtrasoDePagamento());
	}
	
	private @Valid ContasAPagarInclusaoDTO incluiUmaContaCom4DiasDeAtrasoDePagamento() {
		this.contaIncluida = new ContasAPagarInclusaoDTO(NOME_CONTA_4, VALOR_ORIGINAL_CONTA_4, DATA_VENCIMENTO_CONTA_4, DATA_PAGAMENTO_CONTA_4);
		return this.contaIncluida;
	}
	
	protected ResponseEntity<String> dadoOPreenchimentoCorretamenteCom6DiasDeAtrasoNoPagamentoNoEndpointDeInclusaoDeConta() {
		return incluiConta(incluiUmaContaCom6DiasDeAtrasoDePagamento());
	}
	
	private @Valid ContasAPagarInclusaoDTO incluiUmaContaCom6DiasDeAtrasoDePagamento() {
		this.contaIncluida = new ContasAPagarInclusaoDTO(NOME_CONTA_5, VALOR_ORIGINAL_CONTA_5, DATA_VENCIMENTO_CONTA_5, DATA_PAGAMENTO_CONTA_5);
		return this.contaIncluida;
	}
	
	protected ResponseEntity<String> dadoOPreenchimentoDoCampoNomeComNuloNoEndpointDeInclusaoDeConta() {
		return incluiConta(incluiUmaContaComNomeNulo());
	}
	
	private @Valid ContasAPagarInclusaoDTO incluiUmaContaComNomeNulo() {
		this.contaIncluida = new ContasAPagarInclusaoDTO(null, VALOR_ORIGINAL_CONTA_5, DATA_VENCIMENTO_CONTA_5, DATA_PAGAMENTO_CONTA_5);
		return this.contaIncluida;
	}
	
	protected ResponseEntity<String> dadoOPreenchimentoDoCampoNomeComVazioNoEndpointDeInclusaoDeConta() {
		return incluiConta(incluiUmaContaComNomeVazio());
	}
	
	private @Valid ContasAPagarInclusaoDTO incluiUmaContaComNomeVazio() {
		this.contaIncluida = new ContasAPagarInclusaoDTO("", VALOR_ORIGINAL_CONTA_5, DATA_VENCIMENTO_CONTA_5, DATA_PAGAMENTO_CONTA_5);
		return this.contaIncluida;
	}
	
	protected ResponseEntity<String> dadoOPreenchimentoDoCampoValorOriginalComValorZeroNoEndpointDeInclusaoDeConta() {
		return incluiConta(incluiUmaContaComValorOriginalComValorZero());
	}
	
	private @Valid ContasAPagarInclusaoDTO incluiUmaContaComValorOriginalComValorZero() {
		this.contaIncluida = new ContasAPagarInclusaoDTO(NOME_CONTA_5, 0.0, DATA_VENCIMENTO_CONTA_5, DATA_PAGAMENTO_CONTA_5);
		return this.contaIncluida;
	}
	
	protected ResponseEntity<String> dadoOPreenchimentoDoCampoValorOriginalComNuloNoEndpointDeInclusaoDeConta() {
		return incluiConta(incluiUmaContaComValorOriginalNulo());
	}
	
	private @Valid ContasAPagarInclusaoDTO incluiUmaContaComValorOriginalNulo() {
		this.contaIncluida = new ContasAPagarInclusaoDTO(NOME_CONTA_5, null, DATA_VENCIMENTO_CONTA_5, DATA_PAGAMENTO_CONTA_5);
		return this.contaIncluida;
	}
	
	protected ResponseEntity<String> dadoOPreenchimentoDoCampoDataVencimentoComNuloNoEndpointDeInclusaoDeConta() {
		return incluiConta(incluiUmaContaComDataVencimentoNulo());
	}
	
	private @Valid ContasAPagarInclusaoDTO incluiUmaContaComDataVencimentoNulo() {
		this.contaIncluida = new ContasAPagarInclusaoDTO(NOME_CONTA_5, VALOR_ORIGINAL_CONTA_5, null, DATA_PAGAMENTO_CONTA_5);
		return this.contaIncluida;
	}
	
	protected ResponseEntity<String> dadoOPreenchimentoDoCampoDataPagamentoComNuloNoEndpointDeInclusaoDeConta() {
		return incluiConta(incluiUmaContaComDataPagamentoNulo());
	}
	
	private @Valid ContasAPagarInclusaoDTO incluiUmaContaComDataPagamentoNulo() {
		this.contaIncluida = new ContasAPagarInclusaoDTO(NOME_CONTA_5, VALOR_ORIGINAL_CONTA_5, DATA_VENCIMENTO_CONTA_5, null);
		return this.contaIncluida;
	}
	
	protected void dadoTenhaInformacaoNaBaseDeDados() {
		this.contasAPagarRepository.save(adicionaUmaConta());
	}
	
	private ContasAPagar adicionaUmaConta() {
		return new ContasAPagar(NOME_CONTA_1, VALOR_ORIGINAL_CONTA_1, DATA_PAGAMENTO_CONTA_1, 
				LocalDate.now(), VALOR_CORRIGIDO_CONTA_1, QUANTIDADE_DIAS_DE_ATRASO_CONTA_1);
	}
	
	protected ResponseEntity<ContasAPagarListagemDTO[]> quandoRealizarAConsultaNoEndpointDeListarTodasAsContas() {
		return listaTodasContas();
	}
	
	protected List<ContasAPagar> quandoRealizarAConsultaAoBancoDeDados() {
		return this.contasAPagarRepository.findAll();
	}
	
	public ContasAPagarInclusaoDTO getContaIncluida() {
		return contaIncluida;
	}

}
