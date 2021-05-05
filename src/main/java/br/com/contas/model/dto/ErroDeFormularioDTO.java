package br.com.contas.model.dto;

public class ErroDeFormularioDTO {
	
	private String nomeCampo;
	private String erro;
	
	public ErroDeFormularioDTO(String nomeCampo, String erro) {
		this.nomeCampo = nomeCampo;
		this.erro = erro;
	}

	public String getNomeCampo() {
		return nomeCampo;
	}

	public String getErro() {
		return erro;
	}

}
