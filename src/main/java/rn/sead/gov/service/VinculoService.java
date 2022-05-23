package rn.sead.gov.service;

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
	public void softDelete(Long id) {
		// TODO Auto-generated method stub
		
	}
}
