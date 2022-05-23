package rn.sead.gov.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rn.sead.gov.model.Deficiencia;
import rn.sead.gov.repository.generic.GenericRepository;

public interface DeficienciaRepository extends GenericRepository<Deficiencia> {
	
	@Transactional
	@Modifying
	@Query("UPDATE Deficiencia d SET d.status = false WHERE d.id =:id")
	void softDeleteDeficiencia(@Param("id") Long id);
}
