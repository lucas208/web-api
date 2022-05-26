package rn.sead.gov.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import rn.sead.gov.model.PessoaJuridica;
import rn.sead.gov.repository.PessoaJuridicaRepository;
import rn.sead.gov.service.generic.AbstractService;

@Service
public class PessoaJuridicaService extends AbstractService<PessoaJuridica, PessoaJuridicaRepository> {

    public PessoaJuridicaService(PessoaJuridicaRepository repository) {
        super(repository);
    }

    @Override
	public Boolean softDelete(Long id) {
		Optional<PessoaJuridica> pessoaJuridica = repository.findById(id);
		if (pessoaJuridica.isEmpty()) {
			return false;
		} else {
			repository.softDeletePessoaJuridica(id);
			return true;
		}
	}
}
