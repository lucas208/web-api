package rn.sead.gov.service;

import org.springframework.stereotype.Service;
import rn.sead.gov.model.Deficiencia;
import rn.sead.gov.repository.DeficienciaRepository;
import rn.sead.gov.service.generic.AbstractService;

@Service
public class DeficienciaService extends AbstractService<Deficiencia, DeficienciaRepository> {

    public DeficienciaService(DeficienciaRepository repository) {
        super(repository);
    }

	@Override
	public void softDelete(Long id) {
		repository.softDeleteDeficiencia(id);		
	}
}
