package br.com.contas.application.repositorie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.contas.model.ContasAPagar;

@Repository
public interface ContasAPagarRepository extends JpaRepository<ContasAPagar, Integer> {

}
