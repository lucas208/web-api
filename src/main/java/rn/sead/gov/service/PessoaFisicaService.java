package rn.sead.gov.service;

import org.springframework.stereotype.Service;
import rn.sead.gov.model.PessoaFisica;
import rn.sead.gov.repository.PessoaFisicaRepository;
import rn.sead.gov.service.generic.AbstractService;

@Service
public class PessoaFisicaService extends AbstractService<PessoaFisica, PessoaFisicaRepository> {

    public PessoaFisicaService(PessoaFisicaRepository repository) {
        super(repository);
    }
}


