package rn.sead.gov.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rn.sead.gov.model.Servidor;
import rn.sead.gov.repository.generic.GenericRepository;


public interface ServidorRepository extends GenericRepository<Servidor>{
	@Transactional
	@Modifying
	@Query("UPDATE Servidor s SET s.removed = CURRENT_TIMESTAMP WHERE s.id =:id")
	void softDeleteServidor(@Param("id") Long id);
}
