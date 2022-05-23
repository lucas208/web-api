package rn.sead.gov.service;

import org.springframework.stereotype.Service;
import rn.sead.gov.model.PessoaFisica;
import rn.sead.gov.repository.*;
import rn.sead.gov.service.generic.*;

@Service
public class PessoaFisicaService extends AbstractService<PessoaFisica, PessoaFisicaRepository> {

    public PessoaFisicaService(PessoaFisicaRepository repository) {
        super(repository);
    }

	@Override
	public void softDelete(Long id) {
		// TODO Auto-generated method stub
		
	}
}



