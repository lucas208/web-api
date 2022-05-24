package rn.sead.gov.controller.generic;

import org.springframework.http.ResponseEntity;
import rn.sead.gov.model.generic.AbstractEntity;

import java.util.List;

public interface IGenericController<E extends AbstractEntity> {

    List<E> findAll();

    E create(E entity);

    ResponseEntity<E> findById(Long id);

    ResponseEntity<E> update(Long id, E entity);

    ResponseEntity<?> delete(Long id);
}