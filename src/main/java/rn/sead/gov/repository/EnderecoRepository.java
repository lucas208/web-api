package rn.sead.gov.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rn.sead.gov.model.Endereco;
import rn.sead.gov.repository.generic.GenericRepository;

public interface EnderecoRepository extends GenericRepository<Endereco> {
	@Transactional
	@Modifying
	@Query("UPDATE Endereco e SET e.removed = CURRENT_TIMESTAMP WHERE e.id =:id")
	void softDeleteEndereco(@Param("id") Long id);
}
