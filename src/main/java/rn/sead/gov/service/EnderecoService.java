package rn.sead.gov.service;

import java.util.Optional;

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
	public Boolean softDelete(Long id) {
		Optional<Endereco> endereco = repository.findById(id);
		if (endereco.isEmpty()) {
			return false;
		} else {
			repository.softDeleteEndereco(id);
			return true;
		}
	}
}
