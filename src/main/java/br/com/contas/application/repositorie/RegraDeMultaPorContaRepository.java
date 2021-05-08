package br.com.contas.application.repositorie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.contas.model.RegraDeMultaPorConta;

@Repository
public interface RegraDeMultaPorContaRepository extends JpaRepository<RegraDeMultaPorConta, Integer> {

}
