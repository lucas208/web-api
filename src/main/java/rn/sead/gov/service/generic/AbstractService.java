package rn.sead.gov.service.generic;

import rn.sead.gov.model.generic.AbstractEntity;
import rn.sead.gov.repository.generic.GenericRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractService<E extends AbstractEntity, R extends GenericRepository<E>> implements IGenericService<E>  {
    protected final R repository;

    public AbstractService(R repository) {
        this.repository = repository;
    }

    @Override
    public List<E> findAll(){
        return repository.findAll();
    }

    @Override
    public E create(E entity) {
        return  repository.save(entity);
    }

    @Override
    public Optional<E> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<E> update(Long id,E entity) {
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
