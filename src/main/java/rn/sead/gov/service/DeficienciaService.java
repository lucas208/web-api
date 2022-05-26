package rn.sead.gov.service;

import java.util.Optional;

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
	public Boolean softDelete(Long id) {
		Optional<Deficiencia> deficiencia = repository.findById(id);
		if (deficiencia.isEmpty()) {
			return false;
		} else {
			repository.softDeleteDeficiencia(id);
			return true;
		}
	}
}
