package rn.sead.gov.service;

import org.springframework.stereotype.Service;
import rn.sead.gov.model.Endereco;
import rn.sead.gov.repository.EnderecoRepository;
import rn.sead.gov.service.generic.AbstractService;

@Service
public class EnderecoService extends AbstractService<Endereco, EnderecoRepository> {

    public EnderecoService(EnderecoRepository repository) {
        super(repository);
    }

    @Override
	public void softDelete(Long id) {
		repository.softDeleteEndereco(id);
	}
}
