package rn.sead.gov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import rn.sead.gov.model.PessoaJuridica;
import rn.sead.gov.service.PessoaJuridicaService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/pessoas_juridicas")
public class  PessoaJuridicaController {

    @Autowired
    private PessoaJuridicaService service;

    @GetMapping
    public ResponseEntity<List<PessoaJuridica>> findAll() {
        List<PessoaJuridica> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<PessoaJuridica> create(@RequestBody PessoaJuridica entity) {
        entity = service.create(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(entity.getId()).toUri();
        return ResponseEntity.created(uri).body(entity);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<PessoaJuridica> findById(@PathVariable Long id) {
        return (ResponseEntity<PessoaJuridica>) service.findById(id).map(record -> ResponseEntity.ok().body((PessoaJuridica) record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PessoaJuridica> update(@PathVariable Long id, @RequestBody PessoaJuridica entity) {

        return (ResponseEntity<PessoaJuridica>) service.update(id, entity).map(record -> ResponseEntity.ok().body((PessoaJuridica) record))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (service.delete(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
