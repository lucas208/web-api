package rn.sead.gov.repository.generic;

import org.springframework.data.jpa.repository.JpaRepository;
import rn.sead.gov.model.generic.AbstractEntity;

public interface GenericRepository<E extends AbstractEntity> extends JpaRepository<E, Long> {
}
