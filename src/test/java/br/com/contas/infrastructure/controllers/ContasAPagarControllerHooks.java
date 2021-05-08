package br.com.contas.infrastructure.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.contas.application.repositorie.ContasAPagarRepository;
import br.com.contas.application.repositorie.RegraDeMultaPorContaRepository;
import br.com.contas.model.ContasAPagar;
import br.com.contas.model.dto.ContasAPagarListagemDTO;

@Component
public class ContasAPagarControllerHooks {
	
	protected static final String NOME_CONTA_1 = "teste";
	protected static final Double VALOR_ORIGINAL_CONTA_1 = 1.0;
	protected static final LocalDate DATA_PAGAMENTO_CONTA_1 = LocalDate.now();
	protected static final Double VALOR_CORRIGIDO_CONTA_1 = 0.0;
	protected static final Integer QUANTIDADE_DIAS_DE_ATRASO_CONTA_1 = 0;
	
	protected static final String NOME_CONTA_2 = "teste 2";
	protected static final String VALOR_ORIGINAL_CONTA_2 = "2.0";
	protected static final String DATA_VENCIMENTO_CONTA_2 = LocalDate.now().toString();
	protected static final String DATA_PAGAMENTO_CONTA_2 = LocalDate.now().toString();
	protected static final Double VALOR_CORRIGIDO_CONTA_2 = 0.0;
	protected static final Integer QUANTIDADE_DIAS_DE_ATRASO_CONTA_2 = 0;
	
	protected static final String NOME_CONTA_3 = "teste 3";
	protected static final String VALOR_ORIGINAL_CONTA_3 = "100.0";
	protected static final String DATA_VENCIMENTO_CONTA_3 = LocalDate.now().toString();
	protected static final String DATA_PAGAMENTO_CONTA_3 = LocalDate.now().plusDays(2).toString();
	protected static final Double VALOR_CORRIGIDO_CONTA_3 = 102.204;
	protected static final Integer QUANTIDADE_DIAS_DE_ATRASO_CONTA_3 = 2;
	
	protected static final String NOME_CONTA_4 = "teste 4";
	protected static final String VALOR_ORIGINAL_CONTA_4 = "200.0";
	protected static final String DATA_VENCIMENTO_CONTA_4 = LocalDate.now().toString();
	protected static final String DATA_PAGAMENTO_CONTA_4 = LocalDate.now().plusDays(4).toString();
	protected static final Double VALOR_CORRIGIDO_CONTA_4 = 207.648;
	protected static final Integer QUANTIDADE_DIAS_DE_ATRASO_CONTA_4 = 4;
	
	protected static final String NOME_CONTA_5 = "teste 5";
	protected static final String VALOR_ORIGINAL_CONTA_5 = "300.0";
	protected static final String DATA_VENCIMENTO_CONTA_5 = LocalDate.now().toString();
	protected static final String DATA_PAGAMENTO_CONTA_5 = LocalDate.now().plusDays(6).toString();
	protected static final Double VALOR_CORRIGIDO_CONTA_5 = 320.67;
	protected static final Integer QUANTIDADE_DIAS_DE_ATRASO_CONTA_5 = 6;

	private ContasAPagarRepository contasAPagarRepository;
	private RegraDeMultaPorContaRepository regraDeMultaPorContaRepository;
	private ContasAPagarInclusaoDTOTest contaIncluida;
    private TestRestTemplate restTemplate;
	
	@Autowired
	public ContasAPagarControllerHooks(ContasAPagarRepository contasAPagarRepository,
			RegraDeMultaPorContaRepository regraDeMultaPorContaRepository,
			TestRestTemplate restTemplate) {
		this.contasAPagarRepository = contasAPagarRepository;
		this.regraDeMultaPorContaRepository = regraDeMultaPorContaRepository;
		this.restTemplate = restTemplate;
	}
	
	private ResponseEntity<ContasAPagarListagemDTO[]> listaTodasContas() {
		String urlEndpoint = new StringBuilder().append("/contas").toString();
		return this.restTemplate.getForEntity(urlEndpoint, ContasAPagarListagemDTO[].class);
	}
	
	private ResponseEntity<String> incluiConta(ContasAPagarInclusaoDTOTest request) {
		String urlEndpoint = new StringBuilder().append("/contas").toString();
		HttpEntity<ContasAPagarInclusaoDTOTest> entity = new HttpEntity<ContasAPagarInclusaoDTOTest>(request);
		return this.restTemplate.postForEntity(urlEndpoint, entity, String.class);
	}
	
	protected void limpaBaseDeDados() {
		this.regraDeMultaPorContaRepository.deleteAll();
		this.contasAPagarRepository.deleteAll();
	}
	
	protected ResponseEntity<String> dadoOPreenchimentoCorretamenteNoEndpointDeInclusaoDeConta() {
		return incluiConta(incluiUmaConta());
	}
	
	private ContasAPagarInclusaoDTOTest incluiUmaConta() {
		this.contaIncluida = new ContasAPagarInclusaoDTOTest(NOME_CONTA_2, VALOR_ORIGINAL_CONTA_2, DATA_VENCIMENTO_CONTA_2, DATA_PAGAMENTO_CONTA_2);
		return this.contaIncluida;
	}
	
	protected ResponseEntity<String> dadoOPreenchimentoCorretamenteCom2DiasDeAtrasoNoPagamentoNoEndpointDeInclusaoDeConta() {
		return incluiConta(incluiUmaContaCom2DiasDeAtrasoDePagamento());
	}
	
	private ContasAPagarInclusaoDTOTest incluiUmaContaCom2DiasDeAtrasoDePagamento() {
		this.contaIncluida = new ContasAPagarInclusaoDTOTest(NOME_CONTA_3, VALOR_ORIGINAL_CONTA_3, DATA_VENCIMENTO_CONTA_3, DATA_PAGAMENTO_CONTA_3);
		return this.contaIncluida;
	}
	
	protected ResponseEntity<String> dadoOPreenchimentoCorretamenteCom4DiasDeAtrasoNoPagamentoNoEndpointDeInclusaoDeConta() {
		return incluiConta(incluiUmaContaCom4DiasDeAtrasoDePagamento());
	}
	
	private ContasAPagarInclusaoDTOTest incluiUmaContaCom4DiasDeAtrasoDePagamento() {
		this.contaIncluida = new ContasAPagarInclusaoDTOTest(NOME_CONTA_4, VALOR_ORIGINAL_CONTA_4, DATA_VENCIMENTO_CONTA_4, DATA_PAGAMENTO_CONTA_4);
		return this.contaIncluida;
	}
	
	protected ResponseEntity<String> dadoOPreenchimentoCorretamenteCom6DiasDeAtrasoNoPagamentoNoEndpointDeInclusaoDeConta() {
		return incluiConta(incluiUmaContaCom6DiasDeAtrasoDePagamento());
	}
	
	private ContasAPagarInclusaoDTOTest incluiUmaContaCom6DiasDeAtrasoDePagamento() {
		this.contaIncluida = new ContasAPagarInclusaoDTOTest(NOME_CONTA_5, VALOR_ORIGINAL_CONTA_5, DATA_VENCIMENTO_CONTA_5, DATA_PAGAMENTO_CONTA_5);
		return this.contaIncluida;
	}
	
	protected ResponseEntity<String> dadoOPreenchimentoDoCampoNomeComNuloNoEndpointDeInclusaoDeConta() {
		return incluiConta(incluiUmaContaComNomeNulo());
	}
	
	private ContasAPagarInclusaoDTOTest incluiUmaContaComNomeNulo() {
		this.contaIncluida = new ContasAPagarInclusaoDTOTest(null, VALOR_ORIGINAL_CONTA_5, DATA_VENCIMENTO_CONTA_5, DATA_PAGAMENTO_CONTA_5);
		return this.contaIncluida;
	}
	
	protected ResponseEntity<String> dadoOPreenchimentoDoCampoNomeComVazioNoEndpointDeInclusaoDeConta() {
		return incluiConta(incluiUmaContaComNomeVazio());
	}
	
	private ContasAPagarInclusaoDTOTest incluiUmaContaComNomeVazio() {
		this.contaIncluida = new ContasAPagarInclusaoDTOTest("", VALOR_ORIGINAL_CONTA_5, DATA_VENCIMENTO_CONTA_5, DATA_PAGAMENTO_CONTA_5);
		return this.contaIncluida;
	}
	
	protected ResponseEntity<String> dadoOPreenchimentoDoCampoValorOriginalComValorZeroNoEndpointDeInclusaoDeConta() {
		return incluiConta(incluiUmaContaComValorOriginalComValorZero());
	}
	
	private ContasAPagarInclusaoDTOTest incluiUmaContaComValorOriginalComValorZero() {
		this.contaIncluida = new ContasAPagarInclusaoDTOTest(NOME_CONTA_5, "0.0", DATA_VENCIMENTO_CONTA_5, DATA_PAGAMENTO_CONTA_5);
		return this.contaIncluida;
	}
	
	protected ResponseEntity<String> dadoOPreenchimentoDoCampoValorOriginalComNuloNoEndpointDeInclusaoDeConta() {
		return incluiConta(incluiUmaContaComValorOriginalNulo());
	}
	
	private ContasAPagarInclusaoDTOTest incluiUmaContaComValorOriginalNulo() {
		this.contaIncluida = new ContasAPagarInclusaoDTOTest(NOME_CONTA_5, null, DATA_VENCIMENTO_CONTA_5, DATA_PAGAMENTO_CONTA_5);
		return this.contaIncluida;
	}
	
	protected ResponseEntity<String> dadoOPreenchimentoDoCampoValorOriginalComFormatoErradoNoEndpointDeInclusaoDeConta() {
		return incluiConta(incluiUmaContaComValorOriginalComFormatoErrado());
	}
	
	private ContasAPagarInclusaoDTOTest incluiUmaContaComValorOriginalComFormatoErrado() {
		this.contaIncluida = new ContasAPagarInclusaoDTOTest(NOME_CONTA_5, "a", DATA_VENCIMENTO_CONTA_5, DATA_PAGAMENTO_CONTA_5);
		return this.contaIncluida;
	}
	
	protected ResponseEntity<String> dadoOPreenchimentoDoCampoValorOriginalComVazioNoEndpointDeInclusaoDeConta() {
		return incluiConta(incluiUmaContaComValorOriginalComVazio());
	}
	
	private ContasAPagarInclusaoDTOTest incluiUmaContaComValorOriginalComVazio() {
		this.contaIncluida = new ContasAPagarInclusaoDTOTest(NOME_CONTA_5, "", DATA_VENCIMENTO_CONTA_5, DATA_PAGAMENTO_CONTA_5);
		return this.contaIncluida;
	}
	
	protected ResponseEntity<String> dadoOPreenchimentoDoCampoDataVencimentoComNuloNoEndpointDeInclusaoDeConta() {
		return incluiConta(incluiUmaContaComDataVencimentoNulo());
	}
	
	private ContasAPagarInclusaoDTOTest incluiUmaContaComDataVencimentoNulo() {
		this.contaIncluida = new ContasAPagarInclusaoDTOTest(NOME_CONTA_5, VALOR_ORIGINAL_CONTA_5, null, DATA_PAGAMENTO_CONTA_5);
		return this.contaIncluida;
	}
	
	protected ResponseEntity<String> dadoOPreenchimentoDoCampoDataVencimentoComVazioNoEndpointDeInclusaoDeConta() {
		return incluiConta(incluiUmaContaComDataVencimentoVazio());
	}
	
	private ContasAPagarInclusaoDTOTest incluiUmaContaComDataVencimentoVazio() {
		this.contaIncluida = new ContasAPagarInclusaoDTOTest(NOME_CONTA_5, VALOR_ORIGINAL_CONTA_5, "", DATA_PAGAMENTO_CONTA_5);
		return this.contaIncluida;
	}
	
	protected ResponseEntity<String> dadoOPreenchimentoDoCampoDataVencimentoComFormatoErradoNoEndpointDeInclusaoDeConta() {
		return incluiConta(incluiUmaContaComDataVencimentoNoFormatoErrado());
	}
	
	private ContasAPagarInclusaoDTOTest incluiUmaContaComDataVencimentoNoFormatoErrado() {
		this.contaIncluida = new ContasAPagarInclusaoDTOTest(NOME_CONTA_5, VALOR_ORIGINAL_CONTA_5, "01/01/2021", DATA_PAGAMENTO_CONTA_5);
		return this.contaIncluida;
	}
	
	protected ResponseEntity<String> dadoOPreenchimentoDoCampoDataPagamentoComNuloNoEndpointDeInclusaoDeConta() {
		return incluiConta(incluiUmaContaComDataPagamentoNulo());
	}
	
	private ContasAPagarInclusaoDTOTest incluiUmaContaComDataPagamentoNulo() {
		this.contaIncluida = new ContasAPagarInclusaoDTOTest(NOME_CONTA_5, VALOR_ORIGINAL_CONTA_5, DATA_VENCIMENTO_CONTA_5, null);
		return this.contaIncluida;
	}
	
	protected ResponseEntity<String> dadoOPreenchimentoDoCampoDataPagamentoComVazioNoEndpointDeInclusaoDeConta() {
		return incluiConta(incluiUmaContaComDataPagamentoVazio());
	}
	
	private ContasAPagarInclusaoDTOTest incluiUmaContaComDataPagamentoVazio() {
		this.contaIncluida = new ContasAPagarInclusaoDTOTest(NOME_CONTA_5, VALOR_ORIGINAL_CONTA_5, DATA_VENCIMENTO_CONTA_5, "");
		return this.contaIncluida;
	}
	
	protected ResponseEntity<String> dadoOPreenchimentoDoCampoDataPagamentoComFormatoErradoNoEndpointDeInclusaoDeConta() {
		return incluiConta(incluiUmaContaComDataPagamentoNoFormatoErrado());
	}
	
	private ContasAPagarInclusaoDTOTest incluiUmaContaComDataPagamentoNoFormatoErrado() {
		this.contaIncluida = new ContasAPagarInclusaoDTOTest(NOME_CONTA_5, VALOR_ORIGINAL_CONTA_5, DATA_VENCIMENTO_CONTA_5, "01/01/2021");
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
	
	public ContasAPagarInclusaoDTOTest getContaIncluida() {
		return contaIncluida;
	}

}
