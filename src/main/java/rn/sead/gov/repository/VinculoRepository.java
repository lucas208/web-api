package rn.sead.gov.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rn.sead.gov.model.Vinculo;
import rn.sead.gov.repository.generic.GenericRepository;

public interface VinculoRepository extends GenericRepository<Vinculo> {
	@Transactional
	@Modifying
	@Query("UPDATE Vinculo v SET v.removed = CURRENT_TIMESTAMP WHERE v.id =:id")
	void softDeleteVinculo(@Param("id") Long id);
}
