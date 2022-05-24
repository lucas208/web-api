package rn.sead.gov.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rn.sead.gov.model.PessoaFisica;
import rn.sead.gov.repository.generic.GenericRepository;

public interface PessoaFisicaRepository extends GenericRepository<PessoaFisica> {
	@Transactional
	@Modifying
	@Query("UPDATE PessoaFisica p SET p.removed = CURRENT_TIMESTAMP WHERE p.id =:id")
	void softDeletePessoaFisica(@Param("id") Long id);
}
