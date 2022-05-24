package rn.sead.gov.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rn.sead.gov.model.PessoaJuridica;
import rn.sead.gov.repository.generic.GenericRepository;

public interface PessoaJuridicaRepository extends GenericRepository<PessoaJuridica> {
	@Transactional
	@Modifying
	@Query("UPDATE PessoaJuridica p SET p.removed = CURRENT_TIMESTAMP WHERE p.id =:id")
	void softDeletePessoaJuridica(@Param("id") Long id);
}
