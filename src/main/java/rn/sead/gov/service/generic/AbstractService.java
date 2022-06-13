package rn.sead.gov.service.generic;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import rn.sead.gov.model.generic.AbstractEntity;
import rn.sead.gov.repository.generic.GenericRepository;

public abstract class AbstractService<E extends AbstractEntity, R extends GenericRepository<E>> implements IGenericService<E> {
    protected final R repository;

    public AbstractService(R repository) {
        this.repository = repository;
    }

    @Override
    public Page<E> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public E create(E entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public Optional<E> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<E> update(Long id, E entity) {
        entity.setId(id);
        return repository
                .findById(id)
                .map(record -> {
                    repository.saveAndFlush(entity);
                    return record;
                });
    }

    @Override
    public Boolean delete(Long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return true;
                }).orElse(false);
    }
}
