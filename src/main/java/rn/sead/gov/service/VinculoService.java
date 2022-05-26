package rn.sead.gov.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import rn.sead.gov.model.Vinculo;
import rn.sead.gov.repository.VinculoRepository;
import rn.sead.gov.service.generic.AbstractService;

@Service
public class VinculoService extends AbstractService<Vinculo, VinculoRepository> {

    public VinculoService(VinculoRepository repository) {
        super(repository);
    }

    @Override
	public Boolean softDelete(Long id) {
		Optional<Vinculo> vinculo = repository.findById(id);
		if (vinculo.isEmpty()) {
			return false;
		} else {
			repository.softDeleteVinculo(id);
			return true;
		}
	}
}
