package rn.sead.gov.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import rn.sead.gov.model.PessoaFisica;
import rn.sead.gov.repository.PessoaFisicaRepository;
import rn.sead.gov.service.generic.AbstractService;

@Service
public class PessoaFisicaService extends AbstractService<PessoaFisica, PessoaFisicaRepository> {

    public PessoaFisicaService(PessoaFisicaRepository repository) {
        super(repository);
    }

    @Override
	public Boolean softDelete(Long id) {
		Optional<PessoaFisica> pessoaFisica = repository.findById(id);
		if (pessoaFisica.isEmpty()) {
			return false;
		} else {
			repository.softDeletePessoaFisica(id);
			return true;
		}
	}
}



