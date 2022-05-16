package rn.sead.gov.service;

import org.springframework.stereotype.Service;
import rn.sead.gov.model.Servidor;
import rn.sead.gov.repository.ServidorRepository;
import rn.sead.gov.service.generic.AbstractService;

@Service
public class ServidorService extends AbstractService<Servidor, ServidorRepository> {

    public ServidorService(ServidorRepository repository) {
        super(repository);
    }
}
