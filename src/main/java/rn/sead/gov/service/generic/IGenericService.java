package rn.sead.gov.service.generic;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import rn.sead.gov.model.generic.AbstractEntity;

public interface IGenericService<E extends AbstractEntity> {

	Page<E> findAll(Pageable pageable);

    E create(E entity);

    Optional<E> findById(Long id);

    Optional<E> update(Long id, E entity);

    Boolean delete(Long id);
    
    Boolean softDelete(Long id);
}
