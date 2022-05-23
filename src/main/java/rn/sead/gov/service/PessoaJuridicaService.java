package rn.sead.gov.service;

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
	public void softDelete(Long id) {
		// TODO Auto-generated method stub
		
	}
}
